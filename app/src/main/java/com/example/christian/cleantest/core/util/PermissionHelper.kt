package com.example.christian.cleantest.core.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity

class PermissionHelper {

    companion object {

        fun hasWriteExternalStoragePermission(permission: String, context: Context) = Build.VERSION.SDK_INT < 23 ||
                (context as AppCompatActivity).checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

        fun getPermissionFromUser(permission: String, requestCode: Int, context: Context) {
            if (Build.VERSION.SDK_INT > 22) {
                (context as AppCompatActivity).requestPermissions(arrayOf(permission), requestCode)
            }
        }

    }
}