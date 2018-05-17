package com.example.christian.cleantest.domain.usecases

import com.example.christian.cleantest.domain.model.License
import com.example.christian.cleantest.domain.repository.LicenseRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLicensesByCar @Inject constructor(private val licenseRepository: LicenseRepository): SingleUseCase<List<License>, String>() {
    override fun buildUseCaseObservable(param: String): Single<List<License>> {
        return licenseRepository.getLicenses(param)
    }
}