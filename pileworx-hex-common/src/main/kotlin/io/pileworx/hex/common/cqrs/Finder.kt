package io.pileworx.hex.common.cqrs

interface Finder<out SDTO, out DTO> {
    fun findAll(): List<SDTO>
    fun findById(id:String): DTO
}