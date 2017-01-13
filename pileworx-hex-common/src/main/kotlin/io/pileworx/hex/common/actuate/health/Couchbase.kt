package io.pileworx.hex.common.actuate.health

import com.couchbase.client.java.Bucket
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

class Couchbase(val bucket:Bucket) : HealthIndicator {

    @Value("\${spring.couchbase.env.health.pingid}")
    lateinit var pingId:String

    override fun health(): Health {
        try {
            val ping = bucket.get(pingId).content().get("ping")

            if (false == ping)
                throw RuntimeException("Failed to receive positive ping from server.")

        } catch (rex:RuntimeException) {
            return Health.down()
                    .withDetail("Ping Failed", rex.message).build()
        }

        return Health.up().build()
    }
}