podTemplate(containers: [
  containerTemplate(name: 'selenium-maven', image: "markhobson/maven-chrome:jdk-11", command: 'sleep', args: '99d')
  ], volumes: [
  persistentVolumeClaim(mountPath: '/usr/src', claimName: 'selenium-source-pvc', readOnly: false)
  ]) {

  node(POD_LABEL) {
    stage('Build a Maven project') {
      git 'https://github.com/jenkinsci/kubernetes-plugin.git'
      container('maven') {
        sh 'mvn -B -ntp clean package -DskipTests'
      }
    }
  }
}
