package br.com.viavarejo.jenkins.stages.openshift

class OCCreateImageBuilderParameter {
    String clusterName
    String namespace
    String projectName
    String imageStream
    String buildType
}
/**
 * This Stage creates an ImageBuilder in Openshift
 * Depends on Openshift Jenkins Plugin -> https://github.com/openshift/jenkins-client-plugin
 */
class OCCreateImageBuilder {
    static def use(
            OCCreateImageBuilderParameter parameters = new OCCreateImageBuilderParameter(
                    imageStream: 'openshift/java:8',
                    buildType: '--binary'
            ),
            Closure shouldRun = { true }
    ) {
        return [
                shouldRun: shouldRun,
                run      : {
                    openshift.withCluster(parameters.clusterName) {
                        openshift.withProject(parameters.namespace ?: env.NAMESPACE) {
                            echo "Using project: ${openshift.project()}"
                            if (!openshift.selector("buildconfig", parameters.projectName ?: env.PROJECT_NAME).exists()) {
                                openshift.newBuild("--name=${parameters.projectName ?: env.PROJECT_NAME}", "--image-stream=${parameters.imageStream}", parameters.buildType)
                            }
                        }
                    }
                }
        ]
    }
}