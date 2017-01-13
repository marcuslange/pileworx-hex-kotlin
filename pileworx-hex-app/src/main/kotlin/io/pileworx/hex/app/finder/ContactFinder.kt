package io.pileworx.hex.app.finder

import io.pileworx.hex.app.dto.ContactDto
import io.pileworx.hex.app.dto.ContactSummaryDto
import io.pileworx.hex.common.cqrs.Finder

interface ContactFinder: Finder<ContactSummaryDto, ContactDto>