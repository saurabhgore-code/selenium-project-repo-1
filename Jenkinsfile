podTemplate(containers: [
    containerTemplate(name: 'selenium-server', image: "(markhobson/maven-chrome:jdk-11)", command: 'mvn', args: 'clean', install’),
  ] , volumes: [
        persistentVolumeClaim(mountPath: ‘/usr/src’, claimName: ‘selenium-source-pvc’, readOnly: false)
                  ]
       volumes: [
       persistentVolumeClaim(mountPath: ‘/root/.m2’, claimName: ‘selenium-conf-pvc’, readOnly: false)
                  ] )
              {
  
node(POD_LABEL) {
        stage('Get a Maven project') {
            git 'https://github.com/jenkinsci/kubernetes-plugin.git'
            container('selenium-server') {
                stage('run maven goals') {
                    sh 'mvn -B -ntp clean install'
                }
            }
        }
        }
}
         
                
