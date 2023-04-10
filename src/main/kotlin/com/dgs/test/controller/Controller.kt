package com.dgs.test.controller


//import cinema.entity.*


import com.dgs.test.constant.CINEMA_NUM
import com.dgs.test.constant.SEAT_COLS
import com.dgs.test.constant.SEAT_ROWS
import com.dgs.test.email.EmailDetails
import com.dgs.test.email.EmailService
import com.dgs.test.entity.*
import com.dgs.test.service.CinemaService
import com.dgs.test.service.TicketService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class Controller(
//    private val cinema: Cinema = Cinema(),
    val cinemaService: CinemaService, val ticketService: TicketService, val emailService: EmailService
) {
    @PostMapping("/sendMailHtmlFormat")
    fun sendMailHtmlFormat(@RequestBody details: EmailDetails): String? {
        return emailService.sendMailHtmlFormat(details)
    }
    @PostMapping("/sendMail")
    fun sendMail(@RequestBody details: EmailDetails): String? {
        return emailService.sendSimpleMail(details)
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    fun sendMailWithAttachment(
        @RequestBody details: EmailDetails
    ): String? {
        return emailService.sendMailWithAttachment(details)
    }

    @GetMapping("/seats", produces = ["application/json"])
    fun getSeatInfo(): ResponseEntity<List<Any>> {
        println(cinemaService.getSeatsByCinemaId(1))
        return ResponseEntity(cinemaService.getSeatsByCinemaId(1), HttpStatus.OK)
    }

    @GetMapping("/init")
    fun initSomeData(): String {
        try {
            for (cinemaNum in 0 until CINEMA_NUM) {
                val cinemaTemp = Cinema(SEAT_ROWS, SEAT_COLS)
                cinemaService.saveCinema(cinemaTemp)
                for (row in 0 until SEAT_ROWS) {
                    for (col in 0 until SEAT_COLS) {
                        val seatTemp = Seat(row + 1, col + 1, price = if (row <= 3) 10 else 8, cinemaId = cinemaTemp)
                        cinemaService.saveSeat(seatTemp)

                    }
                }
            }
            return "Init Success"
        } catch (e: Exception) {
            return "Init failed because ${e.message}"
        }
    }

    @PostMapping("/purchase", produces = ["application/json"], consumes = ["application/json"])
    fun purchase(
        @RequestBody seat: Seat, @RequestParam("cinemaID", required = false) cinemaId: Long = 1
    ): ResponseEntity<Any> {
        val row = seat.rowSeat - 1
        val column = seat.columnSeat - 1
        val seatInDB: Seat? = cinemaService.getDetailsSeatBySeatAndCinemaId(seat, cinemaId)
        return when {
            row !in 0 until SEAT_ROWS || column !in 0 until SEAT_COLS -> ResponseEntity.status(400)
                .body(mapOf("error" to "The number of a row or a column is out of bounds!"))

            seatInDB?.avail == false -> ResponseEntity.status(400)
                .body(mapOf("error" to "The ticket has been already purchased!"))

            else -> {
                seatInDB?.avail = false
                val ticket = Ticket(seat = seatInDB)
                ticketService.saveTicket(ticket)
                ResponseEntity.status(200).body(ticket)
            }

        }
    }
//
//    @PostMapping("/return", consumes = ["application/json"], produces = ["application/json"])
//    fun returnTicket(@RequestBody ticket: Ticket): ResponseEntity<Any> {
//        println(ticket.toString())
//        val rs: Ticket? = cinema.listTickets.find { it.token==ticket.token }
//        return if(rs==null){
//            ResponseEntity.status(400).body(mapOf("error" to "Wrong token!"))
//        }else{
//            cinema.seats[rs.seat?.row!!][rs.seat.column]?.avail=true
//            rs.state ="Refunded"
//            ResponseEntity.status(200).body(mapOf("returned_ticket" to rs.seat))
//        }
//
//
//    }
//
//    @PostMapping("/stats", produces = ["application/json"])
//    fun stats(@RequestParam("password", required =false) pass:String?):ResponseEntity<Any>{
//        return if(pass=="super_secret"){
//            ResponseEntity.status(200).body(cinema.statistic())
//        } else{
//            ResponseEntity.status(401).body(mapOf("error" to "The password is wrong!"))
//        }
//    }
}