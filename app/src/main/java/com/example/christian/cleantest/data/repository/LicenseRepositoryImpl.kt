package com.example.christian.cleantest.data.repository

import android.content.Context
import android.os.Environment
import com.example.christian.cleantest.domain.model.License
import com.example.christian.cleantest.domain.repository.LicenseRepository
import io.reactivex.Single
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LicenseRepositoryImpl @Inject constructor(val context: Context): LicenseRepository {
    override fun getLicenses(carId: String): Single<List<License>> {
       return Single.just(loadLicenseFiles(carId))
    }

    private fun loadLicenseFiles(carId: String): List<License> {
        val path = "${context.filesDir.absolutePath}${File.separator}$carId${File.separator}licenses"
        return File(path)
                .walkTopDown()
                .map { License(it.path) }
                .toList()

    }
}