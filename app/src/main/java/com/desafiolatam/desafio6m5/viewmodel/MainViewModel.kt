package com.desafiolatam.desafio6m5.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _puntajejugador1 = MutableStateFlow(10)
    private val _puntajejugador2 = MutableStateFlow(10)
    private val _gameOver = MutableLiveData(false)
    private val _winner = MutableLiveData<String?>(null)

    val puntajeJugador1: MutableStateFlow<Int> = _puntajejugador1
    val puntajeJugador2: MutableStateFlow<Int> = _puntajejugador2
    val gameOver: LiveData<Boolean> = _gameOver
    val winner: LiveData<String?> = _winner

    private fun checkGameOver() {
        if ((_puntajejugador1.value ?: 0) >= 20) {
            _gameOver.value = true
            _winner.value = "Player 1"
        } else if ((_puntajejugador2.value ?: 0) >= 20) {
            _gameOver.value = true
            _winner.value = "Player 2"
        }
    }



    fun incrementPlayer1Score() {
        if (_gameOver.value == true) return
        _puntajejugador1.value = (_puntajejugador1.value ?: 0) + 1
        _puntajejugador2.value = (_puntajejugador2.value ?: 0) - 1
        checkGameOver()
    }

    fun incrementPlayer2Score() {
        if (_gameOver.value == true) return
        _puntajejugador2.value = (_puntajejugador2.value ?: 0) + 1
        _puntajejugador1.value = (_puntajejugador1.value ?: 0) - 1
        checkGameOver()
    }
}