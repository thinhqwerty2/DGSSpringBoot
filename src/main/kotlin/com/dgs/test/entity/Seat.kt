package com.dgs.test.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.hibernate.Hibernate
import java.util.UUID


@Entity
data class Seat(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
     var id: Long? = null,
    @Column
    val columnSeat: Int=0,
    @JsonIgnore
    @Column
    var avail: Boolean = true,
    @Column
    val price:Int=0,
    @Column
    val rowSeat: Int=0,

) {
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    open var cinemaId: Cinema? = null

    @OneToMany(mappedBy = "seat", orphanRemoval = true)
    open var tickets: MutableSet<Ticket> = mutableSetOf()

    constructor():this(id=null)

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
//        other as Seat
//
//        return id != null && id == other.id
//    }
//
//    override fun hashCode(): Int = javaClass.hashCode()
//
//    @Override
//    override fun toString(): String {
//        return this::class.simpleName + "(id = $id , row = $row , column = $column , avail = $avail , price = $price )"
//    }

}