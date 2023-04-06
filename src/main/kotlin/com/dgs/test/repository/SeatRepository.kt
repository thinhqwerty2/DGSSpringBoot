package com.dgs.test.repository

import com.dgs.test.entity.Seat
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SeatRepository : CrudRepository<Seat, Long> {
    @Query("select s from Seat s where s.cinemaId = :id and s.rowSeat = :row and s.columnSeat = :col")
    fun getDetailSeatBySeatAndCinemaId(@Param("row") row: Int, @Param("col") col: Int, @Param("id") id: Long?): Seat?

}