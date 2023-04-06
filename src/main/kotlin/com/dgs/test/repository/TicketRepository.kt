package com.dgs.test.repository

import com.dgs.test.entity.Ticket
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository : CrudRepository<Ticket, String> {
}