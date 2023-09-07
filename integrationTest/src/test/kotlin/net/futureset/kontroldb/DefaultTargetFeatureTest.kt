package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DefaultTargetFeatureTest {

    @Test
    fun `Default settings check`() {
        val result = kontrolDb {}

        assertThat(result.effectiveSettings.jdbcUrl)
            .describedAs("default target database is HSQLDB")
            .isEqualTo("jdbc:hsqldb:mem:testdb")
    }
}
