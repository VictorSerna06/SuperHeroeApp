package com.example.superheroe

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.example.superheroe.databinding.ActivityDatosSuperHeroeBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DatosSuperHeroeActivity : AppCompatActivity() {

    // Accedemos a los datos de otras pantallas
    // Constante
    companion object {
        const val EXTRA_ID = "extra_id"
    }

    // Binding
    private lateinit var binding: ActivityDatosSuperHeroeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDatosSuperHeroeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Recuperamos el "id"
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()

        // Método para obtener información
        getInformation(id)
    }

    // Información del super heroe
    private fun getInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroe = getRetrofit().create(ApiService::class.java).getSuperHeroe(id)

            if (superHeroe.body() != null) {
                runOnUiThread {
                    createUI(superHeroe.body()!!)
                }
            }
        }
    }

    // Cargamos los elementos obtenidos
    private fun createUI(superHero: RespuestaInformacionSuperHeroe) {
        Picasso.get().load(superHero.imagen.url).into(binding.ivSuperheroe)
        binding.tvNombreSuperHeroe.text = superHero.nombre
        cargarEstadisticas(superHero.estadisticas)
        binding.tvNombreRealSuperHeroe.text = superHero.biografia.nombreCompleto
        binding.tvPrimeraAparicion.text = superHero.biografia.primeraAparicion
        binding.tvPublisher.text = superHero.biografia.editora

        // Acción del botón regresar
        binding.btnRegresar.setOnClickListener {
            onBackPressed()
        }
    }


    // Valores de las Estadisticas
    private fun cargarEstadisticas(estadisticas: RespuestaEstadisticas) {
        updateHeight(binding.vIntelligence, estadisticas.inteligencia)
        updateHeight(binding.vStrength, estadisticas.fuerza)
        updateHeight(binding.vSpeed, estadisticas.velocidad)
        updateHeight(binding.vDurability, estadisticas.durabilidad)
        updateHeight(binding.vPower, estadisticas.poder)
        updateHeight(binding.vCombat, estadisticas.combate)
    }

    // Parametros
    private fun updateHeight(view: View, stat: String) {
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    // Unidades de vista
    private fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics)
            .roundToInt()
    }

    // LLamada A la API
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}