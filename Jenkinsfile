pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-token',
                    url: 'https://github.com/anshusaini19/SpringBoot-Docker.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t springboot-docker .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                docker stop springboot-container || true
                docker rm springboot-container || true
                docker run -d --name springboot-container -p 8081:8080 springboot-docker
                '''
            }
        }
    }
}