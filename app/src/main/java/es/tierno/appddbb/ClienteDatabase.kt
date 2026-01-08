package es.tierno.appddbb

import androidx.room.Database
import androidx.room.RoomDatabase
import es.tierno.appddbb.dao.ClienteDao
import es.tierno.appddbb.dao.TelefonoDao
import es.tierno.appddbb.data.ClienteEntity
import es.tierno.appddbb.data.TelefonoEntity

@Database(entities = [ClienteEntity::class, TelefonoEntity::class], version = 1)
abstract class ClienteDatabase: RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
    abstract fun telefonoDao(): TelefonoDao
}