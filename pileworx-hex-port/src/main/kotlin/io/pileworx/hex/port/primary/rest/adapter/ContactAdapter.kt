package io.pileworx.hex.port.primary.rest.adapter

import io.pileworx.hex.app.dto.ContactDto
import io.pileworx.hex.app.dto.ContactSummaryDto
import io.pileworx.hex.port.primary.rest.resource.ContactResource
import io.pileworx.hex.port.primary.rest.resource.ContactsResource
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.ResourceSupport

interface ContactAdapter: ResourceAssembler<ContactDto, ContactResource> {
    fun createLink(contactId: String): ResourceSupport
}

interface ContactsAdapter: ResourceAssembler<List<ContactSummaryDto>, ContactsResource>