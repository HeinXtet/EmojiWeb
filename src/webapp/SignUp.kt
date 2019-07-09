package com.heinhtet.webapp

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

const val SIGN_UP = "/signup"

fun Route.signUp(){
    get(SIGN_UP) {
        call.respond(FreeMarkerContent("signup.ftl",null))
    }
}