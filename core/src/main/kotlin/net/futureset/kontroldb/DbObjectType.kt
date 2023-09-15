package net.futureset.kontroldb

enum class DbObjectType {

    TABLE,
    SEQUENCE,
    INDEX,
    FUNCTION,
    PROCEDURE,
    TRIGGER,
    ROLE,
    USER,
    VIEW,
    OTHER,
    PRIMARY_KEY,
    FOREIGN_KEY,
    CHECK,
    TABLESPACE,
    ;

    fun identifier(): String {
        return name.replace("_", " ")
    }
}
