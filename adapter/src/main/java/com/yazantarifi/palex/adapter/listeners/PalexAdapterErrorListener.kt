package com.yazantarifi.palex.adapter.listeners

import java.lang.Exception

/**
 * This Callback Used to Handle Un Expected Exceptions and Return The Errors
 * To Callback To Log Them or Do a Fallback Action
 */
interface PalexAdapterErrorListener {

    fun onErrorAttached(exception: Exception)

}