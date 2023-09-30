package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDb.Companion.dsl
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import net.futureset.kontroldb.test.petstore.CreateProductTable
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.koin.ksp.generated.module

internal class PickupChangesViaModuleTest {

    @Test
    fun `Can get migration changes from scan`() {
        val result = dsl {
            changeModules(PetStore().module)
        }

        assertThat(result.refactorings).hasSizeGreaterThan(1)
        assertThat(result.refactorings).hasAtLeastOneElementOfType(CreateProductTable::class.java)
        assertThat(result.refactorings).hasAtLeastOneElementOfType(CreateCustomerTable::class.java)
    }
}
