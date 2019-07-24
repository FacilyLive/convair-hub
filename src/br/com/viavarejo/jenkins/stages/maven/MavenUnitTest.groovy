package br.com.viavarejo.jenkins.stages.maven


class MavenUnitTest {

    static def use(
            MavenUnitTestParameters parameters = new MavenUnitTestParameters(
                    maven: 'M3',
                    mavenSettingsConfig: 'maven-settings'
            ),
            Closure shouldRun = { true }) {
        return [
                run      : {
                    withMaven(maven: parameters.maven, mavenSettingsConfig: parameters.mavenSettingsConfig) {
                        sh 'mvn clean install -U'
                        sh 'mvn clean test'
                    }
                },
                shouldRun: shouldRun
        ]
    }
}
class MavenUnitTestParameters {
    String maven
    String mavenSettingsConfig
}
