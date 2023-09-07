package net.futureset.kontroldb

import net.futureset.kontroldb.KontrolDbDsl.Companion.kontrolDb
import net.futureset.kontroldb.test.petstore.CreateCustomerTable
import net.futureset.kontroldb.test.petstore.CreateProductTable
import net.futureset.kontroldb.test.petstore.PetStore
import org.assertj.core.api.Assertions.assertThat
import org.koin.ksp.generated.module
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object PickupChangesViaScan : Spek({

    Feature("Can get migration changes from scan") {

        Scenario("Check migrations from Petstore module") {
            val result by memoized {
                kontrolDb {
                    changeModules(PetStore().module)
                }
            }
            When("Migration is created") {
                assertThat(result).isNotNull
            }
            Then("has picked up other refactorings") {
                assertThat(result.refactorings).hasSizeGreaterThan(1)
                assertThat(result.refactorings).hasAtLeastOneElementOfType(CreateProductTable::class.java)
                assertThat(result.refactorings).hasAtLeastOneElementOfType(CreateCustomerTable::class.java)
            }
        }
    }
})
