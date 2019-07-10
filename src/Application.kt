package com.heinhtet

import com.heinhtet.api.phase
import com.heinhtet.api.phrases
import com.heinhtet.model.User
import com.heinhtet.repository.InMemoryRepository
import com.heinhtet.webapp.*
import com.ryanharter.ktor.moshi.moshi
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.auth.Authentication
import io.ktor.auth.basic
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.freemarker.FreeMarker
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.resource
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.locations.Locations
import io.ktor.locations.locations
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.response.respondText
import io.ktor.routing.routing
import javax.naming.AuthenticationException

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(
        StatusPages
    ) {
        exception<AuthenticationException> { cause ->
            call.respond(HttpStatusCode.Unauthorized)
        }
        exception<NoSuchMethodException> { cause ->
            call.respond(HttpStatusCode.MethodNotAllowed)
        }
        exception<Throwable> {
            call.respondText(
                it.localizedMessage,
                ContentType.Text.Plain,
                HttpStatusCode.InternalServerError
            )
        }
    }
    // for convector
    install(ContentNegotiation) {
        moshi()
    }

    // for template
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }


    install(Authentication) {
        basic("auth") {
            realm = "Ktor server"
            validate { credential ->
                if (credential.password == "${credential.name}123") User(credential.name) else null
            }
        }
    }
    install(Locations)

    val inMemoryRepository = InMemoryRepository()

    routing {
        static("/static") {
            resources("images")
        }
        home(inMemoryRepository)
        about()
        Phrases(inMemoryRepository)
        signUp()
        signIn()

        //API
        phase(inMemoryRepository)
        phrases(inMemoryRepository)
    }
}


const val API_VERSION = "/api/v1"


suspend fun ApplicationCall.redriect(location: Any){
    respondRedirect(application.locations.href(location))
}