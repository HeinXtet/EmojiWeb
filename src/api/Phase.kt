package com.heinhtet.api

import com.heinhtet.API_VERSION
import com.heinhtet.model.EmojiPhase
import com.heinhtet.model.Request
import com.heinhtet.repository.Repository
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post

const val PHASE = "$API_VERSION/phrase"

fun Routing.phase(db: Repository) {
    authenticate("auth") {
        post(PHASE) {
            val receive = call.receive<Request>()
            val phrase = db.add(EmojiPhase(receive.emoji, receive.phase))
            call.respond(phrase)
        }
    }
}