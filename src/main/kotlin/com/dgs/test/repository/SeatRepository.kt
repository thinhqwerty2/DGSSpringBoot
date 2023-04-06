package com.dgs.test.repository

import com.dgs.test.entity.Seat
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SeatRepository : CrudRepository<Seat, Long> {
}