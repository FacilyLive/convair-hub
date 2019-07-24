package br.com.viavarejo.jornada.jenkins.stages.maven

class MavenBuildParameters {
    String maven
    String mavenSettingsConfig
}

class MavenBuild {

    static def use(
            MavenBuildParameters parameters = new MavenBuildParameters(
                    maven: 'M3',
                    mavenSettingsConfig: 'maven-settings'
            ),
            Closure shouldRun = { true }
    ) {
        return [
                run      : {
                    withMaven(maven: parameters.maven, mavenSettingsConfig: parameters.mavenSettingsConfig) {
                        sh 'mvn clean package -DskipTests'
                    }
                },
                shouldRun: shouldRun
        ]
    }
}
