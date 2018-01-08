package io.pileworx.hex.port.primary.rest

import io.pileworx.hex.app.ContactService
import io.pileworx.hex.port.primary.rest.adapter.ContactAdapter
import io.pileworx.hex.port.primary.rest.adapter.ContactsAdapter
import io.pileworx.hex.port.primary.rest.command.CreateContactJackson
import io.pileworx.hex.port.primary.rest.resource.ContactResource
import io.pileworx.hex.port.primary.rest.resource.ContactsResource
import org.springframework.hateoas.ResourceSupport
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/contacts"], produces = ["application/hal+json"])
class ContactRestPort(
        val contactService: ContactService,
        val contactAdapter: ContactAdapter,
        val contactsAdapter: ContactsAdapter) {

    @RequestMapping(value = [""], method = [(RequestMethod.GET)])
    fun getContacts() = ResponseEntity<ContactsResource>(
            contactsAdapter.toResource(contactService.findAll()),
            HttpStatus.OK)

    @RequestMapping(value = ["/{id}"], method = [(RequestMethod.GET)])
    fun getContact(@PathVariable id:String) = ResponseEntity<ContactResource>(
            contactAdapter.toResource(contactService.findById(id)),
            HttpStatus.OK)

    @RequestMapping(method = [(RequestMethod.POST)])
    fun postContact(@RequestBody command: CreateContactJackson) = ResponseEntity<ResourceSupport>(
            contactAdapter.createLink(contactService.createContact(command).value),
            HttpStatus.CREATED)
}