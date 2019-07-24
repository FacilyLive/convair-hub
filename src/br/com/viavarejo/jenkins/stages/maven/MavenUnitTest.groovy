package br.com.viavarejo.jenkins.stages.maven

class MavenUnitTestParameters {
    String maven
    String mavenSettingsConfig
}

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
