pipeline {
    agent any
    environment {
        KUBE_CONTEXT = "docker-desktop"  // Set the Kubernetes context for Docker Desktop
        MYSQL_HOST = "localhost"
        MYSQL_PORT = "31000"
        MYSQL_DB = "genpact"
        MYSQL_USER = "root"
        MYSQL_PASSWORD = "root"
        KUBECONFIG = "/c/Users/850075939/.kube/config"  // Explicitly set the kubeconfig path
    }
    stages {
        stage('Check KubeConfig Path') {
            steps {
                script {
                    echo "KUBECONFIG is set to: ${env.KUBECONFIG}"
                }
            }
        }
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
                    bat "kubectl --kubeconfig=C:/Users/850075939/.kube/config config use-context docker-desktop"  // Verbose output for debugging
    
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    bat "kubectl --kubeconfig=C:/Users/850075939/.kube/config apply -f deployment.yml"

                    echo 'deleting the existing Pods...'
                    bat  "kubectl --kubeconfig=C:/Users/850075939/.kube/config delete deployment myapp"

                    echo 'Again Deploying to Kubernetes...'
                    bat "kubectl --kubeconfig=C:/Users/850075939/.kube/config apply -f deployment.yml"

                    echo 'Port Forwarding to 8081'
                    bat "kubectl --kubeconfig=C:/Users/850075939/.kube/config port-forward svc/serviceapp 8081:8081"
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
            cleanWs()  // Clean up workspace after the pipeline runs
        }
    }
}
