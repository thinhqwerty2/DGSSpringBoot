package com.dgs.test.service

import com.dgs.test.repository.TicketRepository
import org.springframework.stereotype.Service

@Service
class TicketService(val ticketRepository: TicketRepository) {
}