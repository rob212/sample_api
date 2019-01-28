#!/groovy

def props
def env
def version
def isBuildAndDeploy
def branchBuildId
def workingBranch
def containerRegistryCredentials
def containerEnvironmentTag
def imageGroup
def scmUrl = scm.getUserRemoteConfigs()[0].getUrl()

if (params.BRANCH != null && params.ENVIRONMENT != null && params.BRANCH_BUILD_ID != null) {
    workingBranch = params.BRANCH
    env = params.ENVIRONMENT
    branchBuildId = params.BRANCHBUILD_ID
    isBuildAndDeploy= true
}