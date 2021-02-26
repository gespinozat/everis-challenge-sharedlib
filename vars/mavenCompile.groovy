def call(givenConfig = [:]) {
    
    def defaultConfig = [
        "maven": [
            "skipTests": true
        ]
    ]

    def effectiveConfig = defaultConfig + givenConfig
    
    node {        

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