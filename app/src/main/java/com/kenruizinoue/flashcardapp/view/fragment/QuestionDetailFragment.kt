package com.kenruizinoue.flashcardapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDatabase
import com.kenruizinoue.flashcardapp.viewModel.BaseViewModelFactory
import com.kenruizinoue.flashcardapp.viewModel.QuestionDetailViewModel
import kotlinx.android.synthetic.main.fragment_question_detail.*

class QuestionDetailFragment : Fragment() {

    private var questionId = -1
    private lateinit var questionCardViewModel: QuestionDetailViewModel
    private lateinit var app: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question_detail, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.question_detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_delete) {
            questionCardViewModel.startDelete(questionId)
            showToast(app, R.string.question_deleted_message)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        questionId = arguments?.let { QuestionDetailFragmentArgs.fromBundle(it).questionId }!!
        questionCardViewModel
            .getQuestionById(questionId).observe(viewLifecycleOwner, { drawUI(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // manual dependency injection
        app = requireNotNull(this.activity).application
        val questionDao = QuestionCardDatabase.getQuestionCardDB(app).questionCardDao()
        val baseViewModel = BaseViewModelFactory { QuestionDetailViewModel(questionDao) }
        questionCardViewModel = ViewModelProvider(this, baseViewModel)
            .get(QuestionDetailViewModel::class.java)

        updateButton.setOnClickListener {
            val showNormalMessage = questionCardViewModel.startUpdate(
                questionId,
                questionEditText.text.toString(),
                answerEditText.text.toString()
            )
            if (showNormalMessage) {
                showToast(app, R.string.card_updated_message)
                navigateToList()
            } else showToast(app, R.string.empty_message)
        }
    }

    private fun drawUI(card: QuestionCard?) {
        if (card != null) {
            questionEditText.setText(card.question)
            answerEditText.setText(card.answer)
        } else navigateToList()
    }

    private fun showToast(context: Context, message: Int) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToList() {
        val action =
            QuestionDetailFragmentDirections.actionQuestionDetailFragmentToQuestionListDest()
        findNavController().navigate(action)
    }

}