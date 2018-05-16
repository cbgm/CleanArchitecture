package com.example.christian.cleantest.presentation.licenseview.mapper

import com.example.christian.cleantest.domain.model.License
import com.example.christian.cleantest.presentation.licenseview.model.LicenseEntity

class LicenseDomainMapper {

    companion object {
        fun transform(license: License) : LicenseEntity {
            return LicenseEntity(license.name)
        }
    }
}