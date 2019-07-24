class ${NAME}Parameter {

}

class ${NAME} {
    static def use(
        ${NAME}Parameter parameters = new ${NAME}Parameter(),
        Closure shouldRun = { true }
    ){
        return [
            shouldRun: shouldRun,
            run: {
                println "Hello World"
            }            
        ]        
    }
}