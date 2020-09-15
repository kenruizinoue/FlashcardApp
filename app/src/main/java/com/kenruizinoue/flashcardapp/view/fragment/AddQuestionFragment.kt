package com.kenruizinoue.flashcardapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.viewModel.AddQuestionViewModel


class AddQuestionFragment : Fragment() {

    private val addQuestionViewModel: AddQuestionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton = view.findViewById<Button>(R.id.saveQuestionButton)
        saveButton.setOnClickListener {
            addQuestionViewModel.startInsert()
            findNavController().navigate(R.id.questionListDest, null)
        }
    }

}