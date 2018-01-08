package io.pileworx.hex.port.primary.ampq

import io.pileworx.hex.app.ContactService
import io.pileworx.hex.port.primary.ampq.message.command.CreateContactMessage
import io.pileworx.hex.port.primary.ampq.message.response.ContactLocationMessage
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class ContactListenerPort(
        private val contactService: ContactService,
        private val rabbitTemplate: RabbitTemplate) {

    @RabbitListener(queues = ["newContact"])
    fun createContact(command:CreateContactMessage) {
        val id = contactService.createContact(command).value

        rabbitTemplate.convertAndSend(
                "contactCreated",
                ContactLocationMessage(command.requestId, "http://localhost:8080/contacts/$id"))
    }
}