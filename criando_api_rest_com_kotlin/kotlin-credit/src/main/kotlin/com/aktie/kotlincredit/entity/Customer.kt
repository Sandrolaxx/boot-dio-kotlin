package com.aktie.kotlincredit.entity

import jakarta.persistence.*

@Entity
@Table(name = "KT_CUSTOMER")
class Customer {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    var firstName: String = ""

    @Column(nullable = false)
    var lastName: String = ""

    @Column(nullable = false)
    val cpf: String

    @Column(nullable = false, unique = true)
    var email: String = ""

    @Column(nullable = false, unique = true)
    var password: String = ""

    @Embedded
    var address: Address = Address()

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
    var credits: List<Credit>

    constructor(cpf: String) {
        this.cpf = cpf
        this.address = Address()
        this.credits = mutableListOf()
    };
}
