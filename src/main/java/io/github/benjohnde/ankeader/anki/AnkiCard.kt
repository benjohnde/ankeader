package io.github.benjohnde.ankeader.anki

data class AnkiCard(val id: Long, val tags: List<String>, val question: String, val answer: String)
