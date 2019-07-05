package com.heinhtet.repository

import com.heinhtet.model.EmojiPhase

interface Repository {

    suspend fun add(emojiPhase: EmojiPhase): EmojiPhase
    suspend fun pharse(id: Int): EmojiPhase?
    suspend fun pharse(id: String): EmojiPhase?
    suspend fun pharses(): ArrayList<EmojiPhase>
    suspend fun remove(emojiPhase: EmojiPhase)
    suspend fun remove(id: Int): Boolean
    suspend fun remove(id: String): Boolean
    suspend fun clear()

}