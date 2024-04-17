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
                bat 'mvn release:prepare release:perform -DreleaseVersion=0.0.1 -DdevelopmentVersion=0.0.2-SNAPSHOT -DbranchName=createJenkinsfile -DskipTests=true -Darguments="-Dmaven.deploy.skip=true -Dtag=false"'
                echo 'Artifact has been released successfully.'
            }
        }
    }
}
