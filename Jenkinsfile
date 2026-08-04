pipeline {

    agent any

    tools {
        jdk 'java17'
        maven 'Maven'
    }

    environment {
        DOCKER_IMAGE = "anshusaini1911/springboot-docker"
        IMAGE_TAG = "latest"
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main',
                    credentialsId: 'github-token',
                    url: 'https://github.com/anshusaini19/SpringBoot-Docker.git'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-token',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {
                    sh '''
                    echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t $DOCKER_IMAGE:$IMAGE_TAG .
                '''
            }
        }

        stage('Push Docker Image') {
            steps {
                sh '''
                docker push $DOCKER_IMAGE:$IMAGE_TAG
                '''
            }
        }

        stage('Deploy Container') {
            steps {
                withCredentials([
                    string(credentialsId: 'google-client-id', variable: 'GOOGLE_CLIENT_ID'),
                    string(credentialsId: 'google-client-secret', variable: 'GOOGLE_CLIENT_SECRET')
                ]) {

                    sh '''
                    docker stop springboot-container || true
                    docker rm springboot-container || true

                    docker run -d \
                      --name springboot-container \
                      -p 8081:8080 \
                      -e GOOGLE_CLIENT_ID="$GOOGLE_CLIENT_ID" \
                      -e GOOGLE_CLIENT_SECRET="$GOOGLE_CLIENT_SECRET" \
                      $DOCKER_IMAGE:$IMAGE_TAG
                    '''
                }
            }
        }
    }

    post {

        success {
            echo 'Application deployed successfully'

            emailext(
                subject: "SUCCESS : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
Build Successful

Job Name : ${env.JOB_NAME}
Build Number : ${env.BUILD_NUMBER}

Application deployed successfully.

Build URL:
${env.BUILD_URL}
""",
                to: "anshusaini2004@gmail.com"
            )
        }

        failure {
            echo 'Pipeline failed'

            emailext(
                subject: "FAILED : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
Build Failed

Job Name : ${env.JOB_NAME}
Build Number : ${env.BUILD_NUMBER}

Please check Jenkins Console Output.

Build URL:
${env.BUILD_URL}
""",
                to: "anshusaini2004@gmail.com"
            )
        }

        always {
            echo 'Pipeline execution completed'
            cleanWs()
        }
    }
}