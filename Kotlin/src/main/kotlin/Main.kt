import java.io.InputStream
import java.io.PrintStream

fun main() {
    val reader = FastReader()
    val writer = FastWriter()


}

class FastReader(private val stream: InputStream = System.`in`) {
    private val buffer = ByteArray(1024 * 16)
    private var size = 0
    private var pos = 0
    private var isEnded = false

    private fun checkAndReplenishment() {
        if (isEnded || size > pos) return

        size = stream.read(buffer, 0, buffer.size)
        pos = 0
        if (size < 0) isEnded = true
    }

    fun readByte(): Byte {
        checkAndReplenishment()
        return buffer[pos++]
    }

    fun read(): String {
        val bytes = mutableListOf<Byte>()

        while (true) {
            val byte = readByte()

            if (byte == SPACE || byte == LINE_FEED) break
            bytes.add(byte)
        }

        return bytes.toByteArray().toString(Charsets.UTF_8)
    }

    fun readln(): String {
        val bytes = mutableListOf<Byte>()

        while (true) {
            val byte = readByte()

            if (byte == LINE_FEED) break
            bytes.add(byte)
        }

        return bytes.toByteArray().toString(Charsets.UTF_8)
    }

    fun skip(i: Int = 1): FastReader {
        repeat(i) {
            while (true) {
                val byte = readByte()

                if (byte == LINE_FEED || byte == SPACE) break
            }
        }

        return this
    }

    fun skipln(i: Int = 1): FastReader {
        repeat(i) {
            while (readByte() != LINE_FEED) {}
        }

        return this
    }

    fun readChar(): Char = read()[0]
    fun readInt(): Int = read().toInt()
    fun readLong(): Long = read().toLong()
    fun readFloat(): Float = read().toFloat()
    fun readDouble(): Double = read().toDouble()
    fun readBoolean(): Boolean = read().toBoolean()
    fun readList(): List<String> = readln().split(" ")
    fun readChars(): List<Char> = readln().split(" ").map { it[0] }
    fun readInts(): List<Int> = readln().split(" ").map { it.toInt() }
    fun readInts(adjust: Int): List<Int> = readln().split(" ").map { it.toInt() + adjust }
    fun readLongs(): List<Long> = readln().split(" ").map { it.toLong() }
    fun readLongs(adjust: Long): List<Long> = readln().split(" ").map { it.toLong() + adjust }
    fun readFloats(): List<Float> = readln().split(" ").map { it.toFloat() }
    fun readDoubles(): List<Double> = readln().split(" ").map { it.toDouble() }
    fun readBooleans(): List<Boolean> = readln().split(" ").map { it.toBoolean() }

    companion object {
        private const val SPACE = ' '.code.toByte()
        private val LINE_FEED = '\n'.code.toByte()
    }
}

class FastWriter(private val stream: PrintStream = System.out, private val capacity: Int = 1024 * 1024 * 8) {
    private val writeText = StringBuilder()

    fun append(any: Any? = ""): FastWriter {
        writeText.append(any)
        checkCapacity()
        return this
    }

    inline fun appendYN(b: Boolean): FastWriter {
        return if (b)
            this.append("Yes")
        else
            this.append("No")
    }

    fun appendln(any: Any? = ""): FastWriter {
        writeText.append(any).append(System.lineSeparator())
        checkCapacity()
        return this
    }

    fun print() {
        stream.print(writeText)
        writeText.clear()
    }

    fun println() {
        stream.println(writeText)
        writeText.clear()
    }

    private fun checkCapacity() {
        if (writeText.length > capacity) print()
    }
}