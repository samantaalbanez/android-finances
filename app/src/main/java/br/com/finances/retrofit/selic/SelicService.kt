package br.com.finances.retrofit.selic

import br.com.finances.model.Selic
import retrofit2.http.GET

interface SelicService {

    @GET("dados")
    suspend fun getSelicToday(): List<Selic>

}