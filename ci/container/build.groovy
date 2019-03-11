#!groovy

def imageName
def jenkinsDeployJob
def imageTag
def BuildNode
def unitTestCmd
def unitTestFailThreshold
def unitTestCoverageOutfile
def unitTestCoverageOutdir
def image
def imageGroup
def propertiesFile = "ci/config/build/config.json"
def containerRegistry

node {
    stage('setup'){
        checkout scm
        props = readJSON file: propertiesFile
        imageName = props.imageName
        imageGroup = props.imageGroup
        imageTag = "${env.BUILD_NUMBER}"
        jenkinsDeployJob = props.jenkinsDeployJob
       // buildNode = props.buildNode
        unitTestCmd = props.unitTestCmd
        unitTestOutFile = props.unitTestOutFile
        unitTestFailThreshold = props.unitTestFailThreshold
        unitTestCoverageOutFile = props.unitTestCoverageOutFile
        unitTestCoverageOutDir = props.unitTestCoverageOutDir
        containerEnvironmentTag = props.containerEnvironmentTag
        containerRegistry = props.containerRegistry
    }
}

// try {
    node('docker') {
        stage('build image') {
        checkout scm
            image = docker.build("${imageName}:${env.BUILD_ID}", "--pull .")
        }

        stage('unit tests') {
            if( unitTestCmd != null ) {
            print('unit test cmd: '+unitTestCmd)
               image.inside {
                  sh(unitTestCmd)
                  print('unit test cmd inside the docker container : '+unitTestCmd)
                }
            }
        }
    }
/*} catch(RuntimeException e) {
    if(e.getMessage() != 'Some tests failed' && e.getMessage() != 'Some tests returned errors') {
        throw new RuntimeException(e);
    } catch(Exception e) {
        throw new Exception(e);
    } finally {
        // put some notifications in here
    }
} */