package com.davelabela.pokedex.data.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import javax.inject.Inject

class PokeHTTPClient @Inject constructor(){

    fun getHttpClient() = HttpClient(Android){

        install(JsonFeature){
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json{
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging){
            logger = object : Logger{
                override fun log(message: String){
                    Log.i(TAG_KTOR_LOGGER, message)
                }
            }
        }

        install(ResponseObserver){
            onResponse { response ->
                Log.i(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
            }
        }

        engine {
            connectTimeout = TIME_OUT
            socketTimeout = TIME_OUT
        }
    }

    companion object{
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logging:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }

}