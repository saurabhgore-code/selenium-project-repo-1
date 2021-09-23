podTemplate(containers: [
  containerTemplate(name: 'selenium-server', image: "(markhobson/maven-chrome:jdk-11)" , command: 'mvn', args: 'clean', 'install')
  ], volumes: [
  persistentVolumeClaim(mountPath: '/usr/src', claimName: 'selenium-source-pvc', readOnly: false)
  ]) {

  node(POD_LABEL) {
    stage('Build a Maven project') {
      git 'https://github.com/saurabhgore-code/selenium-project-repo-1.git'
      container('selenium-server') {
        sh 'mvn -B -ntp clean package -DskipTests'
      }
    }
  }
}
