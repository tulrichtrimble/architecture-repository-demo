workspace extends "./shared-workspace.dsl" {
    model {
        !extend a {
            webapp = container "Web Application"
            database = container "Database"
            
            webapp -> b "Gets data X from"
            webapp -> database "Reads from and writes to"
        }
        !extend repository {
            !docs ./docs
            !adrs ./doc/adr
        }
    }

    views {
        systemContext repository "Tyler_diagram_test" {
            include *
        }

        dynamic * {
            a -> b "Makes a request to"
            {
                {
                    b -> c "Gets data from"
                }
                {
                    b -> d "Gets data from"
                }
            }
            b -> e "Sends data to"
        }
    }
}