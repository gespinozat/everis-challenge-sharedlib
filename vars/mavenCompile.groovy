def call(givenConfig = [:]) {
    
    def defaultConfig = [
        "repo": "https://github.com/gespinozat/everis-challenge-app.git"
        "maven": [
            "skipTests": true
        ]
    ]

    def effectiveConfig = defaultConfig + givenConfig
    
    node {
        stage('Checking files') {
            sh "ls"
            if (fileExists('pom.xml')) {
                echo "POM files exists"
            }
        }
        

        if (effectiveConfig.maven.skipTests) {
            stage("Clean and build skipping tests") {
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        } else {
            stage("Clean and build") {
                sh "mvn -DskipTests clean package"
            }
        }
    }
}