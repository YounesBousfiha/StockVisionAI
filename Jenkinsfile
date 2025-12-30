pipeline {
    agent any

    tools {
            maven 'maven3'
            jdk 'jdk21'
        }

    environment {
        APP_NAME = 'stockvision-backend'

        SONAR_ORG = 'younesbousfiha'
        SONAR_PROJECT = 'younesbousfiha_StockVisionAI'
        SONAR_AUTH_TOKEN = credentials('sonarcloud-token')
    }

    stages {
        stage('📥 Checkout') {
            steps {
               checkout scm
            }
        }

        stage('🧪 Build & Analyze') {
           steps {
                script {
                    withSonarQubeEnv('sonar-cloud') {
                        sh """
                            mvn clean package sonar:sonar \
                            -Dsonar.organization=${SONAR_ORG} \
                            -Dsonar.projectKey=${SONAR_PROJECT} \
                            -Dsonar.host.url=https://sonarcloud.io \
                            -Dsonar.login=${SONAR_AUTH_TOKEN}
                         """
                    }
                }
           }
        }

        stage('🐳 Build Docker Image') {
            steps {
                script {
                    echo "Building Docker Image..."
                    sh "docker build -t ${APP_NAME}:latest ."
                }
            }
        }

        stage('🧹 Cleanup Docker') {
            steps {
                script {
                    echo "Cleaning up Docker images..."
                    sh "docker rmi ${APP_NAME}:latest || true"
                    sh "docker image prune -f"
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}