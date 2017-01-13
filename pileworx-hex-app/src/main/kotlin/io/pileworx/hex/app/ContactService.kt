package io.pileworx.hex.app

import io.pileworx.hex.app.dto.ContactDto
import io.pileworx.hex.app.dto.ContactSummaryDto
import io.pileworx.hex.domain.ContactId
import io.pileworx.hex.domain.contact.CreateContact

interface ContactService {
    fun findAll():List<ContactSummaryDto>
    fun createContact(command:CreateContact): ContactId
    fun findById(id:String): ContactDto
}