package com.aktie.kotlincredit.controllers

import com.aktie.kotlincredit.dto.CreditDTO
import com.aktie.kotlincredit.dto.CreditResumeViewDTO
import com.aktie.kotlincredit.dto.CreditViewDTO
import com.aktie.kotlincredit.impl.CreditService
import com.aktie.kotlincredit.impl.CustomerService
import com.aktie.kotlincredit.mappers.CreditMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/credit")
class CreditController(
    private val creditService: CreditService,
    private val customerService: CustomerService
) {

    @PostMapping()
    fun create(@RequestBody creditDTO: CreditDTO): ResponseEntity<String> {
        val credit = CreditMapper.toEntity(creditDTO)

        val savedCredit = creditService.save(credit)

        return ResponseEntity.ok("Credit ${savedCredit.id} created!")
    }

    @GetMapping
    @RequestMapping("/all")
    fun findAllByCustomerId(@RequestHeader customerId: Long): ResponseEntity<List<CreditResumeViewDTO>> {
        val allCredits = creditService.findAllByCustomerId(customerId)
        val allCreditsDTO = allCredits.stream()
            .map(CreditMapper::toResumeViewDTO)
            .toList()

        return ResponseEntity.ok(allCreditsDTO)
    }

    @GetMapping
    fun findByCode(@RequestHeader customerId: Long, @RequestHeader creditCode: UUID): ResponseEntity<CreditViewDTO> {
        val credit = creditService.findByCreditCode(customerId, creditCode)

        return ResponseEntity.ok(CreditMapper.toViewDTO(credit))
    }

}