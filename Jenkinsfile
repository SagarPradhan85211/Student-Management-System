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
                    sh 'docker build -t studentapp .'
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    echo 'Running Docker Container...'
                    sh '''#!/bin/bash
                    docker run -d -p 8081:8081 \
                        -e MYSQL_HOST=$MYSQL_HOST \
                        -e MYSQL_PORT=$MYSQL_PORT \
                        -e MYSQL_DB=$MYSQL_DB \
                        -e MYSQL_USER=$MYSQL_USER \
                        -e MYSQL_PASSWORD=$MYSQL_PASSWORD \
                        studentapp
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