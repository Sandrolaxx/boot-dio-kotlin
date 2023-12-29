package com.aktie.kotlincredit.repositories

import com.aktie.kotlincredit.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ICreditRepository: JpaRepository<Credit, Long>