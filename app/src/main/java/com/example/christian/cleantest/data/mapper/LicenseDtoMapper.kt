package com.example.christian.cleantest.data.mapper

import com.example.christian.cleantest.data.model.LicenseDto
import com.example.christian.cleantest.domain.model.License

class LicenseDtoMapper {
    companion object {
        fun transform(licenseDto: LicenseDto) : License {
            return License(licenseDto.carId ,licenseDto.name)
        }
    }
}