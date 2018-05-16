package com.example.christian.cleantest.data.repository

import com.example.christian.cleantest.domain.model.License
import com.example.christian.cleantest.domain.repository.LicenseRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LicenseRepositoryImpl @Inject constructor(): LicenseRepository {
    override fun getLicenses(): Single<List<License>> {
       return Single.just(null)
    }
}