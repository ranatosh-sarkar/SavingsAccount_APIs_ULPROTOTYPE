pipeline {
    agent any

    environment {
        IMAGE_NAME = "ul-savings-api"
        DOCKER_REGISTRY = "youracrname.azurecr.io"
        AZURE_CREDENTIALS = credentials('azure-sp') // ID from Jenkins credential store
        RESOURCE_GROUP = "yourResourceGroup"
        CONTAINER_NAME = "ul-savings-api-qa"
        LOCATION = "westeurope"
        CONTAINER_PORT = "8080"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://dev.azure.com/yourorg/SpringBootAPI/_git/SavingsAccount_APIs_ULPROTOTYPE'
            }
        }

        stage('Build JAR') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t $DOCKER_REGISTRY/$IMAGE_NAME:$BUILD_NUMBER .
                echo $AZURE_CREDENTIALS_PSW | docker login $DOCKER_REGISTRY -u $AZURE_CREDENTIALS_USR --password-stdin
                docker push $DOCKER_REGISTRY/$IMAGE_NAME:$BUILD_NUMBER
                '''
            }
        }

        stage('Deploy to Azure ACI (QA)') {
            steps {
                sh '''
                az login --service-principal -u $AZURE_CREDENTIALS_USR -p $AZURE_CREDENTIALS_PSW --tenant YOUR_TENANT_ID
                az container create \
                  --resource-group $RESOURCE_GROUP \
                  --name $CONTAINER_NAME \
                  --image $DOCKER_REGISTRY/$IMAGE_NAME:$BUILD_NUMBER \
                  --cpu 1 --memory 1 \
                  --registry-login-server $DOCKER_REGISTRY \
                  --registry-username $AZURE_CREDENTIALS_USR \
                  --registry-password $AZURE_CREDENTIALS_PSW \
                  --dns-name-label ul-savings-api-qa-${BUILD_NUMBER} \
                  --ports $CONTAINER_PORT \
                  --environment-variables \
                      SPRING_DATASOURCE_URL=$SPRING_DATASOURCE_URL \
                      SPRING_DATASOURCE_USERNAME=$SPRING_DATASOURCE_USERNAME \
                      SPRING_DATASOURCE_PASSWORD=$SPRING_DATASOURCE_PASSWORD
                '''
            }
        }

        stage('Cleanup (temporary)') {
            steps {
                input message: 'Delete QA container?'
                sh '''
                az container delete --name $CONTAINER_NAME --resource-group $RESOURCE_GROUP --yes
                '''
            }
        }
    }
}
