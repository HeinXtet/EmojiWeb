package com.heinhtet.webapp

import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get


const val SIGN_IN = "/signin"

fun Route.signIn(){
    get(SIGN_IN){
        call.respond(FreeMarkerContent("signin.ftl",null))
    }
}
