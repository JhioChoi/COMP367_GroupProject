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
                // Execute SonarQube analysis using Maven SonarQube plugin
                bat """mvn sonar:sonar -Dsonar.url=http://localhost:9000/ ^
                    -Dsonar.login=squ_2737c83846be05a723627b57ed0cf7a14ecd7035 ^
                    -Dsonar.projectName=COMP367_GroupProject ^
                    -Dsonar.java.binaries=. ^
                    -Dsonar.projectKey=COMP367_GroupProject"""
                }
            }
        }
    }
}
