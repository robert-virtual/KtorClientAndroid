package com.example.ktorclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.resources.*

// serializacion de json
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

@Serializable
data class User(val id:Int,val email: String,
val first_name: String,
val last_name: String,
val avatar: String)


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
         findViewById<Button>(R.id.btn_get).setOnClickListener{
             getUsers()
        }

    }
    private fun showMessage(){
        Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
    }


    private fun getUsers(){
            runBlocking {
                val client = HttpClient(CIO){
                    install(ContentNegotiation){
                        json()
                    }
                    defaultRequest {
                        host = "https://reqres.in/api"
                    }
                }

                val users:List<User> = client.get("/users").body()
                println(users.count())
                client.close()

            }



    }

}