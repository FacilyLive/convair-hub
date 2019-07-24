package br.com.viavarejo.jornada.jenkins.stages.maven

class MavenSetVersionParameters {
    String maven
    String mavenSettingsConfig
}

class MavenSetVersion {

    static def use(
            MavenSetVersionParameters parameters = new MavenSetVersionParameters(
                    maven: 'M3',
                    mavenSettingsConfig: 'maven-settings'
            ),
            Closure shouldRun = { env.SHOULD_DEPLOY }) {
        return [
                run      : {
                    withMaven(maven: parameters.maven, mavenSettingsConfig: parameters.mavenSettingsConfig) {
                        sh "mvn versions:set -DnewVersion=${env.VERSION} -DgenerateBackupPoms=false"
                    }
                },
                shouldRun: shouldRun
        ]
    }
}

