pipeline {
    agent any

    environment {
        IMAGE_NAME = 'savings-api-image-qa'
        CONTAINER_NAME = 'savings-api-container-qa'
        PORT = '8082'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ranatosh-sarkar/SavingsAccount_APIs_ULPROTOTYPE.git'
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

        stage('Run Docker Container') {
            steps {
                bat '''
                    docker rm -f %CONTAINER_NAME% || exit 0
                    docker run -d --name %CONTAINER_NAME% -p %PORT%:8082 %IMAGE_NAME%
                '''
            }
        }

        stage('Verify API is Running') {
            steps {
                bat 'docker ps'
                bat 'ping -n 51 127.0.0.1 > nul'
                bat 'curl --retry 5 --retry-delay 3 http://localhost:%PORT%/UL_SavingsAccount-API_prototype/registers || exit 1'
            }
        }

stage('Dump UAT Logs') {
    steps {
        echo "Image being used: ${IMAGE_NAME}"
        echo "Container running: ${CONTAINER_NAME}"
        bat 'docker logs %CONTAINER_NAME% || echo "No logs available from container."'
    }
}
        
        stage('Wait for QA Sign-off') {
            steps {
                input message: 'Has QA completed testing and approved shutdown?', ok: 'Shutdown API Container'
            }
        }
        stage('Cleanup API Container') {
            steps {
                bat 'docker rm -f %CONTAINER_NAME% || exit 0'
                bat 'docker rmi -f %IMAGE_NAME% || exit 0'
            }
        }
    }

    post {
        success {
            echo "Killed Container and Image"
        }
        failure {
            echo "Deployment failed."
        }
    }
}
