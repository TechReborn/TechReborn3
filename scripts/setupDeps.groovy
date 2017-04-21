task ("setupAll") {
    doLast {
        def cacheFiles = new File("Reborncore")
        if(!cacheFiles.exists()){
            logger.lifecycle(":Cloning RebornCore")
            exec 
            { 
                executable 'git' 
                args 'clone', 'https://github.com/TechReborn/RebornCore.git'
            }

            exec 
            {
                workingDir 'Reborncore'
                executable 'git' 
                args 'checkout', '1.11.2-rewrite'
            }
        } else {
            logger.lifecycle(":Pulling RebornCore")
            exec 
            {
                workingDir 'Reborncore'
                executable 'git' 
                args 'pull'
            }
        }
    idea.module.sourceDirs += sourceSets.RebornCore.java.srcDirs
    idea.module.sourceDirs += sourceSets.RebornCore.resources.srcDirs
    }
}


tasks.idea.dependsOn "setupDecompWorkspace"
setupAll.finalizedBy(tasks.idea)