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

        stage('Sonar Analysis') {
            steps {
                // Define the SonarQube Scanner configuration
                def scannerHome = tool 'sonar-scanner'

                // Execute SonarQube analysis using the scanner
                withEnv(["PATH+SCANNER=${scannerHome}/bin"]) {
                    bat """${scannerHome}/bin/sonar-scanner.bat \\
                        -Dsonar.login=${env.sonarToken} \\
                        -Dsonar.projectName=COMP367_GroupProject \\
                        -Dsonar.projectKey=COMP367_GroupProject"""
                }
            }
        }
    }
}
