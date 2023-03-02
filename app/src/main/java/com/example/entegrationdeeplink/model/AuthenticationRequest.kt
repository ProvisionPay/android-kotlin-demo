package com.example.entegrationdeeplink.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthenticationRequest(
    @SerializedName("ApplicationLoginID") @Expose
    private var ApplicationLoginID: String? = null,

    @SerializedName("Password")
    @Expose
    private var Password: String? = null

)
