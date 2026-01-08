package es.tierno.appddbb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import es.tierno.appddbb.data.ClienteEntity
import es.tierno.appddbb.data.ClienteTelefonosEntity
import es.tierno.appddbb.data.TelefonoEntity
import es.tierno.appddbb.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var database: ClienteDatabase
        const val DATABASE_NAME = "cliente-db"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MainActivity.database = Room.databaseBuilder(this,
            ClienteDatabase::class.java,
            DATABASE_NAME).build()

        guardar()

    }

    private fun guardar() {
        val clienteDao = database.clienteDao()
        val telefonoDao = database.telefonoDao();

        var clienteId: Long = 0 ;
        val cliente = ClienteEntity(0, "Alumno", "Apellidos");
        var lista: List<ClienteTelefonosEntity>

        CoroutineScope(Dispatchers.IO).launch {

            // Insertamos el cliente y nos guardamos su id
            clienteId = clienteDao.insert(cliente)

            // Creamos un teléfono para ese cliente (con el id antes recogido)
            val telefono = TelefonoEntity(0, "tel1", clienteId)
            // Insertamos el cliente
            telefonoDao.insert(telefono)

            // Obtenemos todos los clientes y sus telefonos
            lista = clienteDao.getClientesTelefonos();
        }

        Toast.makeText(this,"Todo cargado", Toast.LENGTH_LONG).show()
    }
}