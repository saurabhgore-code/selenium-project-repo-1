podTemplate(containers: [
    containerTemplate(name: 'selenium-server', image: 'markhobson/maven-chrome:jdk-11', command: 'sleep', args: '99d'),
  ]) {

    node(POD_LABEL) {
        stage('Get a Maven project') {
            git 'https://github.com/saurabhgore-code/selenium-project-repo-1.git'
            container('maven') {
                stage('Build a Maven project') {
                    sh 'mvn -B -ntp clean install'
                }
            }
        }
    }
}
