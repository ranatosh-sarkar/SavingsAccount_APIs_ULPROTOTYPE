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
                bat 'ping -n 45 127.0.0.1 > nul'
                bat 'curl --retry 5 --retry-delay 3 http://localhost:%PORT%/UL_SavingsAccount-API_prototype/registers || exit 1'
            }
        }
    }

    post {
        success {
            echo "✅ QA container running. Waiting for manual sign-off."
        }
        failure {
            echo "❌ Deployment failed."
        }
    }
}
