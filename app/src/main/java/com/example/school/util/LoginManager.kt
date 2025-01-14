package com.arramton.bitewe.utils

import android.content.Context
import android.content.SharedPreferences

class LoginManager(val context: Context) {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    private val mode = 0

    init {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, mode)
        editor = sharedPreferences.edit()
    }

    fun removeSharedPreference() {
        editor.clear().apply()
    }

    fun setLoginStatus(isFirstTime: Boolean) {
        editor.putBoolean(IS_LOGGED_IN, isFirstTime).apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false)
    }

    fun setLocationLongitude(longitude: String) {
        editor.putString(LOCATION_LONGITUDE, longitude).apply()
    }

    fun getLocationLongitude(): String? {
        return sharedPreferences.getString(LOCATION_LONGITUDE, "")
    }

    fun setLocationLatitude(latitude: String) {
        editor.putString(LOCATION_LATITUDE, latitude).apply()
    }

    fun getLocationLatitude(): String? {
        return sharedPreferences.getString(LOCATION_LATITUDE, "")
    }

    fun setFcmToken(fcmToken: String) {
        editor.putString(FCM_TOKEN, fcmToken)
        editor.putBoolean("fcmToken", true)
        editor.apply()
    }

    fun userWantWhatsappNotification(): Boolean {
        return sharedPreferences.getBoolean(WANT_WHATSAPP_NOTIFICATION, false)
    }

    fun setUserWhatsappNotification(value: Boolean) {
        editor.putBoolean(WANT_WHATSAPP_NOTIFICATION, value).apply()
    }

    fun getFcmToken(): String? {
        return sharedPreferences.getString(FCM_TOKEN, "")
    }

    fun setToken(tok: String) {
        editor.putString(TOKEN, tok).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(TOKEN, "")
    }

    fun setAddressSelected(value: Boolean) {
        editor.putBoolean(IS_ADDRESS_SELECTED, value).apply()
    }

    fun setGst(value: String?){
        editor.putString(GST,value).apply()
    }

    fun getGST(): String?{
        return sharedPreferences.getString(GST,"")
    }

    fun setFssai(value: String?){
        editor.putString(FSSAI,value).apply()
    }

    fun getFssai(): String?{
        return sharedPreferences.getString(FSSAI,"")
    }

    fun getAddressSelected(): Boolean {
        return sharedPreferences.getBoolean(IS_ADDRESS_SELECTED, false)
    }

    fun setAcceptTermsAndCondition(value: Boolean) {
        editor.putBoolean(ACCEPT_TERMS_AND_CONDITION, value).apply()
    }

    fun isAcceptTermsAndCondition(): Boolean {
        return sharedPreferences.getBoolean(ACCEPT_TERMS_AND_CONDITION, false)
    }

    fun setPhone(value: String?) {
        editor.putString(PHONE, value).apply()
    }

    fun getPhone(): String? {
        return sharedPreferences.getString(PHONE, null)
    }

    fun setWhatsappNo(value: String?) {
        editor.putString(WHATSAPP, value).apply()
    }

    fun getWhatsappNo(): String? {
        return sharedPreferences.getString(WHATSAPP, null)
    }

    private var instance: LoginManager? = null

    fun getInstance(context: Context): LoginManager {
        if (instance == null) {
            instance = LoginManager(context)
        }
        return instance!!
    }

    companion object {
        private const val PREFERENCE_NAME = "loginManager"
        private const val IS_LOGGED_IN = "isLogin"
        private const val TOKEN = "token"
        private const val PHONE = "phone"
        private const val GST = "gst"
        private const val FSSAI = "fssai"
        private const val WHATSAPP = "whatsapp"
        private const val IS_ADDRESS_SELECTED = "is_address_selected"
        private const val FCM_TOKEN = "fcmToken"
        private const val LOCATION_LATITUDE = "latitude"
        private const val LOCATION_LONGITUDE = "longitude"
        private const val ACCEPT_TERMS_AND_CONDITION = "acceptTermsAndCondition"
        private const val WANT_WHATSAPP_NOTIFICATION = "whatsapp_notification"
    }

}
