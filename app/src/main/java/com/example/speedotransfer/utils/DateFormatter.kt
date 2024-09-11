package com.example.speedotransfer.utils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun formatDate(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    return try {
        // Parse the date string
        val date: Date? = dateFormat.parse(dateString)
        date?.let {
            val now = Date()

            val formatter = SimpleDateFormat("HH:mm", Locale.US)
            val formattedTime = formatter.format(it)

            when {
                isSameDay(date, now) -> "Today $formattedTime"
                isYesterday(date, now) -> "Yesterday $formattedTime"
                else -> SimpleDateFormat("dd MMM yyyy HH:mm", Locale.US).format(it)
            }
        } ?: "Unknown date"
    } catch (e: ParseException) {
        println("Parsing error: ${e.message}")
        "Unknown date"
    }
}

fun isSameDay(date1: Date, date2: Date): Boolean {
    val calendar1 = java.util.Calendar.getInstance().apply { time = date1 }
    val calendar2 = java.util.Calendar.getInstance().apply { time = date2 }
    return calendar1.get(java.util.Calendar.YEAR) == calendar2.get(java.util.Calendar.YEAR) &&
            calendar1.get(java.util.Calendar.DAY_OF_YEAR) == calendar2.get(java.util.Calendar.DAY_OF_YEAR)
}

fun isYesterday(date1: Date, date2: Date): Boolean {
    val calendar1 = java.util.Calendar.getInstance().apply { time = date1 }
    val calendar2 = java.util.Calendar.getInstance().apply { time = date2 }
    calendar1.add(java.util.Calendar.DAY_OF_YEAR, 1)
    return calendar1.get(java.util.Calendar.YEAR) == calendar2.get(java.util.Calendar.YEAR) &&
            calendar1.get(java.util.Calendar.DAY_OF_YEAR) == calendar2.get(java.util.Calendar.DAY_OF_YEAR)
}

