package br.com.finances.data.retrofit.selic

import br.com.finances.domain.model.Selic
import retrofit2.http.GET

interface SelicService {

    @GET("dados")
    suspend fun getSelicToday(): List<Selic>

}