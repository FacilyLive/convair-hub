package br.com.viavarejo.jenkins.stages.common

class NewStage {
    static def use(
            NewStageParameter parameters = new NewStageParameter(),
            Closure shouldRun = { true }
    ) {
        return [
                shouldRun: shouldRun,
                run      : {
                    println "Hello World"
                }
        ]        
    }
}

class NewStageParameter {

}
