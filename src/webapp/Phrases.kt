package com.heinhtet.webapp

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

const val PHRASES  = "/phrases"


fun Route.Phrases(){
    get(PHRASES){
        call.respond(FreeMarkerContent("phrases.ftl",null))
    }
}