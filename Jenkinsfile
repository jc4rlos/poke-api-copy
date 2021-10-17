pipeline {
    agent any

    stages {
	stage ("build") {
        	steps {
             	sh "chmod +x gradlew"
             	sh "./gradlew build"
            	}
        }
        stage ("test") {
        	steps {
             sh "chmod +x gradlew"
             sh "./gradlew test"
            }
        }
    }
}
