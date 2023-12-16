package net.futureset.kontroldb

import net.futureset.kontroldb.config.ConfigFileControl
import net.futureset.kontroldb.config.KontrolDbConfig
import net.futureset.kontroldb.settings.ExecutionSettings
import net.futureset.kontroldb.settings.TargetSettingsBuilder
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import java.nio.file.Paths
import java.sql.Connection
import java.sql.DriverManager

class DatabaseProvision : BeforeEachCallback, AfterEachCallback, ParameterResolver {

    val config: KontrolDbConfig by lazy {
        ConfigFileControl().configFile(
            Paths.get("test-config.yml"),
            KontrolDbConfig(
                dialect = "default",
                modules = listOf(),
                executionSettings = ExecutionSettings(),
                targetSettings = TargetSettingsBuilder().build(),
            ),
        )
    }
    val dialect: String by lazy {
        config.dialect ?: throw IllegalStateException("cannot load dialect")
    }

    private val connectionHolder = ConnectionHolder(this::connection)
    private fun executeSql(vararg sql: String) {
        connectionHolder.withConnection {
            sql.forEach { sql -> it.executeSql(sql) }
        }
    }

    private fun connection(): Connection {
        return when (dialect) {
            "hsqldb" -> DriverManager.getConnection("jdbc:hsqldb:mem:testdb", "sa", "")
            "postgres" -> DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "SA", "Th1sIsW0rking")
            "sqlserver" -> DriverManager.getConnection(
                "jdbc:sqlserver://localhost:6283;trustServerCertificate=true",
                "SA",
                "Th1sIsW0rking",
            )

            else -> throw IllegalStateException("cannot provision $dialect")
        }
    }

    override fun afterEach(context: ExtensionContext?) {
        when (dialect) {
            "sqlserver" -> executeSql(
                """
                        IF EXISTS (SELECT * FROM master.sys.databases WHERE name = 'TEST_DB')
                        BEGIN
                            USE master;
                            ALTER DATABASE [TEST_DB] SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
                            DROP DATABASE [TEST_DB];
                            DROP LOGIN [deploymentUser];  
                        END
         """,
            )
            "postgres" -> executeSql(
                "DROP DATABASE \"TEST_DB\"",
                "DROP TABLESPACE \"MY_INDEX_TS\"",
                "DROP USER \"deploymentUser\"",
            )
            "hsqldb" -> executeSql("SHUTDOWN")
            else -> println("No way to provision $dialect")
        }
        connectionHolder.close()
    }

    override fun beforeEach(context: ExtensionContext?) {
        when (dialect) {
            "sqlserver" -> executeSql(
                "CREATE DATABASE [TEST_DB];",
                "CREATE LOGIN [deploymentUser] WITH PASSWORD = 'APasswordForTesting123', DEFAULT_DATABASE=[TEST_DB]",
                "ALTER AUTHORIZATION ON DATABASE::[TEST_DB] TO [deploymentUser]",
            )
            "postgres" -> executeSql(
                """CREATE DATABASE "TEST_DB"""",
                """CREATE USER "deploymentUser" WITH PASSWORD 'sfnisdofskonm3'""",
                """CREATE TABLESPACE "MY_INDEX_TS" OWNER "deploymentUser" LOCATION '/var/lib/postgresql/data'""",
                """GRANT ALL PRIVILEGES on database "TEST_DB" to "deploymentUser"""",
                """ALTER DATABASE "TEST_DB" OWNER TO "deploymentUser"""",
                """ALTER USER "deploymentUser" CREATEROLE""",
//                """GRANT ALL ON SCHEMA "public" TO "deploymentUser"""",
            )
            else -> println("No DB provision log for $dialect")
        }
    }

    override fun supportsParameter(parameterContext: ParameterContext?, extensionContext: ExtensionContext?): Boolean {
        return when {
            parameterContext?.parameter?.type == ConnectionHolder::class.java -> return true
            parameterContext?.parameter?.type == String::class.java && parameterContext.isAnnotated(DialectName::class.java) -> return true
            parameterContext?.parameter?.type == KontrolDbConfig::class.java -> return true
            else -> false
        }
    }

    override fun resolveParameter(parameterContext: ParameterContext?, extensionContext: ExtensionContext?): Any {
        return when (parameterContext?.parameter?.type) {
            ConnectionHolder::class.java -> connectionHolder
            String::class.java -> dialect
            KontrolDbConfig::class.java -> config
            else -> throw UnsupportedOperationException("Cannot resolve parameter")
        }
    }
}
