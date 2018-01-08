package io.pileworx.hex.port.primary.rest

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.*
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = [""], produces = ["application/hal+json"])
class EntryRestPort {

    @RequestMapping(value = [""], method = [(RequestMethod.GET)])
    fun getEntry(): HttpEntity<ResourceSupport> {
        val er = ResourceSupport()
        er.add(linkTo(methodOn(EntryRestPort::class.java).getEntry()).withSelfRel())
        er.add(linkTo(methodOn(ContactRestPort::class.java).getContacts()).withRel("contacts"))

        return ResponseEntity<ResourceSupport>(er, HttpStatus.OK)
    }
}