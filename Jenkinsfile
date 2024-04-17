pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                // build maven project
                bat 'mvn clean install'
                // archive artifact
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
                // run sonarqube analysis
                withSonarQubeEnv('sonar-server') {
                    bat "mvn sonar:sonar -Dsonar.login=squ_2737c83846be05a723627b57ed0cf7a14ecd7035"
                }
            }
        }

        stage('Deliver') {
            steps {
                echo 'Releasing artifact...'
                // perform a release 
                bat 'mvn release:prepare release:perform -DreleaseVersion=0.0.1 -DdevelopmentVersion=0.0.2-SNAPSHOT -Dbranch=createJenkinsfile -DskipTests=true -Darguments="-Dmaven.deploy.skip=true -Dtag=false"'
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
                echo 'Deploying to QAT Env...'
            }
        }

        stage('Deploy to Staging Env') {
            steps {
                echo 'Deploying to Staging Env...'
            }
        }

        stage('Deploy to Production Env') {
            steps {
                echo 'Deploying to Production Env...'
            }
        }
    }
}
