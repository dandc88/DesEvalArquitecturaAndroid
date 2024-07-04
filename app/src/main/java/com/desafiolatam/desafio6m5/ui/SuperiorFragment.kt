package com.desafiolatam.desafio6m5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.desafiolatam.desafio6m5.R
import com.desafiolatam.desafio6m5.databinding.FragmentSuperiorBinding
import com.desafiolatam.desafio6m5.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class SuperiorFragment : Fragment() {

    private var _binding: FragmentSuperiorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperiorBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.puntajeJugador1.collect { score ->
                binding.tvContadorSuperior.text = score.toString()
            }
        }

        viewModel.gameOver.observe(viewLifecycleOwner) { isGameOver ->
            if (isGameOver) {
                viewModel.winner.observe(viewLifecycleOwner) { winner ->
                    if (winner == "Player 1") {
                        binding.btnIncrementarSuperior.text =  getString(R.string.ganador)
                    } else {
                        binding.btnIncrementarSuperior.text =  getString(R.string.perdedor)
                    }
                }
            }
        }

        binding.btnIncrementarSuperior.setOnClickListener {
            viewModel.incrementPlayer1Score()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}