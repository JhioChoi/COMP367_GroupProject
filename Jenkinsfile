pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                checkout scm
            }
        }

        stage('Static Code Analysis') {
            steps {
                script {
                    // Run SonarQube analysis
                    withSonarQubeEnv('webapp') {
                        bat 'mvn sonar:sonar'
                    }
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

