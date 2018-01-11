package io.github.benjohnde.ankeader.generator

import io.github.benjohnde.ankeader.anki.AnkiCard

import java.io.IOException

class DocGenerator(private val cards: List<AnkiCard>) {
    @Throws(IOException::class)
    fun gen(): String {
        val sb = StringBuilder()
        injectHeader(sb)
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
        injectFooter(sb)
        return sb.toString()
    }

    private fun injectHeader(sb: StringBuilder) {
        sb.append("<html><head>")
        sb.append("<link href=\"https://fonts.googleapis.com/css?family=Proza+Libre\" rel=\"stylesheet\">")
        sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n")
        sb.append("<meta charset=\"utf-8\">")
        sb.append("<style>")
        sb.append("body { font-family: 'Proza Libre', sans-serif; }")
        sb.append("div { margin: 0 auto; width: 99vw; }")
        sb.append("img { max-width: 99vw; }")
        sb.append("</style>")
        sb.append("</head><body><div id=\"container\"")
    }

    private fun injectFooter(sb: StringBuilder) {
        sb.append("</div></body></html>")
    }
}
