pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                checkout scm
            }
        }

        stage('Sonar Analysis') {
            steps {
                // Configure SonarQube environment
                withSonarQubeEnv(credentialsId: 'sonarToken', installationName: 'sonar-server') {
                    // Execute SonarQube analysis
                    bat '''"C:\\SonarQube\\bin\\sonar-scanner" ^
                        -Dsonar.projectName=COMP367_GroupProject ^
                        -Dsonar.projectKey=COMP367_GroupProject ^
                        -Dsonar.sources=. ^
                        -Dsonar.java.binaries=. ^
                        -Dsonar.host.url=http://localhost:9000/'''
                }
            }
        }

        stage('Build Maven') {
            steps {
                // Build the Maven project
                bat 'mvn clean install'
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
    }
}
