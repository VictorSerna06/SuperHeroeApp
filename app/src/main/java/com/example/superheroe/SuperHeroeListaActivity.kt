package com.example.superheroe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.superheroe.databinding.ActivitySuperHeroeListaBinding
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroe.DatosSuperHeroeActivity.Companion.EXTRA_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroeListaActivity : AppCompatActivity() {

    // Binding
    private lateinit var binding: ActivitySuperHeroeListaBinding

    // Retrofit
    private lateinit var retrofit: Retrofit

    // Adapter
    private lateinit var adapter: superHeroeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroeListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Se llama a la función Retrofit
        retrofit = getRetrofit()

        // inicializamos los componentes
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            // Se activa al momento de pulsar el botón de buscar
            override fun onQueryTextSubmit(query: String?): Boolean {
                buscarNombre(query.orEmpty())
                return false
            }

            // Se activa al momento de escribir
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        // Creamos el adapter y usamos la función lambda
        adapter = superHeroeAdapter { id -> navegarDatos(id) }
        binding.rvSuperHeroe.setHasFixedSize(true)
        binding.rvSuperHeroe.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHeroe.adapter = adapter
    }

    // Buscar nombre
    private fun buscarNombre(query: String) {
        // ProgreesBar
        binding.progressBar.isVisible = true
        // Se realiza en un hilo secundario
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<superHeroeDataRespuesta> =
                retrofit.create(ApiService::class.java).getSuperHeroes(query)
            if (myResponse.isSuccessful) {
                val response: superHeroeDataRespuesta? = myResponse.body()
                if (response != null) {
                    // Ocultamos el elemento ProgressBar
                    withContext(Dispatchers.Main) {
                        adapter.updateList(response.superHeroes)
                        binding.progressBar.isVisible = false
                    }
                }
            } else {

            }
        }
    }

    // Llamada a Retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Pantalla de super heroe
    private fun navegarDatos(id: String) {
        val intent = Intent(this, DatosSuperHeroeActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }

    private fun error(){
    }
}

