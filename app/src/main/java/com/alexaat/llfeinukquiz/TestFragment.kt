package com.alexaat.llfeinukquiz


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.alexaat.llfeinukquiz.databinding.FragmentTestBinding



class TestFragment : Fragment() {

     var listSize: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentTestBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_test, container, false)

        var index = 0


        val listOfQuestions = getQuestions()

        listSize = listOfQuestions.size

        applyList(listOfQuestions,binding,index)

        val numQuestions = listOfQuestions.size

        binding.nextButton.setOnClickListener {
            if(answerIsCorrect(binding.answersRadioGroup.checkedRadioButtonId)){
               index++
                if(index>=3){
                    val numCorrect = index
                    index = 0
                    val action = TestFragmentDirections.actionTestFragmentToGameWonFragment(numQuestions,numCorrect)
                    it.findNavController().navigate(action)
                }else {

                    applyList(listOfQuestions, binding, index)
                }
            }else{
                it.findNavController().navigate(R.id.action_testFragment_to_gameOverFragment)
            }
        }
        return binding.root
    }


    private fun getQuestions(): ArrayList<Question>{
        val list = ArrayList<Question>()
        list.add(
            Question("Who were the first people to arrive in Britain in what we call the Stone Age?",
            listOf("Farmers","Hunter-gatherers","Warriors","Pirates"),
            Correct.B))
        list.add(Question("Which flag has a diagonal red cross on a white ground?",
                listOf("The cross of St George, patron saint of England",
                    "The cross of St Patrick, patron saint of Ireland",
                    "The cross of St David, patron saint of Wales",
                    "The cross of St Andrew, patron saint of Scotland"),
             Correct.B))
        list.add(Question("When did the Wars of the Roses start?",
            listOf("1388",
                "1455",
                "1462",
                "1478"),
            Correct.B)
        )

        return list
    }

    private fun applyList(listOfQuestions:ArrayList<Question>, binding:FragmentTestBinding, index:Int){

        val question = listOfQuestions[index]

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.test_fragment_title,index+1, listSize)

            binding.apply {
                binding.answersRadioGroup.clearCheck()
                questionText.text = question.question
                answerARadioButton.text = question.answers[0]
                answerBRadioButton.text = question.answers[1]
                answerCRadioButton.text = question.answers[2]
                answerDRadioButton.text = question.answers[3]
            }
    }

    private fun answerIsCorrect(id:Int):Boolean{
        if(id == R.id.answer_b_radio_button){
            return true
        }
        return false
    }
}
