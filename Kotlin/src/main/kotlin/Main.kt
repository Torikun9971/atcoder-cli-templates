import java.io.InputStream
import java.io.PrintWriter

fun main() {
    val reader = FastReader()
    val writer = StringBuilder()


}

class FastReader(
    private val stream: InputStream = System.`in`,
    bufferSize: Int = 1024 * 16
) {
    private val buffer = ByteArray(bufferSize)
    private var size = 0
    private var pos = 0
    var isEOF = false
        private set

    @Suppress("NOTHING_TO_INLINE")
    private inline fun replenish(): Boolean {
        if (size > pos) return true
        if (isEOF) return false

        size = stream.read(buffer, 0, buffer.size)
        pos = 0

        if (size < 0) {
            isEOF = true
            return false
        }

        return true
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun readByte(): Byte {
        if (!replenish()) return -1
        return buffer[pos++]
    }

    fun read(): String {
        var len = 0
        var bytes = ByteArray(32)

        while (true) {
            val byte = readByte()
            if (byte == EOF || byte == SPACE || byte == LINE_FEED) break

            if (len >= bytes.size) bytes = bytes.copyOf(len * 2)
            bytes[len++] = byte
        }

        return String(bytes, 0, len, Charsets.UTF_8)
    }

    fun readln(): String {
        var len = 0
        var bytes = ByteArray(32)

        while (true) {
            val byte = readByte()
            if (byte == EOF || byte == LINE_FEED) break

            if (len >= bytes.size) bytes = bytes.copyOf(len * 2)
            bytes[len++] = byte
        }

        return String(bytes, 0, len, Charsets.UTF_8)
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

    @Suppress("ControlFlowWithEmptyBody")
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
        private const val EOF = (-1).toByte()
        private const val SPACE = ' '.code.toByte()
        private val LINE_FEED = '\n'.code.toByte()
    }
}

val OUTPUT_WRITER = PrintWriter(System.out, false)

@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.appendYN(b: Boolean): StringBuilder {
    return this.append(if (b) "Yes" else "No")
}

@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.print(writer: PrintWriter = OUTPUT_WRITER) {
    writer.print(this)
    writer.flush()
    this.clear()
}

@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.println(writer: PrintWriter = OUTPUT_WRITER) {
    writer.println(this)
    writer.flush()
    this.clear()
}