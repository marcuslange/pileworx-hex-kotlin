package io.pileworx.hex.port.primary.ampq.message.response

import java.io.Serializable

data class ContactLocationMessage(
        val requestId:String,
        val contactUri:String): Serializable