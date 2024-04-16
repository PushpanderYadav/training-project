pipeline{
    agent any
    tools{
        maven "MAVEN"
    }
    stages{
        stage("checkout"){
            steps{
                echo"this is branch ${params.choice}"
                git url:"https://github.com/PushpanderYadav/training-project",branch:"${params.choice}"
            }
        }
            stage("compile"){
                steps{
                     sh 'mvn compile'
                }
            }
        }
    }
    
    
    
    
