package br.com.mvclopes.gistcompose.utils

import android.graphics.Color
import android.os.Build
import br.com.mvclopes.gistcompose.model.domain.File
import br.com.mvclopes.gistcompose.model.domain.Gist
import br.com.mvclopes.gistcompose.model.domain.Owner
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun filterFiles(files: Collection<File>): MutableList<String> {
    val languages = mutableListOf<String>()
    files.forEach { file ->
        if (file.language != EMPTY_TEXT && !languages.contains(file.language)) languages.add(file.language)
    }
    return languages
}

fun tratativeDate(date: String): String {
    val strDate = date.replace("Z", EMPTY_TEXT)

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val parsedDate = LocalDateTime.parse(strDate, DateTimeFormatter.ISO_DATE_TIME)
        parsedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    } else {
        val year = strDate.substring(0, strDate.indexOf("-"))
        val month = strDate.substring(5, 7)
        val day = strDate.substring(8, strDate.indexOf('T'))
        "$day/$month/$year"
    }
}

fun tratativeFileSize(size: Long): String {
    val formatter = DecimalFormat("#.##")
    val megaBytes: Long = 1024 * 1024
    val kiloBytes: Long = 1024
    return if (size >= megaBytes){
        "${formatter.format(size / megaBytes)} MB"
    } else if (size >= kiloBytes) {
        "${formatter.format(size / kiloBytes)} kB"
    } else {
        "${formatter.format(size)} B"
    }
}

fun getRandomColor(): Int {
    val random = Random
    return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
}

// Mock functions
fun mockGist() = Gist(
        id = "ddf880fb9cbb52ecda5ef5a13df23143",
        description = "Awesome sotware to try",
        files = hashMapOf(),
        date = "2021-12-13T21:55:14Z",
        owner = Owner(
            id = 59021155,
            login = "joshpetit",
            avatarUrl = "https://avatars.githubusercontent.com/u/59021155?v=4",
            url = "https://api.github.com/users/joshpetit",
        ),
        isFavorite = false
    )

fun mockFile() = File(
        filename = "Hello world.md",
        type = "plain/text",
        language = "Kotlin",
        size = 1024
    )

fun mockListFiles() = listOf(
        File(
            filename = "Hello world.md",
            type = "plain/text",
            language = "Kotlin",
            size = 1024
        ),
        File(
            filename = "MyJson",
            type = "application/json",
            language = "JSON",
            size = 256
        ),
        File(
            filename = "TestPPCOMP.cpp",
            type = "plain/text",
            language = "C++",
            size = 1024 * 1024
        ),
        File(
            filename = "MyCalculator.py",
            type = "application/json",
            language = "Python",
            size = 958
        ),
    )
