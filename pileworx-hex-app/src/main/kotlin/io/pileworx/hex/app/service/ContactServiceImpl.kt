package io.pileworx.hex.app.service

import io.pileworx.hex.app.ContactService
import io.pileworx.hex.app.dto.*
import io.pileworx.hex.app.finder.ContactFinder
import io.pileworx.hex.domain.Contact
import io.pileworx.hex.domain.ContactId
import io.pileworx.hex.domain.ContactRepository
import io.pileworx.hex.domain.contact.CreateContact
import org.springframework.stereotype.Service

@Service("contactService")
class ContactServiceImpl(
        private val contactRepository: ContactRepository,
        private val contactFinder:ContactFinder):
    ContactService {

    override fun findAll(): List<ContactSummaryDto> = contactFinder.findAll()

    override fun findById(id:String): ContactDto = contactFinder.findById(id)

    override fun createContact(command: CreateContact): ContactId {
        val id = contactRepository.nextId()
        contactRepository.save(Contact.create(id, command))
        return id
    }
}