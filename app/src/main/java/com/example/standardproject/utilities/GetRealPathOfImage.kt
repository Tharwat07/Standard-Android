package com.example.standardproject.utilities

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.lifecycle.MutableLiveData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

internal fun getRealPathOfImage(
    context: Context,
    uri: Uri,
): String? =
    if (uri.path?.contains("file://") == true) {
        uri.path
    } else {
        getFileFromContentUri(context, uri).path
    }

private val _fileName = MutableLiveData("")

@SuppressLint("SuspiciousIndentation")
private fun getFileFromContentUri(
    context: Context,
    contentUri: Uri,
): File {

    val fileExtension = getFileExtension(context, contentUri) ?: ""
    val fileName = "temp_file${System.currentTimeMillis()}.$fileExtension"
    val tempFile = File(context.cacheDir, fileName)
    tempFile.createNewFile()
    var oStream: FileOutputStream? = null
    var inputStream: InputStream? = null

    try {
        oStream = FileOutputStream(tempFile)
        inputStream = context.contentResolver.openInputStream(contentUri)
        inputStream?.let { copy(inputStream, oStream) }
        oStream.flush()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        // Close streams
        inputStream?.close()
        oStream?.close()
    }
    setFileName(fileName)
    Log.e("getFileFromContentUri: ", tempFile.toString())
    return tempFile
}

fun setFileName(name: String) {
    _fileName.value = name
}

private fun getFileExtension(context: Context, uri: Uri): String? =
    if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
        MimeTypeMap.getSingleton().getExtensionFromMimeType(context.contentResolver.getType(uri))
    } else {
        uri.path?.let { MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(File(it)).toString()) }
    }

@Throws(IOException::class)
private fun copy(source: InputStream, target: OutputStream) {
    val buffer = ByteArray(8192)
    var length: Int
    while (source.read(buffer).also { length = it } > 0) {
        target.write(buffer, 0, length)
    }
}


fun prepareFileParts(
    partName: String,
    fileRealPaths: List<String>,
    fileUris: List<Uri>,
    context: Context,
//    listener: ProgressRequestBody.UploadCallback?
): List<MultipartBody.Part> {
    val parts = mutableListOf<MultipartBody.Part>()
    for (i in fileRealPaths.indices) {
        val file = File(fileRealPaths[i])
        val requestFile = ProgressRequestBody(
            file,
            context.contentResolver.getType(fileUris[i])!!.toMediaTypeOrNull(),
            i + 1,
//            listener!!
        )
        parts.add(MultipartBody.Part.createFormData("$partName", file.name, requestFile))
    }
    return parts
}