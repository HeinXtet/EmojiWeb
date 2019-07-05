package com.heinhtet.repository

import com.heinhtet.model.EmojiPhase
import java.lang.IllegalArgumentException
import java.util.concurrent.atomic.AtomicInteger

class InMemoryRepository : Repository {

    private val emojiPhases = ArrayList<EmojiPhase>()
    private val idCounter = AtomicInteger()

    override suspend fun add(emojiPhase: EmojiPhase): EmojiPhase {
        return if (emojiPhases.contains(emojiPhase)) {
            emojiPhases.find { it.id == emojiPhase.id }!!
        } else {
            emojiPhase.id = idCounter.incrementAndGet()
            this.emojiPhases.add(emojiPhase)
            emojiPhase
        }
    }

    override suspend fun pharse(id: Int): EmojiPhase? = pharse(id.toString())

    override suspend fun pharse(id: String): EmojiPhase? {
        return emojiPhases.find { id == id } ?: throw IllegalArgumentException("no phase found for id $id")
    }

    override suspend fun pharses(): ArrayList<EmojiPhase> = emojiPhases

    override suspend fun remove(emojiPhase: EmojiPhase) {
        if (!emojiPhases.contains(emojiPhase)) {
            throw IllegalArgumentException("no phase found for id ${emojiPhase.id}")
        }
        emojiPhases.remove(emojiPhase)
    }

    override suspend fun remove(id: Int): Boolean = emojiPhases.remove(pharse(id))
    override suspend fun remove(id: String): Boolean = emojiPhases.remove(pharse(id))

    override suspend fun clear() {
        emojiPhases.clear()
    }

}