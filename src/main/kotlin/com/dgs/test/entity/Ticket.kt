package com.dgs.test.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*


@Entity
data class Ticket(
//    val token: String = UUID.randomUUID().toString(),
//    @JsonProperty("ticket")
//    val seat: Seat?,
    @JsonIgnore
    var state: String? = "Sold",
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    open var token: String = UUID.randomUUID().toString(),

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "seat_id")
    open var seat: Seat? = null,

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    open var cinema: Cinema? = null
) {


    override fun toString(): String {
        return "Ticket(token='$token', state=$state)"
    }


}