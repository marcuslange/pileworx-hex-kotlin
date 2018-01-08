package io.pileworx.hex.domain.config

import com.couchbase.client.java.Bucket
import io.pileworx.hex.common.actuate.health.Couchbase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DomainContext {
    @Bean
    fun couchbase(bucket:Bucket) = Couchbase(bucket)
}