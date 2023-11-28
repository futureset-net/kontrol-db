
import net.futureset.kontroldb.DbDockerExtension

plugins {
    id("kontrol-db.database.setup")
    id("kontrol-db.docker")
}

extensions.configure<DbDockerExtension> {
    imageId.set("mcr.microsoft.com/mssql/server:2022-latest")
    envProperties.put("ACCEPT_EULA", "Y")
    envProperties.put("MSSQL_SA_PASSWORD", "Th1sIsW0rking")
    internalToExternalPortMap.put(1433, 6283)
    waitForStartupLogMessage.set("SQL Server is now ready for client connections")
}

description = "sqlserver extensions for kontrol-db"

dependencies {
    api(libs.sqlserver)
}
