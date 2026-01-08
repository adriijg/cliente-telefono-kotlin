package es.tierno.appddbb.data

import androidx.room.Embedded
import androidx.room.Relation

data class ClienteTelefonosEntity(
    @Embedded val cliente: ClienteEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "cliente"
    )
    val telefonos: List<TelefonoEntity>
)
