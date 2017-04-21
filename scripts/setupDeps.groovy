task ("setupDeps") {
    def cacheFiles = new File("Reborncore")
    if(!cacheFiles.exists()){
        logger.lifecycle(":Cloning RebornCore")
    } else {
        logger.lifecycle(":Pulling RebornCore")
    }
    
}