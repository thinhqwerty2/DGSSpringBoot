package com.dgs.test.repository

import com.dgs.test.entity.Cinema
import com.dgs.test.entity.Seat
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CinemaRepository : CrudRepository<Cinema, Long> {


    override fun findById(id: Long): Optional<Cinema>


    @Query("select c from Cinema c where c.id = :id")
    fun getCinemaById(@Param("id") id: Long): Optional<Cinema>

    @Query("select c.seats from Cinema c join c.seats where c.id=:id")
    fun getInfoSeatsByCinemaId(@Param("id") id: Long): List<Seat>


}