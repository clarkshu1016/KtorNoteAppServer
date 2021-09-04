package com.usfuchsia.note.plugins

import com.usfuchsia.note.collections.User
import com.usfuchsia.note.data.ifUserExists
import com.usfuchsia.note.data.registerUser
import com.usfuchsia.note.data.request.AccountRequest
import com.usfuchsia.note.data.response.SimpleResponse
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.request.ContentTransformationException
import io.ktor.response.*
import io.ktor.routing.*


fun Route.registerRoute() {
    route(("/")){
        get {
            call.respond("hello ")
        }
    }
    route("/test"){
        get {
            call.respond("hello from test")
        }
    }
    route("/register") {
        post {
            val request = try {
                call.receive<AccountRequest>()
            } catch (e: ContentTransformationException) {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            if (!ifUserExists(request.email)) {
                if (registerUser(User(request.email, request.password))) {
                    call.respond(HttpStatusCode.OK, SimpleResponse(true, "Successfully created account"))
                } else {
                    call.respond(HttpStatusCode.OK, SimpleResponse(false, "Create account failed"))
                }
            }else{
                call.respond(HttpStatusCode.OK,SimpleResponse(false,"User existed"))
            }
        }
    }
}