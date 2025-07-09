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
                git url: 'https://github.com/ranatosh-sarkar/SavingsAccount_APIs_ULPROTOTYPE.git'
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t $IMAGE_NAME .
                '''
            }
        }

        stage('Run API Container') {
            steps {
                sh '''
                docker rm -f $CONTAINER_NAME || true
                docker run -d --name $CONTAINER_NAME -p $PORT:8082 $IMAGE_NAME
                '''
            }
        }

        stage('Verify Container is Running') {
            steps {
                sh 'docker ps'
                sh 'curl --retry 5 --retry-delay 3 http://localhost:8082/UL_SavingsAccount-API_prototype/registers'
            }
        }

    }

    post {
        always {
            echo 'Cleaning up...'
            sh 'docker rm -f $CONTAINER_NAME || true'
        }
    }
}
