package net.futureset.kontroldb.oracle.generator

import net.futureset.kontroldb.generator.DbAwareGenerator
import net.futureset.kontroldb.generator.SqlGenerator
import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import org.koin.core.annotation.Single

@Single(binds = [SqlGenerator::class])
class DropIfExistsGenerator(
    es: EffectiveSettings,
) : DbAwareGenerator<DropIfExists>(es, DropIfExists::class) {
    override fun convertSingle(): DropIfExists.() -> String? = {
        """DROP $objectType ${objectName.toQuoted()}""".let {
            if (ifExists) {
                """BEGIN
                                EXECUTE IMMEDIATE '$it';
                                EXCEPTION
                                WHEN OTHERS THEN
                                IF SQLCODE != -942 AND SQLCODE !=-4043 THEN
                                RAISE;
                                END IF;
                                END; """
            } else {
                it
            }
        }
    }
}
