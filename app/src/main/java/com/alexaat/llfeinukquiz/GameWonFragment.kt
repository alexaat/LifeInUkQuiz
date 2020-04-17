package com.alexaat.llfeinukquiz


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.alexaat.llfeinukquiz.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {

    var numQuestions = 0
    var numCorrect = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentGameWonBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)

        binding.buttonPracticeAgain.setOnClickListener {
            it.findNavController().navigate(R.id.action_gameWonFragment_to_testFragment)
        }

        numQuestions = GameWonFragmentArgs.fromBundle(arguments!!).numQuestions
        numCorrect = GameWonFragmentArgs.fromBundle(arguments!!).numCorrect

        //enable menu
        setHasOptionsMenu(true)

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.game_won_fragment_title)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_game_won, menu)
        setShareItemVisibility(menu.findItem(R.id.share))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareSuccess(){
        val mimeType = "text/plain"
        val text = "Number Of Questions: $numQuestions. Answered: $numCorrect"
        val shareIntent = ShareCompat.IntentBuilder.from(activity!!)
            .setType(mimeType)
            .setChooserTitle(getString(R.string.select_share_option))
            .setText(text)
            .createChooserIntent()
        startActivity(shareIntent)
    }

    private fun setShareItemVisibility(item: MenuItem){
        val intent = Intent()
        intent.type = "text/plain"
        intent.action = Intent.ACTION_SEND
        val packageManager = getActivity()!!.packageManager
        if(intent.resolveActivity(packageManager)==null){
            item.isVisible = false
        }
    }
}
