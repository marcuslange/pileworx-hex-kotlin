package io.pileworx.hex.port.primary.rest.command

import io.pileworx.hex.domain.contact.CreateContact

data class CreateContactJackson(
        override val firstName: String,
        override val lastName: String,
        override val middleName: String,
        override val addressOne: String,
        override val addressTwo: String,
        override val addressCity: String,
        override val addressState: String,
        override val addressZip: String,
        override val phoneOne: String,
        override val phoneTwo: String,
        override val phoneThree: String,
        override val email: String
): CreateContact