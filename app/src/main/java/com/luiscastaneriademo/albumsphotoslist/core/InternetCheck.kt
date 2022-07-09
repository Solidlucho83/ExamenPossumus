package com.luiscastaneriademo.albumsphotoslist.core

import kotlinx.coroutines.coroutineScope
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

object InternetCheck {

    suspend fun internetIsok() = coroutineScope{
        return@coroutineScope try {
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", 53)
            sock.connect(socketAddress, 6000)
            sock.close()
            true
        }catch (e: IOException){
            false

        }

    }
}