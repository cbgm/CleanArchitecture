package com.example.christian.cleantest.data.repository

import android.content.Context
import com.example.christian.cleantest.core.util.ImageUtil
import com.example.christian.cleantest.domain.model.License
import com.example.christian.cleantest.domain.repository.LicenseRepository
import io.reactivex.Single
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LicenseRepositoryImpl @Inject constructor(val context: Context) : LicenseRepository {

    @Inject
    lateinit var imageUtil: ImageUtil

    override fun getLicenses(carId: String): Single<List<License>> {
        return Single.just(loadLicenseFiles(carId))
    }

    private fun loadLicenseFiles(carId: String): List<License> {
        //TODO Request Permission when onClick
        return if (imageUtil.licensesPathExists(carId)) {
            return File(imageUtil.getLicensesPath(carId)).list().map { License( carId,it ) }.toList()
        } else {
            emptyList()
        }
    }
}