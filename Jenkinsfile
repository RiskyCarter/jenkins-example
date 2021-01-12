def gv

pipeline {
    agent any

    stages {
        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
                echo 'Init..'
            }
        }
        stage('Build') {
            steps {
                script {
                    //gv.buildApp()
                }
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}