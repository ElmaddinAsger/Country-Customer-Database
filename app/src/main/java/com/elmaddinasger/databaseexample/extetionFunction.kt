package com.elmaddinasger.databaseexample

fun String.isNotNullBlankOrEmpty () : Boolean {
    return if (!this.isNullOrBlank() && !this.isNullOrEmpty()) true else false
}