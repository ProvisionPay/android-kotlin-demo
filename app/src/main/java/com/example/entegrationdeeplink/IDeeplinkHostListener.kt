package com.example.entegrationdeeplink

interface IDeeplinkHostListener {
    fun onCallSoftPosEvent(paymentSessionToken: String?)
    fun onError(error: String?)
}