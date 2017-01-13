package io.pileworx.hex.domain.contact

interface CreateContact {
    val firstName:String
    val lastName:String
    val middleName:String
    val addressOne:String
    val addressTwo:String
    val addressCity:String
    val addressState:String
    val addressZip:String
    val phoneOne:String
    val phoneTwo:String
    val phoneThree:String
    val email:String
}