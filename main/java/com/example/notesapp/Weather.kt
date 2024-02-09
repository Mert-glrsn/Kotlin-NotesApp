package com.example.notesapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.databinding.ActivityWeatherBinding
import com.example.notesapp.utils.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import java.text.SimpleDateFormat
import java.util.Locale

class Weather : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCurrentWeather()
    }

    private fun getCurrentWeather() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather("ankara", "metric", "b9932ecbe28b5d4a75c4d5aaa619fc8c")
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_SHORT).show()
                }
                return@launch
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "http error ${e.message}", Toast.LENGTH_SHORT).show()
                }
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
val data =response.body()!!
                    val iconId = data.weather[0].icon
                    val imgUrl = "https://openweathermap.org/img/w/$iconId.png"
                    Picasso.get().load(imgUrl).into(binding.imgWeather)

                    binding.tvSunrise.text = SimpleDateFormat("hh:mm a" , Locale.ENGLISH).format(data.sys.sunrise * 1000)
                    binding.tvSunset.text = SimpleDateFormat("hh:mm a" , Locale.ENGLISH).format(data.sys.sunset * 1000)
                    binding.apply {
                        tvStatus.text=data.weather[0].description
                        tvWind.text= "${ data.wind.speed.toString() } KM/H"
                        tvLocation.text = "${data.name}\n${data.sys.country}"
                        tvTemp.text = "${data.main.temp} °C"
                        tvFeelsLike.text = "Feels Like: ${data.main.feels_like} °C"
                        tvMinTemp.text= "min temp: ${data.main.temp_min} °C"
                        tvMaxTemp.text= "max temp: ${data.main.temp_max} °C"
                        tvHumidity.text = "${data.main.humidity} %"
                        tvPressure.text = "${data.main.pressure} hPa"
                        tvUpdateTime.text = "Last Update: ${SimpleDateFormat("hh:mm a" , Locale.ENGLISH).format(data.dt * 1000)}"
                    }
                }
            }
        }
    }
}
