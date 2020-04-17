package com.alexaat.llfeinukquiz


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.alexaat.llfeinukquiz.databinding.FragmentGameOverBinding


class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameOverBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_game_over, container, false)
        binding.buttonTryAgain.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameOverFragment_to_testFragment)
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.game_over_fragment_title)

        return binding.root
    }


}
