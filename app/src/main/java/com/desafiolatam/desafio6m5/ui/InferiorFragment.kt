package com.desafiolatam.desafio6m5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.desafiolatam.desafio6m5.R
import com.desafiolatam.desafio6m5.databinding.FragmentInferiorBinding
import com.desafiolatam.desafio6m5.viewmodel.MainViewModel
import kotlinx.coroutines.launch


class InferiorFragment : Fragment() {
    private var _binding: FragmentInferiorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInferiorBinding.inflate(inflater, container, false)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.puntajeJugador2.collect { score ->
                binding.tvContadorInferior.text = score.toString()
            }
        }

        viewModel.gameOver.observe(viewLifecycleOwner) { isGameOver ->
            if (isGameOver) {
                viewModel.winner.observe(viewLifecycleOwner) { winner ->
                    if (winner == "Player 2") {
                        binding.btnIncrementarInferior.text = getString(R.string.ganador)
                    } else {
                        binding.btnIncrementarInferior.text = getString(R.string.perdedor)
                    }
                }
            }
        }



        binding.btnIncrementarInferior.setOnClickListener {
            viewModel.incrementPlayer2Score()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}