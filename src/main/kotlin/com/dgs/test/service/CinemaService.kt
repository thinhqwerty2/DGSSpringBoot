package com.dgs.test.service

import com.dgs.test.repository.CinemaRepository
import org.springframework.stereotype.Service

@Service
class CinemaService(val cinemaRepository: CinemaRepository) {

    init {
        
    }
}