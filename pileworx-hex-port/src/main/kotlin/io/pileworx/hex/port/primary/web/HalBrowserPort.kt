package io.pileworx.hex.port.primary.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping(value = ["/"], produces = ["text/html"])
class HalBrowserPort {

    @RequestMapping(value = [""], method = [(RequestMethod.GET)])
    fun getEntry() = RedirectView("hal-browser/browser.html")
}