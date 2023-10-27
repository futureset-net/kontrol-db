package net.futureset.kontroldb

enum class OperatingSystem(val lineSeparator: String) {
    WINDOWS("\r\n"),
    LINUX("\n"),
    MAC("\n"),
    AUTO(
        when {
            "win" in System.getProperty("os.name", "") -> WINDOWS.lineSeparator
            "mac" in System.getProperty("os.name", "") -> MAC.lineSeparator
            else -> LINUX.lineSeparator
        },
    ),
    ;

    companion object {
        fun current(): OperatingSystem =
            when {
                "win" in System.getProperty("os.name", "") -> WINDOWS
                "mac" in System.getProperty("os.name", "") -> MAC
                else -> LINUX
            }
    }
}
