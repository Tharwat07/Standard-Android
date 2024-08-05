package com.example.standardproject.utilities

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream
import java.util.concurrent.atomic.AtomicLong

class ProgressRequestBody(
    private val file: File,
    private val contentType: MediaType?,
    private val fileNumber: Int,
//    private val listener: UploadCallback
) : RequestBody() {

//    interface UploadCallback {
//        fun onProgressUpdate(percentage: Int, fileNumber: Int)
//    }

    override fun contentType(): MediaType? {
        return contentType
    }

    override fun contentLength(): Long {
        return file.length()
    }

    override fun writeTo(sink: BufferedSink) {
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileLength = file.length()
        var uploaded = 0L

        FileInputStream(file).use { inputStream ->
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                uploaded += read
                sink.write(buffer, 0, read)
//                listener.onProgressUpdate((100 * uploaded / fileLength).toInt(), fileNumber)
            }
        }
    }

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 2048
    }
}
