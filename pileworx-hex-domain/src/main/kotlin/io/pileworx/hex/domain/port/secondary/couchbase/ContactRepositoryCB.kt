package io.pileworx.hex.domain.port.secondary.couchbase

import com.couchbase.client.java.Bucket
import com.fasterxml.jackson.databind.ObjectMapper
import io.pileworx.hex.common.domain.repository.CouchbaseRepositoryAbstract
import io.pileworx.hex.domain.Contact
import io.pileworx.hex.domain.ContactId
import io.pileworx.hex.domain.ContactRepository
import io.pileworx.hex.domain.contact.ContactIdJackson
import io.pileworx.hex.domain.contact.ContactJackson
import org.springframework.stereotype.Repository
import java.util.*

@Repository("contactRepository")
class ContactRepositoryCB(
        bucket: Bucket,
        jsonMapper: ObjectMapper):
    CouchbaseRepositoryAbstract<Contact, ContactId>(
        bucket,
        ContactJackson::class.java as Class<Contact>,
        jsonMapper),
    ContactRepository {

    override fun nextId(): ContactId = ContactIdJackson(UUID.randomUUID().toString())
}