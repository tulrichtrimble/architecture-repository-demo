


## Prep
 1. Add the ADR Tools to your path
    1. Using Windows WSL or Linux
        1. sudo vim ~/.bash_profile
        1. `i` to insert
        1. add the following to your profile `export PATH=$PATH:/mnt/c/Users/username/adr-tools/src` changing the path to the location of the adr-tools
        1. `esc` to exit insert mode
        1. `:wq` to save and exit
    1. `source ~/.bash_profile` to refresh your profile
    1. Create an ADR
        1. `adr new My Test ADR Title`

## Run Structurizr OnPrem
1. Install Tomcat
1. Stop Tomcat and delete the root web app. 
    1. In 1. C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps, rename structurizr-onprem.war to ROOT.warSee [here](https://docs.structurizr.com/onpremises/installation)
1. Open Tomcat default URL: http://localhost:8098 (I changed the default port from 8080)
1. Default creds `structurizr` : ``password`
1. OnPrem data directory is C:\usr\local\structurizr
1. Create a new workspace
    1. Create a new pw with http://localhost:8098/bcrypt/password
1. Add a bcrypt key to the `structurizr.apiKey=` field of structurizr.properties
    1. restart the tomcat server


## Run local instance of Structurizr Lite
1. In Powershell `java "-Djdk.util.jar.enableMultiRelease=false" -jar ".\bin\structurizr-lite.war" ".\arch-repository-workspace"`
2. Open browser to [http://127.0.0.1:8080/workspace/diagrams](http://127.0.0.1:8080/workspace/diagrams)

## Import the Backstage Catalog
1. in WSL 
    1. cd /mnt/c/Users/tulrich/work/lab/structurizr/structurizr-demo/examples/enterprise
    1. compile the examples `./gradlew compileJava` Creates the `/build` folder
    1. `./gradlew run -PmainClass=org.example.Example3`
1. in Powershell
    1. 
    1. `.\gradlew run "-PmainClass=org.example.Example3"`

## Walkthrough

1. Load the main System Context Diagram
1. Double