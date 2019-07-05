package com.heinhtet

import com.heinhtet.api.phase
import com.heinhtet.api.phrases
import com.heinhtet.repository.InMemoryRepository
import com.heinhtet.webapp.about
import com.heinhtet.webapp.home
import com.ryanharter.ktor.moshi.moshi
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.freemarker.FreeMarker
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.routing
import javax.naming.AuthenticationException

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
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
            call.respond(HttpStatusCode.MethodNotAllowed    )
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

    val inMemoryRepository = InMemoryRepository()

    routing {
        home()
        about()

        //API
        phase(inMemoryRepository)
        phrases(inMemoryRepository)
    }
}


const val API_VERSION = "/api/v1"