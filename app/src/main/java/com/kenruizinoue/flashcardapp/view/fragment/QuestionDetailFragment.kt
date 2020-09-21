package com.kenruizinoue.flashcardapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDatabase
import com.kenruizinoue.flashcardapp.viewModel.BaseViewModelFactory
import com.kenruizinoue.flashcardapp.viewModel.QuestionDetailViewModel

class QuestionDetailFragment : Fragment() {

    private lateinit var questionCardViewModel: QuestionDetailViewModel
    private lateinit var questionEditText: EditText
    private lateinit var answerEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val questionId = arguments?.let { QuestionDetailFragmentArgs.fromBundle(it).questionId }!!
        questionCardViewModel
            .getQuestionById(questionId).observe(viewLifecycleOwner, { drawUI(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionEditText = view.findViewById(R.id.questionEditText)
        answerEditText = view.findViewById(R.id.answerEditText)

        // manual dependency injection
        val application = requireNotNull(this.activity).application
        val questionDao = QuestionCardDatabase.getQuestionCardDB(application).questionCardDao()
        val baseViewModel = BaseViewModelFactory { QuestionDetailViewModel(questionDao) }
        questionCardViewModel = ViewModelProvider(this, baseViewModel)
            .get(QuestionDetailViewModel::class.java)
    }

    private fun drawUI(card: QuestionCard) {
        questionEditText.setText(card.question)
        answerEditText.setText(card.answer)
    }

}