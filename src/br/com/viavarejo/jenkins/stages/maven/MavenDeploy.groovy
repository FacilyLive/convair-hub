package br.com.viavarejo.jenkins.stages.maven

class MavenDeployParameters {
    String maven
    String mavenSettingsConfig
}

class MavenDeploy {

    static def use(
            MavenDeployParameters parameters = new MavenDeployParameters(
                    maven: 'M3',
                    mavenSettingsConfig: 'maven-settings'
            ),
            Closure shouldRun = { env.SHOULD_DEPLOY }) {
        return [
                run      : {
                    withMaven(maven: parameters.maven, mavenSettingsConfig: parameters.mavenSettingsConfig) {
                        sh 'mvn deploy -DskipTests'
                    }
                },
                shouldRun: shouldRun
        ]
    }
}
