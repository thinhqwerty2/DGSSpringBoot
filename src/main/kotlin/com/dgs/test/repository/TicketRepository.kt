package com.dgs.test.repository

import com.dgs.test.entity.Ticket
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : CrudRepository<Ticket, String> {

    @Query("select c.tickets from Cinema c join c.tickets where c.id=:id")
    fun getInfoTicketsByCinemaId(@Param("id") id:Long):List<Ticket>
}