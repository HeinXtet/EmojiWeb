package com.heinhtet.webapp

import com.heinhtet.model.User
import com.heinhtet.repository.Repository
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

const val PHRASES  = "/phrases"


fun Route.Phrases(db: Repository){

    authenticate("auth") {


    get(PHRASES){

        val user = call.authentication.principal as User
        call.respond(FreeMarkerContent("phrases.ftl",mapOf("phrases" to db.pharses(),"displayName" to user.displayName)))
    }
    }

}