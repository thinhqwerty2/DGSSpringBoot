package com.dgs.test.controller

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.dgs.test.entity.Cinema
import com.dgs.test.entity.Seat


@DgsComponent
class DGSController {
    private val cinema= Cinema()

//    @DgsQuery
//    fun seats(): List<Seat?> {
//        return cinema.getAvailableSeat()
//    }
}