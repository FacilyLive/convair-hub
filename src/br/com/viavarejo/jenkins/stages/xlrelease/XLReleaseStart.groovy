package br.com.viavarejo.jenkins.stages.xlrelease
class XLReleaseStartParameters {
    String overrideCredentialId
    String serverCredentials
    String template
    String releaseTitle
    ArrayList<LinkedHashMap<String, String>> variables
    Boolean startRelease
}
/**
 * Invoke XL Release to Start a Release
 * This stage relies on XLRelease Plugin -> https://wiki.jenkins.io/display/JENKINS/XL+Release+Plugin
 */
class XLReleaseStart {
    static def use(
            XLReleaseStartParameters  parameters = new XLReleaseStartParameters(
                    overrideCredentialId: 'xebia_jenkins',
                    serverCredentials: 'xebia_jenkins',
                    template: '',
                    releaseTitle: '',
                    variables: [[:]],
                    startRelease: true

            ),
            Closure shouldRun = { env.SHOULD_DEPLOY }){
        return [
                run: {
                    xlrCreateRelease overrideCredentialId: parameters.overrideCredentialId,
                                     serverCredentials: parameters.serverCredentials,
                                     template: parameters.template,
                                     releaseTitle: parameters.releaseTitle,
                                     variables: parameters.variables,
                                     startRelease: parameters.startRelease
                },
                shouldRun: shouldRun
        ]
    }
}
