pipeline {
    agent any

    environment {
        KUBE_CONTEXT = "docker-desktop"  // Set the Kubernetes context for Docker Desktop
        MYSQL_HOST = "localhost"
        MYSQL_PORT = "31000"
        MYSQL_DB = "genpact"
        MYSQL_USER = "root"
        MYSQL_PASSWORD = "root"
        KUBECONFIG = '/c/Users/850075939/.kube/config'  // Path to kubeconfig
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker Image...'
                    bat 'docker build -t studentapp .'  // Builds the Docker image for your app
                }
            }
        }

        stage('Set Kubernetes Context') {
            steps {
                script {
                    echo 'Switching Kubernetes Context to docker-desktop...'
                    bat "kubectl config use-context ${KUBE_CONTEXT}"  // Switch to the correct Kubernetes context
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    bat 'kubectl apply -f deployment.yml'  // Deploy your Kubernetes configuration using a .yml file
                }
            }
        }

        stage('Test Database Connection') {
            steps {
                script {
                    echo 'Testing Database Connection...'
                    bat '''mysql -h %MYSQL_HOST% -P %MYSQL_PORT% -u %MYSQL_USER% -p%MYSQL_PASSWORD% -D %MYSQL_DB% -e "SHOW TABLES;"'''
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Clean the workspace after the pipeline runs
        }

        success {
            echo 'Pipeline executed successfully!'
        }

        failure {
            echo 'Pipeline failed.'
        }
    }
}
