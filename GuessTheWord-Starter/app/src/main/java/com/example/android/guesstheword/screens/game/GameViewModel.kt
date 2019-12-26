package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel: ViewModel() {

    // The current word
    private val word = MutableLiveData<String>()
    val _word:LiveData<String>
    get() = word



    // The current score
    private val score = MutableLiveData<Int>()
    val _score:LiveData<Int>
    get() = score


    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private val gf=MutableLiveData<Boolean>()
    val _gf:LiveData<Boolean>
    get()=gf


    init {
        Log.i("gvm","Game View Model created")
        gf.value=false
        resetList()
        nextWord()
        score.value=0

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("gvm","Game View Model Destroyed")
    }
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            word.value = (wordList).removeAt(0)
        }
        else{
           // gameFinished()
            gf.value=true
        }

    }
  fun onSkip() {

        score.value=(score.value)?.minus(1)

        nextWord()
    }

  fun onCorrect() {
        score.value=(score.value)?.plus(1)
        nextWord()
    }
    fun ongamecomplete(){
        gf.value=false
    }
}