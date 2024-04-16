pipeline{
    agent any
    tools{
        maven "MAVEN"
    }
    stages{
        stage("checkout"){
            steps{
                git url:"https://github.com/PushpanderYadav/training-project"
            }
        }
            stage("compile"){
                steps{
                     sh 'mvn compile'
                }
            }
        }
    }
    
    
    
    
