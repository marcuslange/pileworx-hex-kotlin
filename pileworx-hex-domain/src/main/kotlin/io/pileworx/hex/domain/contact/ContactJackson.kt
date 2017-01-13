package io.pileworx.hex.domain.contact

import io.pileworx.hex.common.jackson.IdJackson
import io.pileworx.hex.domain.Contact
import io.pileworx.hex.domain.ContactId

class ContactIdJackson(value:String) : IdJackson(value), ContactId

data class ContactJackson(
        override val id: ContactIdJackson,
        val name:NameJackson,
        val address:AddressJackson,
        val phone:PhoneJackson,
        val email:EmailJackson
): Contact

data class NameJackson(
        val first:String,
        val last:String,
        val middle:String
) {
    companion object {
        fun create(first:String, last:String, middle:String):NameJackson {
            return NameJackson(first, last, middle)
        }
    }
}

data class AddressJackson(
        val one:String,
        val two:String,
        val city:String,
        val state:String,
        val zip:String
) {
    companion object {
        fun create(one:String, two:String, city:String, state:String, zip:String):AddressJackson {
            return AddressJackson(one, two, city, state, zip)
        }
    }
}

data class PhoneJackson(
        val areaCode:String,
        val localExchange:String,
        val stationNumber:String
) {
    companion object {
        fun create(areaCode:String, localExchange:String, stationNumber:String): PhoneJackson {
            return PhoneJackson(areaCode, localExchange, stationNumber)
        }
    }
}

data class EmailJackson(
        val localPart:String,
        val domain:String
) {
    companion object {
        fun create(email: String): EmailJackson {
            val parts = email.split("@")
            return EmailJackson(parts[0], parts[1])
        }
    }
}