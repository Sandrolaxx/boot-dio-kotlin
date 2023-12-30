package com.aktie.kotlincredit.controllers

import com.aktie.kotlincredit.dto.CustomerDTO
import com.aktie.kotlincredit.dto.CustomerEditDTO
import com.aktie.kotlincredit.dto.CustomerViewDTO
import com.aktie.kotlincredit.impl.CustomerService
import com.aktie.kotlincredit.mappers.CustomerMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customer")
class CustomerController(
    private val customerService: CustomerService
) {

    @PostMapping()
    fun register(@RequestBody customerDTO: CustomerDTO): ResponseEntity<String> {
        val customerEntity = CustomerMapper.toEntity(customerDTO)
        val savedCustomer = customerService.save(customerEntity)

        return ResponseEntity.ok("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping
    fun find(@RequestHeader id: Long): ResponseEntity<CustomerViewDTO> {
        val customerEntity = customerService.findById(id)

        return ResponseEntity.ok(CustomerMapper.toViewDTO(customerEntity))
    }

    @PatchMapping
    fun update(@RequestHeader id: Long, @RequestBody customerDTO: CustomerEditDTO): ResponseEntity<CustomerViewDTO> {
        val customerEntity = customerService.findById(id)

        customerService.save(CustomerMapper.toEntity(customerEntity, customerDTO))

        return ResponseEntity.ok(CustomerMapper.toViewDTO(customerEntity))
    }

    @DeleteMapping
    fun delete(@RequestHeader id: Long) = customerService.delete(id)
}