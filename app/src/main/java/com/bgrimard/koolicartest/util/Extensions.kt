package com.bgrimard.koolicartest.util

import com.google.gson.Gson

class Extensions {
    interface JSONSerializable {
        fun toJSON(): String = Gson().toJson(this)
    }

    inline fun <reified T: JSONSerializable> String.toObject(): T = Gson().fromJson(this, T::class.java)
}