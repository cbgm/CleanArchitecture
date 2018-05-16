package com.example.christian.cleantest.domain.repository

import com.example.christian.cleantest.domain.model.License
import io.reactivex.Single


interface LicenseRepository {
    fun getLicenses(): Single<List<License>>
}