package br.com.viavarejo.jenkins.stages.maven


class MavenSonar {

    static def use(
            MavenSonarParameters parameters = new MavenSonarParameters(
                    maven: 'M3',
                    mavenSettingsConfig: 'maven-settings',
                    springProfile: 'docker',
                    readPropertiesPath: 'target/sonar/report-task.txt'
            ),
            Closure shouldRun = { env.SHOULD_DEPLOY }) {
        return [
                run      : {
                    withMaven(maven: parameters.maven, mavenSettingsConfig: parameters.mavenSettingsConfig) {
                        sh "mvn clean package sonar:sonar -Dspring.profiles.active=${parameters.springProfile}"
                        script {
                            def props = readProperties file: parameters.readPropertiesPath
                            env.SONAR_CE_TASK_URL = props.ceTaskUrl
                            echo env.SONAR_CE_TASK_URL
                        }
                    }
                },
                shouldRun: shouldRun
        ]
    }
}
class MavenSonarParameters {
    String maven
    String mavenSettingsConfig
    String springProfile
    String readPropertiesPath
}
