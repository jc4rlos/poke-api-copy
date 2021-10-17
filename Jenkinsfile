pipeline {
    agent any

    stages {
		stage ("build") {
        	steps {
            
			 echo 'Compile project'
    sh "chmod +x gradlew"
    sh "./gradlew clean build --no-daemon"
            }
        }
    }
}
