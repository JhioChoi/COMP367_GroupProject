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
                withSonarQubeEnv('sonar-server') {
                    bat "mvn sonar:sonar -Dsonar.login=${env.sonarToken}"
                }
            }
        }
    }
}
