import net.futureset.kontroldb.DbDockerExtension

plugins {
    `dokka-convention`
    id("kontrol-db.database.setup")
    id("kontrol-db.docker")
}

extensions.configure<DbDockerExtension> {
    imageId.set("mcr.microsoft.com/mssql/server@sha256:c1aa8afe9b06eab64c9774a4802dcd032205d1be785b1fd51e1c0151e7586b74")
    envProperties.put("ACCEPT_EULA", "Y")
    envProperties.put("MSSQL_SA_PASSWORD", "Th1sIsW0rking")
    internalToExternalPortMap.put(1433, 6283)
    waitForStartupLogMessage.set("SQL Server is now ready for client connections")
}

description = "sqlserver extensions for kontrol-db"

dependencies {
    api(libs.sqlserver)
}
