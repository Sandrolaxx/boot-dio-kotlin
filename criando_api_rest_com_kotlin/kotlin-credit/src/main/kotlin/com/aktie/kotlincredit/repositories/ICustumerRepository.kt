package com.aktie.kotlincredit.repositories

import com.aktie.kotlincredit.entity.Custumer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ICustumerRepository: JpaRepository<Custumer, Long>