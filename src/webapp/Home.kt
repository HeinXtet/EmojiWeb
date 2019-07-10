package com.heinhtet.webapp

import com.heinhtet.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route


const val HOME = "/"
@Location(HOME)
class Home
fun Route.home(db: Repository) {
    get<Home> {
        this.call.respond(FreeMarkerContent("home.ftl",null))
    }
}