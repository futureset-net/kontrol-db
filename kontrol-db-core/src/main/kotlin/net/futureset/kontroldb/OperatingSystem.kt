package net.futureset.kontroldb

enum class OperatingSystem(
    val lineSeparator: String,
) {
    WINDOWS("\r\n"),
    LINUX("\n"),
    MAC("\n"),
    AUTO(
        when {
            "win" in System.getProperty("os.name", "").lowercase() -> WINDOWS.lineSeparator
            "mac" in System.getProperty("os.name", "").lowercase() -> MAC.lineSeparator
            else -> LINUX.lineSeparator
        },
    ),
    ;

    companion object {
        fun current(): OperatingSystem = when {
            "win" in System.getProperty("os.name", "").lowercase() -> WINDOWS
            "mac" in System.getProperty("os.name", "").lowercase() -> MAC
            else -> LINUX
        }
    }
}
