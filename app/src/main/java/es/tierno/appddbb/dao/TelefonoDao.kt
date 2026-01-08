package es.tierno.appddbb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import es.tierno.appddbb.data.TelefonoEntity

@Dao
interface TelefonoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(telefono: TelefonoEntity)
}