pipeline {
    agent any
    environment {
        MYSQL_HOST = "localhost"
        MYSQL_PORT = "31000"
        MYSQL_DB = "genpact"
        MYSQL_USER = "root"
        MYSQL_PASSWORD = "root"
        KUBE_CONFIG = credentials('kube-config') // Jenkins credential ID for the kubeconfig
    }
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker Image...'
                    sh 'docker build -t studentapp .'
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo 'Deploying to Kubernetes...'
                    // Use the kubeconfig from Jenkins credentials to authenticate kubectl
                    sh '''#!/bin/bash
                    echo $KUBE_CONFIG > kubeconfig.yaml
                    export KUBECONFIG=$(pwd)/kubeconfig.yaml
                    kubectl apply -f deployment.yml --validate=false
                    '''
                }
            }
        }
        stage('Test Database Connection') {
            steps {
                script {
                    echo 'Testing Database Connection...'
                    sh '''#!/bin/bash
                    mysql -h $MYSQL_HOST -P $MYSQL_PORT -u $MYSQL_USER -p$MYSQL_PASSWORD -D $MYSQL_DB -e "SHOW TABLES;"
                    '''
                }
            }
        }
    }
}
