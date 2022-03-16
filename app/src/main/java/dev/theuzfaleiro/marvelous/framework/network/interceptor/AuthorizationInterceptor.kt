package dev.theuzfaleiro.marvelous.framework.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest.getInstance
import java.util.Calendar
import kotlin.time.Duration.Companion.seconds

private const val QUERY_PARAMETER_TIMESTAMP = "ts"
private const val QUERY_PARAMETER_API_KEY = "apikey"
private const val QUERY_PARAMETER_HASH = "hash"

private const val RADIX = 16
private const val HASH_LENGTH = 32

class AuthorizationInterceptor(
    private val publicKey: String,
    private val privateKey: String,
    private val calendar: Calendar
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.request()
        val requestUrl = response.url

        val timestamp = calendar.timeInMillis.seconds.toString()

        val hash = "$timestamp$privateKey$publicKey"
        val signature = hash.md5()

        val newUrl = requestUrl.newBuilder()
            .addQueryParameter(QUERY_PARAMETER_API_KEY, publicKey)
            .addQueryParameter(QUERY_PARAMETER_TIMESTAMP, timestamp)
            .addQueryParameter(QUERY_PARAMETER_HASH, signature)
            .build()

        val newRequest = response.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }

    private fun String.md5(): String {
        val messageDigest = getInstance("MD5")
        return BigInteger(1, messageDigest.digest(toByteArray())).toString(RADIX).padStart(
            HASH_LENGTH, '0'
        )
    }
}
