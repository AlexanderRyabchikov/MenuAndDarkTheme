package org.skender.testapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val INPUT_DEFAULT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"
const val OUTPUT_DEFAULT_FORMAT = "dd.MM.yyyy HH:mm"

fun formatDate(date: String,
               inputFormatTemplate: String = INPUT_DEFAULT_FORMAT,
               outputFormatTemplate: String = OUTPUT_DEFAULT_FORMAT): String {

    val inputFormat = SimpleDateFormat(inputFormatTemplate, Locale.US)
    val outputFormat = SimpleDateFormat(outputFormatTemplate, Locale.US)

    return try {
        outputFormat.format(inputFormat.parse(date))
    } catch (e: ParseException) {
        "Некорректная дата"
    }
}