pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                checkout scm
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

        stage('SonarQube analysis') {
            steps {
                // Run SonarQube analysis
                script {
                    // Define the SonarQube Scanner configuration
                    def scannerHome = tool 'sonar-scanner'

                    // Execute SonarQube analysis
                    withEnv(["PATH+SCANNER=${scannerHome}/bin"]) {
                        bat "${scannerHome}/bin/sonar-scanner.bat -Dsonar.login=${env.sonarToken}"
                    }
                }
            }
        }
    }
}
