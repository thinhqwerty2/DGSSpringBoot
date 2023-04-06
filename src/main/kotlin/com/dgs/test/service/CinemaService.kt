package com.dgs.test.service

import com.dgs.test.entity.Cinema
import com.dgs.test.entity.Seat
import com.dgs.test.repository.CinemaRepository
import com.dgs.test.repository.SeatRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CinemaService(val cinemaRepository: CinemaRepository,val seatRepository: SeatRepository) {
    fun saveCinema(cinema: Cinema): Cinema {
        return cinemaRepository.save(cinema)
    }
    fun saveSeat(seat:Seat):Seat {
        return seatRepository.save(seat)
    }

    fun getSeatsByCinemaId(cinemaId: Long):List<Seat>{
        return cinemaRepository.getInfoSeatsByCinemaId(cinemaId)
    }

    fun getDetailsSeatBySeatAndCinemaId(seat: Seat, cinemaId:Long): Seat? {
        return seatRepository.getDetailSeatBySeatAndCinemaId(seat.rowSeat,seat.columnSeat,cinemaId)
    }

    fun findCinemaById(cinemaId: Long): Optional<Cinema> {
        return cinemaRepository.getCinemaById(cinemaId)
    }

    fun purchaseTicket(seat: Seat,cinemaId: Long){
        val cinema = findCinemaById(cinemaId)

    }



}