package br.com.viavarejo.jornada.jenkins.stages.openshift

class OCTagImageParameters {
    String cluster
    String namespace
    String fromImage
    String toImage
}
/**
 * This Stage tags an Image using Openshift
 * Depends on Openshift Jenkins Plugin -> https://github.com/openshift/jenkins-client-plugin
 */
class OCTagImage {

    static def use(OCTagImageParameters parameters, Closure shouldRun = { env.SHOULD_DEPLOY }){
        return [
                run: {

                        openshift.withCluster(parameters.cluster) {
                            openshift.withProject("${parameters.namespace ?: env.NAMESPACE}") {
                                openshift.tag(parameters.fromImage, parameters.toImage)
                            }
                        }

                },
                shouldRun: shouldRun
        ]
    }
}
