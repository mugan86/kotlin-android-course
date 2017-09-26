package amldev.kotlinfordevelopers.extensions

/**
 * Created by anartzmugika on 21/8/17.
 */
fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()