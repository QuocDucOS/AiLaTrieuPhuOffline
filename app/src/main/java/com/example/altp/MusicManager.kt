package com.example.altp

import android.content.Context
import android.media.MediaPlayer
import java.util.logging.Handler
import kotlin.random.Random

class MusicManager {
    private var arr_Music = intArrayOf(
        R.raw.ques1,
        R.raw.ques2_b,
        R.raw.ques3,
        R.raw.ques4_b,
        R.raw.ques5,
        R.raw.ques6,
        R.raw.ques7_b,
        R.raw.ques8,
        R.raw.ques9_b,
        R.raw.ques10,
        R.raw.ques11,
        R.raw.ques12,
        R.raw.ques13,
        R.raw.ques14,
        R.raw.ques15
    )
    private var arr_Music2 = intArrayOf(
        R.raw.ques1_b,
        R.raw.ques2,
        R.raw.ques3_b,
        R.raw.ques4,
        R.raw.ques5_b,
        R.raw.ques6,
        R.raw.ques7_b,
        R.raw.ques8_b,
        R.raw.ques9,
        R.raw.ques10,
        R.raw.ques11,
        R.raw.ques12,
        R.raw.ques13,
        R.raw.ques14,
        R.raw.ques15
    )
    private var context: Context? = null
    private var media1: MediaPlayer? = null
    private var media2: MediaPlayer? = null
    private var media3: MediaPlayer? = null
    private var media4: MediaPlayer? = null
    private var mediaA: MediaPlayer? = null
    private var handler = android.os.Handler()

    constructor(context: Context) {
        this.context = context
    }

    fun startMusicBackground() {

        media1 = MediaPlayer.create(context, R.raw.bgmusic)
        media1!!.isLooping = true
        media1!!.start()
    }

    fun startFragment2() {
        media2 = MediaPlayer.create(context, R.raw.luatchoi_b)
        media2!!.start()
        media3 = MediaPlayer.create(context, R.raw.ready_c)
        handler.postDelayed(Runnable {
            media3!!.start()
        }, 8000)
    }

    fun start15qs(position: Int) {

        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            media4 = MediaPlayer.create(context, arr_Music[position])
            media4!!.start()
        } else {
            media4 = MediaPlayer.create(context, arr_Music2[position])
            media4!!.start()
        }
    }

    fun stopfr1() {
        media1!!.stop()
    }

    fun stopfr2() {
        media2!!.stop()
        media3!!.stop()
    }

    fun readAnswerA() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.ans_a)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.ans_a2)
            mediaA!!.start()
        }
    }

    fun readAnswerB() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.ans_b)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.ans_b2)
            mediaA!!.start()
        }
    }

    fun readAnswerC() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.ans_c)

            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.ans_c2)
            mediaA!!.start()
        }
    }

    fun readAnswerD() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.ans_d)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.ans_d2)
            mediaA!!.start()
        }
    }

    fun readTrueA() {
        var rd = java.util.Random()
        var a = rd.nextInt(3) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.true_a)
            mediaA!!.start()
        } else if (a == 2) {
            mediaA = MediaPlayer.create(context, R.raw.true_a2)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.true_a3)
            mediaA!!.start()
        }
    }

    fun readTrueB() {
        var rd = java.util.Random()
        var a = rd.nextInt(3) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.true_b)
            mediaA!!.start()
        } else if (a == 2) {
            mediaA = MediaPlayer.create(context, R.raw.true_b2)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.true_b3)
            mediaA!!.start()
        }
    }

    fun readTrueC() {
        var rd = java.util.Random()
        var a = rd.nextInt(3) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.true_c)
            mediaA!!.start()
        } else if (a == 2) {
            mediaA = MediaPlayer.create(context, R.raw.true_c2)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.true_c3)
            mediaA!!.start()
        }
    }

    fun readTrueD() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.true_d2)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.true_d3)
            mediaA!!.start()
        }
    }

    fun readFalseA() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.lose_a)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.lose_a2)
            mediaA!!.start()
        }
    }

    fun readFalseB() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.lose_b)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.lose_b2)
            mediaA!!.start()
        }
    }

    fun readFalseC() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.lose_c)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.lose_c2)
            mediaA!!.start()
        }
    }

    fun readFalseD() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.lose_d)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.lose_d2)
            mediaA!!.start()
        }
    }

    fun callHelp() {
        mediaA = MediaPlayer.create(context, R.raw.help_call)
        mediaA!!.start()
    }

    fun callAudience() {
        mediaA = MediaPlayer.create(context, R.raw.khan_gia)
        mediaA!!.start()
    }

    fun call50percent() {
        var rd = java.util.Random()
        var a = rd.nextInt(2) + 1
        if (a == 1) {
            mediaA = MediaPlayer.create(context, R.raw.sound5050)
            mediaA!!.start()
        } else {
            mediaA = MediaPlayer.create(context, R.raw.sound5050_2)
            mediaA!!.start()
        }
    }


}