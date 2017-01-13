package io.pileworx.hex.port.primary.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.annotation.Resource

@Configuration
class AmqpContext(
        private val connectionFactory: ConnectionFactory,
        private val halObjectMapper: ObjectMapper) {

    @Bean
    fun rabbitListenerContainerFactory(): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setMessageConverter(jackson2JsonMessageConverter())
        return factory
    }

    @Bean
    fun rabbitTemplate(): AmqpTemplate {
        val template = RabbitTemplate(connectionFactory)
        template.messageConverter = jackson2JsonMessageConverter()
        return template
    }

    @Bean
    fun jackson2JsonMessageConverter(): MessageConverter {
        val converter = Jackson2JsonMessageConverter()
        converter.setJsonObjectMapper(halObjectMapper)
        return converter
    }
}