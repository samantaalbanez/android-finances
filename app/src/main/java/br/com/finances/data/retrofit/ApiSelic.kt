package br.com.finances.data.retrofit

import br.com.finances.domain.model.Selic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiSelic {

    @GET("dados")
    fun getAnyThing(
        @Query(value = "formato") sortOrder: String?,
    ): Call<List<Selic>>
}