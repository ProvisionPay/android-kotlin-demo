package com.example.entegrationdeeplink.service

import com.example.entegrationdeeplink.IDeeplinkHostListener
import com.example.entegrationdeeplink.model.AuthenticationRequest
import com.example.entegrationdeeplink.model.AuthenticationResponse
import com.example.entegrationdeeplink.model.CreatePaymentSessionTokenRequest
import com.example.entegrationdeeplink.model.CreatePaymentSessionTokenResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DeeplinkApi(val your_url_service:String,val listener:IDeeplinkHostListener) {
    private val gsonBuilder = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .setLenient()
        .serializeNulls()
        .create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(your_url_service)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
        .build()

    var iDeeplinkService = retrofit.create(IDeeplinkService::class.java)

    fun callAuthenticateAndCreatePaymentSessionService() {
        val callAuthentication: Call<AuthenticationResponse?>? =
            iDeeplinkService.authenticate(AuthenticationRequest("applicationLoginId", "password"))
        callAuthentication!!.enqueue(object : Callback<AuthenticationResponse?> {
            override fun onResponse(
                call: Call<AuthenticationResponse?>,
                response: Response<AuthenticationResponse?>
            ) {
                if (response.code() == 200) {
                    callCreatePaymentSessionService()
                }
            }

            override fun onFailure(call: Call<AuthenticationResponse?>, t: Throwable) {}
        })
    }

    fun callCreatePaymentSessionService() {
        val callSessionId: Call<CreatePaymentSessionTokenResponse?>? =
            iDeeplinkService.createpaymentsessiontoken(
                "your_token",
                CreatePaymentSessionTokenRequest()
            )
        callSessionId!!.enqueue(object : Callback<CreatePaymentSessionTokenResponse?> {
            override fun onResponse(
                call: Call<CreatePaymentSessionTokenResponse?>,
                response: Response<CreatePaymentSessionTokenResponse?>
            ) {
                if (response.code() == 200) {
                    listener.onCallSoftPosEvent("your_paymentSessionToken")
                } else if (response.code() == 500) {
                    listener.onError("response code 500")
                }
            }

            override fun onFailure(call: Call<CreatePaymentSessionTokenResponse?>, t: Throwable) {}
        })
    }
}