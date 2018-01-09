package io.github.benjohnde.ankeader.generator

import io.github.benjohnde.ankeader.anki.AnkiCard

import java.io.IOException

class DocGenerator(private val cards: List<AnkiCard>) {
    @Throws(IOException::class)
    fun gen(): String {
        val sb = StringBuilder()
        this.cards.forEach({
            val qst = it.question.replace("<img src=\"", "<img src=\"media/")
            val asw = it.answer.replace("<img src=\"", "<img src=\"media/")
            sb.append("<h4>#" + it.tags + "</h4>")
            sb.append("<h3>Frage</h3>")
            sb.append(qst)
            sb.append("<h3>Antwort</h3>")
            sb.append(asw)
            sb.append("<hr>")
        })
        return sb.toString()
    }
}
