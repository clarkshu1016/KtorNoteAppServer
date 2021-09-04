package com.usfuchsia.note.data

import com.usfuchsia.note.collections.User
import org.litote.kmongo.reactivestreams.*  //NEEDED! import KMongo reactivestreams extensions
import org.litote.kmongo.coroutine.* //NEEDED! import KMongo coroutine extensions
import org.litote.kmongo.eq


val client = KMongo.createClient().coroutine //use coroutine extension
val database = client.getDatabase("NotesDataBase") //normal java driver usage

suspend fun registerUser(user: User): Boolean {
    return database.getCollection<User>().insertOne(user).wasAcknowledged()
}

suspend fun ifUserExists(email: String): Boolean {
    return database.getCollection<User>().findOne(User::email eq email)!=null
}