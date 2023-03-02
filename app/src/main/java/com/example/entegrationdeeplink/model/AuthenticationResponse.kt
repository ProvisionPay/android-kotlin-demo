package com.example.entegrationdeeplink.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("Token") @Expose
    private var Token: String? = null
)
