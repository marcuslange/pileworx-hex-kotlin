package io.pileworx.hex.app.port.secondary.couchbase

import com.couchbase.client.java.Bucket
import com.couchbase.client.java.view.ViewQuery
import com.fasterxml.jackson.databind.ObjectMapper
import io.pileworx.hex.app.dto.ContactDto
import io.pileworx.hex.app.dto.ContactSummaryDto
import io.pileworx.hex.app.finder.ContactFinder
import io.pileworx.hex.common.domain.RepositoryException
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.io.IOException

@Service("contactFinder")
class ContactFinderCB(
        private val bucket: Bucket,
        private val jsonMapper: ObjectMapper):
    ContactFinder {

    val typeParameterClass = ContactDto::class.java
    val typeParameterSummaryClass = ContactSummaryDto::class.java

    override fun findAll(): List<ContactSummaryDto> {
        return bucket.query(ViewQuery.from("contacts", "contact_summary"))
                .allRows()
                .map { r -> readSummaryDoc(r.value().toString()) }
    }

    override fun findById(id:String): ContactDto {
        val row = bucket.query(ViewQuery.from("contacts", "contact")).first { r -> r.id() == id }

        return readDoc(row.value().toString())
    }

    fun readDoc(source: String): ContactDto {
        try {
            return jsonMapper.readValue(source, typeParameterClass)
        } catch (e: IOException) {
            val rex = RepositoryException(source, e)
            throw RepositoryException("Exception while deserializing document", rex)
        }
    }

    fun readSummaryDoc(source: String): ContactSummaryDto {
        try {
            return jsonMapper.readValue(source, typeParameterSummaryClass)
        } catch (e: IOException) {
            val rex = RepositoryException(source, e)
            throw RepositoryException("Exception while deserializing document", rex)
        }
    }
}