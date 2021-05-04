pipeline {
    agent any

    stages {
        stage('build') {
            steps {
                sh 'mvn -DskipTests clean compile'
            }
        }

        stage('test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('deliver') {
            steps {
                sh 'mvn -DskipTests install'
            }
        }
    }
}