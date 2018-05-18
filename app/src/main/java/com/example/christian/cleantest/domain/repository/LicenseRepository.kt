package com.example.christian.cleantest.domain.repository

import com.example.christian.cleantest.domain.model.License
import io.reactivex.Single


interface LicenseRepository {
    fun getLicenses(carId: String): Single<List<License>>

    /*fun addLicense(carId: String, licenseDesc: String)

    fun deleteLicense(carId: String, licenseDesc: String)

    fun updateLicense(carId: String, licenseDesc: String)*/
}