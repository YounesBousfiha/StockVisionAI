pipeline {
    agent any
    stages {
        stage('Check Docker') {
            steps {
                sh 'docker --version'
                echo 'Docker is running inside Jenkins! 🎉'
            }
        }
    }
}