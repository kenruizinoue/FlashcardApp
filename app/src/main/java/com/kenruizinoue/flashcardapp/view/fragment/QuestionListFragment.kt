package com.kenruizinoue.flashcardapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.viewModel.QuestionListViewModel

class QuestionListFragment : Fragment() {

    private val questionListViewModel: QuestionListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addFab = view.findViewById<FloatingActionButton>(R.id.addFab)

        addFab.setOnClickListener {
            findNavController().navigate(R.id.addQuestionDest, null)
        }

        questionListViewModel.getUsers().observe(viewLifecycleOwner, { cards ->
            // add data to the recycler view
        })

    }
}