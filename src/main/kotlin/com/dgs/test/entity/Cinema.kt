package com.dgs.test.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.hibernate.Hibernate
import kotlin.jvm.Transient

const val SEAT_ROWS = 9
const val SEAT_COLS = 9

@Entity
data class Cinema(
    @JsonProperty("total_rows") val numRows: Int = SEAT_ROWS,

    @JsonProperty("total_columns") val numCol: Int = SEAT_COLS,

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    @OneToMany(mappedBy = "cinemaId", cascade = [CascadeType.ALL], orphanRemoval = true)
    open var seats: MutableSet<Seat> = mutableSetOf(),

    @OneToMany(mappedBy = "cinema", orphanRemoval = true)
    open var tickets: MutableSet<Ticket> = mutableSetOf()
//    @Transient
//    @JsonIgnore val seats: Array<Array<Seat?>> = Array(SEAT_ROWS) { arrayOfNulls(SEAT_COLS) },
//    @Transient
//    @JsonIgnore val listTickets: MutableList<Ticket> = mutableListOf()
) {
//    init {
//        for (row in 0 until SEAT_ROWS) {
//            for (col in 0 until SEAT_COLS) {
//                seats[row][col] = Seat(row + 1, col + 1, price = if (row <= 3) 10 else 8)
//            }
//        }
//    }
//
//    @JsonProperty("available_seats")
//    fun getAvailableSeat(): List<Seat?> {
//        val rs = mutableListOf<Seat?>()
//        for (row in 0 until SEAT_ROWS) {
//            for (col in 0 until SEAT_COLS) {
//                if (seats[row][col]?.avail == true) {
//                    rs.add(seats[row][col])
//                }
//            }
//        }
//        return rs
//    }
//
//
//    fun statistic(): Map<String, Int> {
//        val rs = mapOf<String, Int>()
//        var currentIncome = 0
//        var numberOfPurchasedTickets = 0
//        listTickets.forEach {
//            if (it.state == "Sold") {
//                currentIncome += it.seat?.price ?: 0
//
//                numberOfPurchasedTickets += 1
//            }
//        }
//        return mapOf(
//            "current_income" to currentIncome,
//            "number_of_available_seats" to SEAT_ROWS * SEAT_COLS - numberOfPurchasedTickets,
//            "number_of_purchased_tickets" to numberOfPurchasedTickets
//        )
//
//    }


}