package com.athena.photoedit

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

/**
 * A custom [FileProvider] to handle file URIs for image storage.
 */
class ComposeFileProvider : FileProvider() {
    companion object {
        /**
         * Creates a temporary image file in the cache directory and returns its URI.
         *
         * @param context The context used to access the cache directory.
         * @return The URI of the created temporary image file.
         */
        fun getImageUri(context: Context): Uri {
            // Create a directory named "images" in the cache directory
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()

            // Create a temporary file with a prefix "selected_image_" and suffix ".jpg" in the "images" directory
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory,
            )

            // Get the authority for the FileProvider
            val authority = context.packageName + ".fileprovider"

            // Return the URI for the created file
            return getUriForFile(
                context,
                authority,
                file,
            )
        }
    }
}