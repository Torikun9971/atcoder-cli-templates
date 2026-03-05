import java.io.InputStream
import java.io.PrintWriter

fun main() {
    val reader = FastReader()
    val writer = StringBuilder()


}

// =======================
//     Reader / Writer
// =======================

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

    fun readLong(): Long {
        var minus = null as Boolean?
        var res = 0L

        while (true) {
            val byte = readByte()
            if (byte == EOF || byte == SPACE || byte == LINE_FEED) break

            if (minus == null) {
                if (byte == MINUS) {
                    minus = true
                    continue
                } else {
                    minus = false
                }
            }

            res *= 10
            res += byte - ZERO
        }

        if (minus == true) res = -res
        return res
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

    fun readInt(): Int = readLong().toInt()
    fun readFloat(): Float = read().toFloat()
    fun readDouble(): Double = read().toDouble()
    fun readBoolean(): Boolean = read().toBoolean()
    fun readArray(n: Int): Array<String> = Array(n) { read() }
    fun readInts(n: Int): IntArray = IntArray(n) { readInt() }
    fun readLongs(n: Int): LongArray = LongArray(n) { readLong() }
    fun readFloats(n: Int): FloatArray = FloatArray(n) { readFloat() }
    fun readDoubles(n: Int): DoubleArray = DoubleArray(n) { readDouble() }
    fun readBooleans(n: Int): BooleanArray = BooleanArray(n) { readBoolean() }

    companion object {
        private const val EOF = (-1).toByte()

        private const val SPACE = ' '.code.toByte()
        private const val LINE_FEED = '\n'.code.toByte()

        private const val MINUS = '-'.code.toByte()
        private const val ZERO = '0'.code.toByte()
    }
}

val OutputWriter = PrintWriter(System.out, false)

@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.appendYesNo(b: Boolean): StringBuilder {
    return this.append(if (b) "Yes" else "No")
}

@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.print(writer: PrintWriter = OutputWriter) {
    writer.print(this)
    writer.flush()
    this.clear()
}

@Suppress("NOTHING_TO_INLINE")
inline fun StringBuilder.println(writer: PrintWriter = OutputWriter) {
    writer.println(this)
    writer.flush()
    this.clear()
}