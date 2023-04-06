package com.dgs.test.controller

import com.dgs.test.entity.Cinema
import com.dgs.test.entity.Seat
import com.dgs.test.entity.Ticket
import com.dgs.test.service.CinemaService
import com.dgs.test.service.TicketService
import com.netflix.graphql.dgs.*
import java.util.*


@DgsComponent
class DGSController(val cinemaService: CinemaService,
    val ticketService: TicketService) {
    @DgsQuery
    fun cinemaById(@InputArgument id:Long): Optional<Cinema> {
        return cinemaService.findCinemaById(id)
    }
    @DgsMutation
    fun purchase( @InputArgument rowSeat:Int,@InputArgument colSeat:Int ,@InputArgument cinemaId:Long):Ticket{
        val seat=Seat(rowSeat = rowSeat, columnSeat = colSeat)
        val seatInDB: Seat? =cinemaService.getDetailsSeatBySeatAndCinemaId(seat,cinemaId)
        seatInDB?.avail = false
        val ticket= Ticket(seat = seatInDB)
        cinemaService.saveSeat(seatInDB!!)
        return ticketService.saveTicket(ticket)
    }

    @DgsData(parentType = "Cinema")
    fun seats(dfe:DgsDataFetchingEnvironment):List<Seat>{
        return cinemaService.getSeatsByCinemaId(dfe.getSource<Cinema>().id!!)
    }

    @DgsData(parentType = "Cinema")
    fun listTickets(dfe: DgsDataFetchingEnvironment):List<Ticket>{
        return ticketService.getTicketByCinemaId(dfe.getSource<Cinema>().id!!)
    }
}