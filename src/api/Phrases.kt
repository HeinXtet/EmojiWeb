package com.heinhtet.api

import com.heinhtet.API_VERSION
import com.heinhtet.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

const val PHRASES = "$API_VERSION/phrases"

fun Route.phrases(db: Repository) {
    get(PHRASES) {
        //        call.respond(db.pharses().toArray())
        val phrases = db.pharses()
        call.respond(FreeMarkerContent("phrases.ftl", mapOf("phrases" to phrases)))

    }

}