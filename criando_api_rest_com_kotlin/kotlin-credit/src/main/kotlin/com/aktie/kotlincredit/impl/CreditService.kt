package com.aktie.kotlincredit.impl

import com.aktie.kotlincredit.entity.Credit
import com.aktie.kotlincredit.repositories.ICreditRepository
import com.aktie.kotlincredit.service.ICreditService
import com.aktie.kotlincredit.service.ICustomerService
import java.util.*

class CreditService(
    private val creditRepo: ICreditRepository,
    private val customerService: ICustomerService
) : ICreditService {
    override fun save(credit: Credit): Credit {
        credit.customer?.apply { customerService.findById(id!!) }

        if (credit.customer == null) {
            throw RuntimeException("Usuário não identificado para criação do emprestimo")
        }

        return creditRepo.save(credit)
    }

    override fun findAllByCustomerId(customerId: Long): List<Credit> {
        return creditRepo.findAllByCustumerId(customerId)
    }

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = creditRepo.findByCreditCode(creditCode) ?: throw RuntimeException("Crédito não encontrado")

        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Não é possível acessar essa informação")
    }
}