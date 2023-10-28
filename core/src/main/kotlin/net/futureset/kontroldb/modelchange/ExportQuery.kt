package net.futureset.kontroldb.modelchange

import net.futureset.kontroldb.Builder
import net.futureset.kontroldb.KontrolDbDslMarker
import net.futureset.kontroldb.ModelChange
import net.futureset.kontroldb.ModelChangesBuilder
import net.futureset.kontroldb.Resource.Companion.resource
import net.futureset.kontroldb.SupportsResultSetHandler
import net.futureset.kontroldb.settings.EffectiveSettings
import java.nio.file.Files
import java.sql.ResultSet

data class ExportQuery(
    val selectQuery: SelectQuery,
    val path: String?,
    val separator: String,
) : ModelChange, SupportsResultSetHandler {
    override fun resultSetHandler(effectiveSettings: EffectiveSettings): ((ResultSet) -> Unit)? {
        if (path == null) {
            return null
        } else {
            return { rs ->
                val headings = IntRange(1, rs.metaData.columnCount).map(rs.metaData::getColumnName)
                Files.newBufferedWriter(effectiveSettings.outputDirectory.resolve(path))
                    .use { file ->
                        file.write(headings.joinToString(separator = separator))
                        file.newLine()
                        generateSequence { if (rs.next()) headings.associateWith(rs::getString) else null }
                            .forEach {
                                file.write(it.values.joinToString(separator = separator))
                                file.newLine()
                            }
                    }
            }
        }
    }
}

@KontrolDbDslMarker
class ExportQueryBuilder : Builder<ExportQueryBuilder, ExportQuery> {

    private lateinit var selectQuery: SelectQuery
    private var path: String? = null
    private var separator: String = "|"

    fun query(lambda: SelectQuery.SelectQueryBuilder.() -> Unit) = apply {
        selectQuery = SelectQuery.SelectQueryBuilder().apply(lambda).build()
    }

    fun outputFile(path: String) {
        this.path = resource(path).path
    }
    fun separator(separator: String) = apply {
        this.separator = separator
    }
    override fun build(): ExportQuery =
        ExportQuery(selectQuery, path, separator)
}

fun ModelChangesBuilder.exportData(lambda: ExportQueryBuilder.() -> Unit) = apply {
    changes.add(ExportQueryBuilder().apply(lambda).build())
}
