package es.tierno.appddbb

import android.os.Bundle
import android.util.Log
import android.view.View
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
    }

    fun guardar(view: View) {
        // Recogemos los datos de nuestra app
        val nameClient = binding.nameInput.text.toString()
        val surnameClient = binding.surnameInput.text.toString()
        val tlf1Client = binding.tlf1Input.text.toString()
        val tlf2Client = binding.tlf2Input.text.toString()

        // Creamos toda la lógica para DB.
        val clienteDao = database.clienteDao()
        val telefonoDao = database.telefonoDao();

        var clienteId: Long = 0 ;
        val cliente = ClienteEntity(0, nameClient, surnameClient);
        var lista: List<ClienteTelefonosEntity>

        CoroutineScope(Dispatchers.IO).launch {

            // Insertamos el cliente y nos guardamos su id
            clienteId = clienteDao.insert(cliente)

            // Creamos un teléfono para ese cliente (con el id antes recogido)
            val telefono1 = TelefonoEntity(0, tlf1Client, clienteId)
            val telefono2 = TelefonoEntity(0, tlf2Client, clienteId)
            // Insertamos el cliente
            telefonoDao.insert(telefono1)
            telefonoDao.insert(telefono2)

            // Obtenemos todos los clientes y sus telefonos
            lista = clienteDao.getClientesTelefonos();
            lista.forEach { registro ->
                Log.i("DB_LISTA", "Cliente: ${registro.cliente.nombre} - Teléfonos: ${registro.telefonos.size}")
                Log.i("DB_LISTA", "Teléfonos de ${registro.cliente.nombre}: ${registro.telefonos}")
            }
        }

        Toast.makeText(this,"Todo cargado", Toast.LENGTH_LONG).show()
    }

    fun mensajeToast(view: View) {
        Toast.makeText(this, "Próximamente...", Toast.LENGTH_LONG).show()
        Log.i("NEXT", "Funcionalidad no añadida")
    }
}