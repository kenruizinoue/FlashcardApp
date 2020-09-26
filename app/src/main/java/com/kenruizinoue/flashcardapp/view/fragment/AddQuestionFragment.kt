package com.kenruizinoue.flashcardapp.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.di.MyApplication
import com.kenruizinoue.flashcardapp.utils.hideKeyboard
import com.kenruizinoue.flashcardapp.viewModel.AddQuestionViewModel
import kotlinx.android.synthetic.main.fragment_add_question.*
import javax.inject.Inject

class AddQuestionFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var addQuestionViewModel: AddQuestionViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addQuestionViewModel = ViewModelProvider(this, viewModelFactory)
            .get(AddQuestionViewModel::class.java)

        saveQuestionButton.setOnClickListener {
            val dataAdded = addQuestionViewModel.startInsert(
                questionEditText.text.toString(),
                answerEditText.text.toString(),
                view,
                resources
            )

            if (dataAdded) {
                findNavController().navigate(R.id.questionListDest, null)
                hideKeyboard()
            }
        }
    }

}