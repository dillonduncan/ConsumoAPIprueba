package com.example.consumoapiprueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import com.example.consumoapiprueba.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),OnQueryTextListener {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.svBuscarPerros.setOnQueryTextListener(this)
    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://localhost:44388/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getSuperHeroPowerForiD(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call =
                getRetrofit().create(APIServices::class.java).GetSuperHeroPower("Superpoderes/$id")
            val pawer = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    binding.txtIdPower.text = pawer!!.codigoPoder.toString()
                    binding.txtNamePower.text = pawer!!.namePoder
                } else {
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            getSuperHeroPowerForiD(query.toInt())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}