pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // checkout source code from the repository
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                // build the Maven project
                bat 'mvn clean install'
                // archive the artifact
                archiveArtifacts 'target/*.jar'
            }
        }

        stage('Test') {
            steps {
                // run unit tests 
                bat 'mvn test'
                // generate code coverage report
                bat 'mvn jacoco:report'
            }
        }

        stage('SonarQube analysis') {
            steps {
                // run SonarQube analysis
                withSonarQubeEnv('sonar-server') {
                    bat "mvn sonar:sonar -Dsonar.login=squ_2737c83846be05a723627b57ed0cf7a14ecd7035"
                }
            }
        }

        stage('Deliver') {
            steps {
                echo 'Releasing artifact...'
                // perform a release 
                withCredentials([string(credentialsId: 'githubToken', variable: 'GITHUB_TOKEN')]) {
                    bat 'mvn release:prepare release:perform -Dbranch=master -DautoVersionSubmodules=true -DskipTests=true -Darguments="-Dmaven.deploy.skip=true -Dgithub.token=${GITHUB_TOKEN}" -DallowTimestampedSnapshots=true'
                }
                echo 'Artifact has been released successfully.'
            }
        }

        stage('Deploy to Dev Env') {
            steps {
                // deploy artifact to the development environment
                // launch deployed app 
                echo 'Deploying to Dev Env...'
                echo 'Launching deployed app...'
            }
        }

        stage('Deploy to QAT Env') {
            steps {
                // deploy artifact to the QAT environment
                echo 'Deploying to QAT Env...'
            }
        }

        stage('Deploy to Staging Env') {
            steps {
                // deploy artifact to the staging environment
                echo 'Deploying to Staging Env...'
            }
        }

        stage('Deploy to Production Env') {
            steps {
                // deploy artifact to the production environment
                echo 'Deploying to Production Env...'
            }
        }
    }
}
