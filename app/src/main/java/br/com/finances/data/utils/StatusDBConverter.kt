package br.com.finances.data.utils

fun Long.toStatusDB(): StatusDataBase {
    return when(this) {
        StatusDataBase.SUCCESS.ordinal.toLong() -> StatusDataBase.SUCCESS
        else -> StatusDataBase.IDLE
    }
}
fun Int.toStatusDB(): StatusDataBase {
    return when(this) {
        1 -> StatusDataBase.SUCCESS
        else -> StatusDataBase.IDLE
    }
}
