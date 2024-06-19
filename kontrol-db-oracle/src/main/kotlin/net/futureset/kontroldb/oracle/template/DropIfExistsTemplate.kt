package net.futureset.kontroldb.oracle.template

import net.futureset.kontroldb.modelchange.DropIfExists
import net.futureset.kontroldb.settings.EffectiveSettings
import net.futureset.kontroldb.template.DbAwareTemplate
import net.futureset.kontroldb.template.SqlTemplate
import net.futureset.kontroldb.template.TemplatePriority
import org.koin.core.annotation.Singleton
import kotlin.reflect.KClass

@Singleton(binds = [SqlTemplate::class])
class DropIfExistsTemplate(db: EffectiveSettings) : DbAwareTemplate<DropIfExists>(db, TemplatePriority.DATABASE) {
    override fun type(): KClass<DropIfExists> = DropIfExists::class

    override fun convertToSingleStatement(change: DropIfExists): String {
        """DROP ${change.objectType} ${change.objectName.toSql()}""".let {
            return if (change.ifExists) {
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
