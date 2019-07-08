package com.heinhtet.api

import com.heinhtet.API_VERSION
import com.heinhtet.model.EmojiPhase
import com.heinhtet.model.Request
import com.heinhtet.model.User
import com.heinhtet.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.auth.*
import io.ktor.request.receiveParameters
import io.ktor.response.respondRedirect
import io.ktor.routing.post

const val PHRASES = "$API_VERSION/phrases"

fun Route.phrases(db: Repository) {
    authenticate("auth") {
        get(PHRASES) {
//            call.respond(db.pharses().toArray()) to return API
            val user = call.authentication.principal as User
            val phrases = db.pharses()
            call.respond(FreeMarkerContent("phrases.ftl", mapOf("phrases" to phrases, "displayName" to user.displayName)))
        }

        post(PHRASES){
            val params = call.receiveParameters()
            val emoji = params["emoji"] ?: throw IllegalArgumentException("Missing Parameter : emoji")
            val phrase = params["phrase"] ?: throw IllegalArgumentException("Missing Parameter : phrase")
            db.add(EmojiPhase(emoji,phrase))
            call.respondRedirect(PHRASES)
        }
    }

}