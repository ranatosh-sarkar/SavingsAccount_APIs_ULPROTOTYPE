pipeline {
    agent any

    environment {
    IMAGE_NAME = "ul-savings-api"
    DOCKER_REGISTRY = "ulsavingsacr.azurecr.io"
    
    // Azure SP credentials (Jenkins secret ID: azure-sp)
    AZURE_CREDENTIALS = credentials('azure-sp')

    // ACR login (Jenkins secrets: ACR_USERNAME, ACR_PASSWORD)
    ACR_USERNAME = credentials('ACR_USERNAME')
    ACR_PASSWORD = credentials('ACR_PASSWORD')

    // Resource settings
    RESOURCE_GROUP = "savings-rg"
    CONTAINER_NAME = "ul-savings-api-qa"
    LOCATION = "westeurope"
    CONTAINER_PORT = "8080"

    // Oracle DB (Jenkins secrets)
    SPRING_DATASOURCE_URL = credentials('SPRING_DATASOURCE_URL')
    SPRING_DATASOURCE_USERNAME = credentials('SPRING_DATASOURCE_USERNAME')
    SPRING_DATASOURCE_PASSWORD = credentials('SPRING_DATASOURCE_PASSWORD')
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
                    echo $ACR_PASSWORD | docker login $DOCKER_REGISTRY -u $ACR_USERNAME --password-stdin
                    docker push $DOCKER_REGISTRY/$IMAGE_NAME:$BUILD_NUMBER
                   '''
            }
        }

        stage('Deploy to Azure ACI (QA)') {
            steps {
                sh '''
                az login --service-principal -u $AZURE_CREDENTIALS_USR -p $AZURE_CREDENTIALS_PSW --tenant 0084b924-3ab4-4116-9251-9939f695e54c
                az container create \
                --resource-group $RESOURCE_GROUP \
                  --name $CONTAINER_NAME \
                  --image $DOCKER_REGISTRY/$IMAGE_NAME:$BUILD_NUMBER \
                  --cpu 1 --memory 1 \
                  --registry-login-server $DOCKER_REGISTRY \
                  --registry-username $ACR_USERNAME \
                  --registry-password $ACR_PASSWORD \
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
