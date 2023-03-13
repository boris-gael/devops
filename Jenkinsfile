pipeline {
    agent any
    tools {
        maven 'maven-9'
    }
    environment {
        registry = 'ndongboris/devops' //'430965556548.dkr.ecr.eu-west-3.amazonaws.com/devops'
        registryCredentials = 'jenkins-dockerhub-credentials'
        dockerImage = ''
        profile = 'dev'
    }
    stages {
        stage("Checkout project") {
            steps {
                git branch: 'master', credentialsId: 'jenkins-git-credentials', url: 'https://github.com/boris-gael/devops.git'
            }
        }
        stage("Build package") {
            steps {
                sh 'mvn clean package -P+profile'
            }
        }
        stage("Sonarqube analysis") {
            steps {
                withSonarQubeEnv(installationName: 'sonar-9', credentialsId: 'jenkins-sonarcloud-token') {
                    sh 'mvn sonar:sonar -Dsonar.organization=sonarcloudorganizationkey12345 -Dsonar.projectKey=boris-gael_devops'
                }
            }
        }
        // stage("Quality gate") {
        //     steps {
        //         script {
        //             timeout(time: 1, unit: 'MINUTES') {
        //                 def qg = waitForQualityGate()
        //                 if (qg.status != 'NONE') {
        //                     error "Pipeline aborted due to quality gate failure: ${qg.status}"
        //                 }
        //             }
        //         }
        //     }
        // }
        stage("Build docker image") {
            steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
            }
        }
        stage("Deploy image to Amazon ECR") {
            steps {
                script {
                    docker.withRegistry(/*"https://" + registry*/ '', registryCredentials) {
                        dockerImage.push()
                        dockerImage.push("latest")
                    }
                }
            }
        }
    }
    post {
        success {
            mail bcc: '', body: 'Devops Pipeline build successfully!', cc: '', from: 'ndongboris92@gmail.com', replyTo: '', subject: 'devops CI/CD status', to: 'ndongboris92@gmail.com'
        }
        failure {
            mail bcc: '', body: 'Devops Pipeline build has failed!', cc: '', from: 'ndongboris92@gmail.com', replyTo: '', subject: 'devops CI/CD status', to: 'ndongboris92@gmail.com'
        }
    }
}