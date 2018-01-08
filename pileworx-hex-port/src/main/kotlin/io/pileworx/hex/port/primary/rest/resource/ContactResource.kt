package io.pileworx.hex.port.primary.rest.resource

import io.pileworx.hex.app.dto.AddressDto
import io.pileworx.hex.app.dto.NameDto
import io.pileworx.hex.app.dto.PhoneDto
import org.springframework.hateoas.ResourceSupport

class ContactsResource(
        val _embedded:List<ContactSummaryResource>
): ResourceSupport()

class ContactSummaryResource(
        val title:String,
        val email:String,
        val state:String
): ResourceSupport()

class ContactResource(
        val name: NameDto,
        val address: AddressDto,
        val phone: PhoneDto,
        val email:String
): ResourceSupport()