podTemplate(containers: [
    containerTemplate(name: 'selenium-server', image: markhobson/maven-chrome:jdk-11, command: 'sleep', args: '99')
  ]) {

    node(POD_LABEL) {
        stage('Get a Maven project') {
            git 'https://github.com/jenkinsci/kubernetes-plugin.git'
            container('maven') {
                stage('Build a Maven project') {
                    sh 'mvn -B -ntp clean install'
                }
            }
        }
    }
} 
