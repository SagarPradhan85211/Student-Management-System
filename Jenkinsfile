pipeline {
    agent any
    environment {
        KUBE_CONTEXT = "docker-desktop"  // Set the Kubernetes context for Docker Desktop
        MYSQL_HOST = "localhost"
        MYSQL_PORT = "31000"
        MYSQL_DB = "genpact"
        MYSQL_USER = "root"
        MYSQL_PASSWORD = "root"
    }
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker Image...'
                    bat 'docker build -t studentapp .'
                }
            }
        }
        stage('Set Kubernetes Context') {
            steps {
                script {
                    echo 'Switching Kubernetes Context to docker-desktop...'
                    bat "kubectl config use-context ${KUBE_CONTEXT}"  // Switch to the right context
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    bat 'kubectl apply -f deployment.yml'  // Apply the deployment.yaml to Kubernetes
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
}
