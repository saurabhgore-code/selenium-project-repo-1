podTemplate(containers: [
    containerTemplate(name: 'selenium-server', image: "(markhobson/maven-chrome:jdk-11)", command: 'sleep', args: '99d')
  ], volumes: [
        persistentVolumeClaim(mountPath: ‘/usr/src’, claimName: ‘selenium-source-pvc’, readOnly: false)
       ])
              {
  
node(POD_LABEL) {
        stage('Get a Maven project') {
            git 'https://github.com/saurabhgore-code/selenium-project-repo-1.git'
            container('selenium-server') {
                stage('run maven goals') {
                    sh 'mvn -B -ntp clean install' 
                }
            }
        }
        }
}
