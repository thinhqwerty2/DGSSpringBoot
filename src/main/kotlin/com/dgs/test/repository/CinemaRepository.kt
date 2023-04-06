package com.dgs.test.repository

import com.dgs.test.entity.Cinema
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CinemaRepository: CrudRepository<Cinema, Long> {

}