package com.dgs.test.service

import com.dgs.test.entity.Ticket
import com.dgs.test.repository.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketService(val ticketRepository: TicketRepository) {
    fun getTicketByCinemaId(cinemaId:Long):List<Ticket>{
        return ticketRepository.getInfoTicketsByCinemaId(cinemaId)
    }
    fun saveTicket(ticket: Ticket):Ticket{
        return ticketRepository.save(ticket)
    }
}