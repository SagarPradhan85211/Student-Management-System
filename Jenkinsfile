pipeline {
    agent any
    environment {
        MYSQL_HOST = "localhost"
        MYSQL_PORT = "31000"
        MYSQL_DB = "genpact"
        MYSQL_USER = "root"
        MYSQL_PASSWORD = "root"
        DOCKER_IMAGE_NAME = "studentapp"
        DOCKER_TAG = "latest"
        KUBE_CONFIG_PATH = "C:\\Users\\850075939\\.kube"  // Path to kubeconfig on Windows
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
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    bat """
                        kubectl --kubeconfig=%KUBE_CONFIG_PATH% apply -f kubernetes-deployment.yml
                    """
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
