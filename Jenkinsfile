pipeline {
  agent {
    kubernetes {
      label 'pod-label'  // all your pods will be named with this prefix, followed by a unique id
      idleMinutes 5  // how long the pod will live after no jobs have run on it
      yamlFile 'selenium-maven-pod.yml'  // path to the pod definition relative to the root of our project 
        // define a default container if more than a few stages use it, will default to jnlp container
    }
  }
  stages {
    stage('Build') {
      steps {  // no container directive is needed as the maven container is the default
        sh "mvn clean install"   
      }
    }
  }
