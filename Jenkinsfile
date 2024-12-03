pipeline {
    agent any
    environment {
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
                    bat 'docker build -t studentapp .'  // Use 'bat' for Windows
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    bat 'kubectl apply -f deployment.yml'  // Use 'bat' for Windows
                }
            }
        }
        stage('Test Database Connection') {
            steps {
                script {
                    echo 'Testing Database Connection...'
                    bat '''@echo off
                    mysql -h %MYSQL_HOST% -P %MYSQL_PORT% -u %MYSQL_USER% -p%MYSQL_PASSWORD% -D %MYSQL_DB% -e "SHOW TABLES;"  // Use batch script syntax for environment variables
                    '''
                }
            }
        }
    }
}
