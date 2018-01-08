package io.pileworx.hex.domain

import io.pileworx.hex.common.domain.AggregateRoot
import io.pileworx.hex.common.domain.Id
import io.pileworx.hex.common.domain.Repository
import io.pileworx.hex.domain.contact.*

interface ContactId: Id

interface Contact: AggregateRoot<ContactId> {
    companion object {
        fun create(id:ContactId, cmd:CreateContact): Contact = ContactJackson(
                id as ContactIdJackson,
                NameJackson.create(cmd.firstName, cmd.lastName, cmd.middleName),
                AddressJackson.create(cmd.addressOne, cmd.addressTwo, cmd.addressCity, cmd.addressState, cmd.addressZip),
                PhoneJackson.create(cmd.phoneOne, cmd.phoneTwo, cmd.phoneThree),
                EmailJackson.create(cmd.email)
        )
    }
}

interface ContactRepository: Repository<Contact, ContactId>