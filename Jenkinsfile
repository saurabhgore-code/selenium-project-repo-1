podTemplate(containers: [
    containerTemplate(name: 'apache-container', image: 'httpd', command: 'sleep', args: '99d')
  ]) 
{
node(web-server) {
        stage('Get the project') {
            git 'https://github.com/saurabhgore-code/selenium-project-repo-1.git'
            container('apache') {
                stage('run apache container') {
                    sh 'echo my name is sagore'
                }
            }
        }
}
}
