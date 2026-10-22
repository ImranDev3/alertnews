package com.alertnews.app

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import java.io.IOException

class MusicPlayer(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    var isPlaying = false
        private set

    fun playMusic(musicUrl: String, onStateChange: (Boolean) -> Unit) {
        stopMusic()
        try {
            val uri = Uri.parse(musicUrl)
            mediaPlayer = MediaPlayer().apply {
                setDataSource(context, uri)
                isLooping = true
                setOnPreparedListener { isPlaying = true; onStateChange(true) }
                setOnCompletionListener { isPlaying = false; onStateChange(false) }
                prepareAsync()
                start()
            }
        } catch (e: IOException) {
            isPlaying = false
            onStateChange(false)
        }
    }

    fun stopMusic(onStateChange: (Boolean) -> Unit) {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = null
        } catch (e: Exception) { }
        isPlaying = false
        onStateChange(false)
    }

    fun pauseMusic() {
        try { mediaPlayer?.pause() } catch (e: Exception) { }
        isPlaying = false
    }

    fun resumeMusic() {
        try { mediaPlayer?.start() } catch (e: Exception) { }
        isPlaying = true
    }

    fun release() {
        stopMusic { }
        mediaPlayer?.release()
        mediaPlayer = null
    }
}