package br.com.viavarejo.jenkins.stages.git


class GitVersion {

    static def use(
            GitVersionParameters parameters = new GitVersionParameters(
                    credentialsId: 'git',
                    userEmail: 'jenkins@jenkins.com.br',
                    userName: "Jenkins"
            ),
            Closure shouldRun = { env.SHOULD_DEPLOY }) {
        return [
                run      : {
                    withCredentials([usernamePassword(credentialsId: parameters.credentialsId, passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                        sh "git config --global user.email '${parameters.userEmail}'"
                        sh "git config --global user.name '${parameters.userName}'"
                        sh "git add -A"
                        sh "git commit --no-verify -m '${env.VERSION} [skip ci]'"
                        sh "git tag -a ${env.VERSION} -f -m '${env.VERSION} [skip ci]'"
                        sh "git push http://${GIT_USERNAME}:${GIT_PASSWORD}@${env.PROJECT_REPO} --tags"
                        sh "git push http://${GIT_USERNAME}:${GIT_PASSWORD}@${env.PROJECT_REPO} HEAD:${env.BRANCH_NAME}"
                    }
                },
                shouldRun: shouldRun
        ]
    }
}

class GitVersionParameters {
    String credentialsId
    String userEmail
    String userName
}
