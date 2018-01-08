package io.pileworx.hex.common.domain

interface Id {
    val value: String
}

interface Entity<out TID> where TID: Id {
    val id: TID
}

interface AggregateRoot<out TID>: Entity<TID> where TID: Id

interface Repository<T, TID> where T: AggregateRoot<TID>, TID: Id {
    fun nextId(): TID
    fun find(id: TID): T
    fun save(aggregateRoot: T)
}

class RepositoryException(message: String, cause: Throwable): RuntimeException(message, cause)