pipeline {
    agent any

    stages {
		stage ("build") {
        	steps {
            	echo 'xdddddddddd'
			withGradle(){
				sh 'chmod +x gradlew'
			  sh './gradlew build'
			}
            }
        }
    }
}
