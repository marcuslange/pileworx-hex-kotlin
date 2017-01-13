package io.pileworx.hex.common.domain.repository

import com.couchbase.client.java.Bucket
import com.couchbase.client.java.document.RawJsonDocument
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import io.pileworx.hex.common.domain.AggregateRoot
import io.pileworx.hex.common.domain.Id
import io.pileworx.hex.common.domain.Repository
import io.pileworx.hex.common.domain.RepositoryException
import java.io.IOException

abstract class CouchbaseRepositoryAbstract<T, TID>(
        protected val bucket:Bucket,
        protected val typeParameterClass: Class<T>,
        protected val jsonMapper: ObjectMapper):
    Repository<T, TID> where T: AggregateRoot<TID>, TID: Id {

    override fun find(id:TID): T {
        return readDoc(bucket.get(RawJsonDocument.create(id.value, "")).content())
    }

    override fun save(aggregateRoot: T) {
        bucket.insert(RawJsonDocument.create(aggregateRoot.id.value, writeDoc(aggregateRoot)))
    }

    protected fun readDoc(source: String): T {
        try {
            return jsonMapper.readValue(source, typeParameterClass)
        } catch (e: IOException) {
            val rex = RepositoryException(source, e)
            throw RepositoryException("Exception while deserializing document", rex)
        }
    }

    protected fun writeDoc(source: T): String {
        try {
            return jsonMapper.writeValueAsString(source)
        } catch (e: JsonProcessingException) {
            throw RepositoryException("Exception while serializing document", e)
        }
    }
}