package com.kenruizinoue.flashcardapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.di.MyApplication
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.viewModel.PracticeViewModel
import kotlinx.android.synthetic.main.fragment_practice.*
import javax.inject.Inject

class PracticeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var practiceViewModel: PracticeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_practice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        practiceViewModel = ViewModelProvider(this, viewModelFactory)
            .get(PracticeViewModel::class.java)

        practiceFab.setOnClickListener { practiceViewModel.fabPressed() }
        practiceViewModel.fetchQuestionCards()
        practiceViewModel.isAnswerTextVisible.observe(viewLifecycleOwner, { updateVisibility(it) })
        practiceViewModel.actualQuestionCard.observe(viewLifecycleOwner, { updateQuestion(it) })
    }

    private fun updateVisibility(showAnswerText: Boolean) {
        if (showAnswerText) {
            answerTextView.visibility = View.VISIBLE
            practiceFab.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_arrow_next) })
        } else {
            answerTextView.visibility = View.GONE
            practiceFab.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_view_answer) })
        }
    }

    private fun updateQuestion(questionCard: QuestionCard?) {
        if (questionCard != null) {
            questionTextView.text = questionCard.question
            answerTextView.text = questionCard.answer
        }
    }

}