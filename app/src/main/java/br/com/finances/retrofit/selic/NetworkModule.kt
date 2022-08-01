package br.com.finances.retrofit.selic

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    inline fun <reified T> createNetworkService(): T {

        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient
            .Builder()
            .addInterceptor { chain ->

                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                //https://api.bcb.gov.br/dados/serie/bcdata.sgs.432/dados?formato=json&dataInicial=01/07/2022&dataFinal=31/07/2022
                //https://api.bcb.gov.br/dados/serie/bcdata.sgs.432/dados?formato=json&dataInicial=23/07/2022&dataFinal=23/07/2022
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("formato", "json")
                    .addQueryParameter("dataInicial", "23/07/2022")
                    .addQueryParameter("dataFinal", "23/07/2022")
                    .build()

                val newRequest = originalRequest.newBuilder().url(newUrl).build()

                chain.proceed(
                    newRequest
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build()
                )
            }

            .addInterceptor(log)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.bcb.gov.br/dados/serie/bcdata.sgs.432/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()

        return retrofit.create(T::class.java)
    }
}