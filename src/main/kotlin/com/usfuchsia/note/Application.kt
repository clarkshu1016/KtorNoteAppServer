package com.usfuchsia.note

import com.usfuchsia.note.collections.User
import com.usfuchsia.note.data.registerUser
import com.usfuchsia.note.plugins.registerRoute
import io.ktor.server.netty.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    install(DefaultHeaders)
    install(Routing) {
        registerRoute()
    }
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
//    CoroutineScope(Dispatchers.IO).launch {
//        val user = User("test@gmail.com", "12342")
//        registerUser(user)
//    }
}

