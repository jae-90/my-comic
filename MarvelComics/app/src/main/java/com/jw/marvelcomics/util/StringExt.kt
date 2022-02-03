package com.jw.marvelcomics.util

import androidx.core.net.toUri
import java.math.BigInteger
import java.security.MessageDigest

fun String.convertToHttpsUriString(): String {
    return this.toUri().buildUpon()
        .scheme("https")
        .build()
        .toString()
}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray()))
        .toString(16).padStart(32, '0')
}