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

node('loopback'){
    stage('setup'){
        checkout scm
        props = readJSON file: propertiesFile
        imageName = props.imageName
        imageGroup = props.imageGroup
        imageTag = "${env.BUILD_NUMBER}"
        jenkinsDeployJob = props.jenkinsDeployJob
        buildNode = props.buildNode
        unitTestCmd = props.unitTestCmd
        unitTestOutFile = props.unitTestOutFile
        unitTestFailThreshold = props.unitTestFailThreshold
        unitTestCoverageOutFile = props.unitTestCoverageOutFile
        unitTestCoverageOutDir = props.unitTestCoverageOutDir
        containerEnvironmentTag = props.containerEnvironmentTag
        containerRegistry = props.containerRegistry
    }
}

try {
    node(buildNode) {
        stage('build image') {
        checkout scm
          //  sh ("cp ci/container/Dockerfile .")
            sh ("docker build -t ${imageName} --pull ." )
            image = docker.image("${imageName}")
        }

        stage('unit tests') {
            if( unitTestCmd != null ) {
                image.inside {
                    sh(unitTestCmd)
                }
            }
        }
    }
} catch(RuntimeException e) {
    if(e.getMessage() != 'Some tests failed' && e.getMessage() != 'Some tests returned errors') {
        throw new RuntimeException(e)
    } catch(Exception e) {
        throw new Exception(e)
    } finally {
        // put some notifications in here
    }
}