package io.pileworx.hex.port.primary.rest.adapter.hal

import io.pileworx.hex.app.dto.ContactDto
import io.pileworx.hex.app.dto.ContactSummaryDto
import io.pileworx.hex.port.primary.rest.ContactRestPort
import io.pileworx.hex.port.primary.rest.EntryRestPort
import io.pileworx.hex.port.primary.rest.adapter.ContactAdapter
import io.pileworx.hex.port.primary.rest.adapter.ContactsAdapter
import io.pileworx.hex.port.primary.rest.resource.ContactResource
import io.pileworx.hex.port.primary.rest.resource.ContactSummaryResource
import io.pileworx.hex.port.primary.rest.resource.ContactsResource
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn

@Component("contactAdapter")
class ContactAdapterHal :
        ResourceAssemblerSupport<ContactDto, ContactResource>(
                ContactRestPort::class.java,
                ContactResource::class.java),
        ContactAdapter {

    override fun createLink(contactId: String): ResourceSupport {
        val resource = ResourceSupport()
        resource.add(linkTo(methodOn(ContactRestPort::class.java).getContact(contactId)).withSelfRel())
        return resource
    }

    override fun toResource(entity: ContactDto): ContactResource {
        val cr = ContactResource(
                entity.name,
                entity.address,
                entity.phone,
                entity.email)

        cr.add(linkTo(methodOn(ContactRestPort::class.java).getContact(entity.id)).withSelfRel())
        cr.add(linkTo(methodOn(ContactRestPort::class.java).getContacts()).withRel("parent"))

        return cr
    }
}

@Component("contactsAdapter")
class ContactsAdapterHal:
        ResourceAssemblerSupport<List<ContactSummaryDto>, ContactsResource>(
                ContactRestPort::class.java,
                ContactsResource::class.java),
        ContactsAdapter {

    override fun toResource(entity: List<ContactSummaryDto>): ContactsResource {
        val cr = ContactsResource(
                entity.map { cs ->
                    val csr = ContactSummaryResource(cs.name, cs.email, cs.state)
                    csr.add(linkTo(methodOn(ContactRestPort::class.java).getContact(cs.id)).withSelfRel())
                    csr.add(linkTo(methodOn(ContactRestPort::class.java).getContacts()).withRel("parent"))
                    csr
                })

        cr.add(linkTo(methodOn(ContactRestPort::class.java).getContacts()).withSelfRel())
        cr.add(linkTo(methodOn(EntryRestPort::class.java).getEntry()).withRel("parent"))
        return cr
    }
}