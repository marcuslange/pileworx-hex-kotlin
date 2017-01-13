package io.pileworx.hex.app.dto


data class ContactSummaryDto(
        val id:String,
        val name:String,
        val email:String,
        val state:String)

data class ContactDto(
        val id: String,
        val name:NameDto,
        val address: AddressDto,
        val phone: PhoneDto,
        val email:String
)

data class NameDto(
        val first:String,
        val last:String,
        val middle:String
)

data class AddressDto(
        val one:String,
        val two:String,
        val city:String,
        val state:String,
        val zip:String
)

data class PhoneDto(
        val one:String,
        val two:String,
        val three:String
)