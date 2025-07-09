pipeline {
    agent any

    environment {
        IMAGE_NAME = 'savings-api-image'
        CONTAINER_NAME = 'savings-api-container'
        PORT = '8082'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/ranatosh-sarkar/SavingsAccount_APIs_ULPROTOTYPE.git'
            }
        }

        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
            }
        }

        stage('Run API Container') {
            steps {
                bat '''
                    docker rm -f %CONTAINER_NAME% || exit 0
                    docker run -d --name %CONTAINER_NAME% -p %PORT%:8082 %IMAGE_NAME%
                '''
            }
        }

        stage('Verify Container is Running') {
            steps {
                bat 'docker ps'
                bat 'ping -n 21 127.0.0.1 > nul'
                bat 'curl --retry 5 --retry-delay 3 http://localhost:8082/UL_SavingsAccount-API_prototype/registers || exit 0'
            }
        }

        stage('Dump API Logs') {
            steps {
                bat 'docker logs %CONTAINER_NAME%'
            }
        }

        stage('QA Approval') {
            steps {
            input message: 'API container is up and running. Cypress tests can now be executed.Do NOT proceed unless QA has verified manually.'

        }
}
    }

    //post {
    //    always {
    //        echo 'Cleaning up...'
    //        bat 'docker rm -f %CONTAINER_NAME% || exit 0'
    //    }
    //}
}

