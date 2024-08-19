workspace {
    !identifiers hierarchical
     model {
        repository = softwareSystem "Architecture Repository" 
        
        userthing = person "Useropotomus"
        userthing -> repository "Uses"
        a = softwareSystem "A"
        b = softwareSystem "B"
        c = softwareSystem "C"
        d = softwareSystem "D"
        e = softwareSystem "E"

        b -> c
        b -> d
        b -> e
     }
}