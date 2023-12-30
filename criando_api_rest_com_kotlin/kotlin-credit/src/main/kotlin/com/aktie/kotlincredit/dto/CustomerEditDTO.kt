package com.aktie.kotlincredit.dto

import java.math.BigDecimal

data class CustomerEditDTO(
    val firstName: String?,
    val lastName: String?,
    val income: BigDecimal?,
    val zipCode: String?,
    val street: String?
)
