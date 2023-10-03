package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDb.Companion.dsl
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DefaultTargetFeatureTest {

    @Test
    fun `Default settings check`() {
        dsl {}.use {
            assertThat(it.effectiveSettings.jdbcUrl)
                .describedAs("default target database is HSQLDB")
                .isEqualTo("jdbc:hsqldb:mem:testdb")
        }
    }
}
