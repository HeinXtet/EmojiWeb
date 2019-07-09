package com.heinhtet.webapp

import com.heinhtet.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get


const val HOME = "/"

fun Route.home(db: Repository) {
    get(HOME) {
        this.call.respond(FreeMarkerContent("home.ftl",null))
    }
}