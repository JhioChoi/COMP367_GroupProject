pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout source code from the repository
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                // Build the Maven project
                bat 'mvn clean install'
                // Archive the artifact
                archiveArtifacts 'target/*.jar'
            }
        }

        stage('Test') {
            steps {
                // Run unit tests and generate code coverage report
                bat 'mvn test'
                // Generate code coverage report
                bat 'mvn jacoco:report'
            }
        }

        stage('SonarQube analysis') {
            steps {
                // Run SonarQube analysis
                withSonarQubeEnv('sonar-server') {
                    bat "mvn sonar:sonar -Dsonar.login=squ_2737c83846be05a723627b57ed0cf7a14ecd7035"
                }
            }
        }

        stage('Deliver') {
            steps {
                echo 'Releasing artifact...'
                // perform a release with the Maven Release Plugin
                bat 'mvn release:prepare release:perform -DtagNameFormat=@{project.artifactId}-@{project.version}'
                echo 'Artifact has been released successfully.'
            }
        }

        stage('Deploy to Dev Env') {
            steps {
                // Deploy artifact to the development environment
                // Launch deployed app 
                echo 'Deploying to Dev Env...'
                echo 'Launching deployed app...'
            }
        }

        stage('Deploy to QAT Env') {
            steps {
                // Deploy artifact to the QA/test environment
                echo 'Deploying to QAT Env...'
            }
        }

        stage('Deploy to Staging Env') {
            steps {
                // Deploy artifact to the staging environment
                echo 'Deploying to Staging Env...'
            }
        }

        stage('Deploy to Production Env') {
            steps {
                // Deploy artifact to the production environment
                echo 'Deploying to Production Env...'
            }
        }
    }
}
