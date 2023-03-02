package com.example.entegrationdeeplink.service

import com.example.entegrationdeeplink.model.AuthenticationRequest
import com.example.entegrationdeeplink.model.AuthenticationResponse
import com.example.entegrationdeeplink.model.CreatePaymentSessionTokenRequest
import com.example.entegrationdeeplink.model.CreatePaymentSessionTokenResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IDeeplinkService {
    @POST("authorize")
    fun authenticate(@Body request: AuthenticationRequest?): Call<AuthenticationResponse?>?

    @POST("createpaymentsessiontoken")
    fun createpaymentsessiontoken(@Header("Authorization") token: String?, @Body request: CreatePaymentSessionTokenRequest?): Call<CreatePaymentSessionTokenResponse?>?
}