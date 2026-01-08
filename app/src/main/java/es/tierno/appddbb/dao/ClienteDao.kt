package es.tierno.appddbb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import es.tierno.appddbb.data.ClienteEntity
import es.tierno.appddbb.data.ClienteTelefonosEntity

@Dao
interface ClienteDao {
    @Transaction
    @Query("SELECT * FROM cliente")
    suspend fun getClientesTelefonos(): List<ClienteTelefonosEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cliente: ClienteEntity): Long
}