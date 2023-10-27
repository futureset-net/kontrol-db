package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbEngineBuilder.Companion.dsl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(DatabaseProvision::class)
internal class DefaultTargetFeatureTest {

    @Test
    fun `Default settings check`() {
        dsl {
        }.use {
            assumeTrue(it.effectiveSettings.databaseName == "hsqldb")
            assertThat(it.effectiveSettings.jdbcUrl)
                .describedAs("default target database is HSQLDB")
                .isEqualTo("jdbc:hsqldb:mem:testdb")
        }
    }
}
