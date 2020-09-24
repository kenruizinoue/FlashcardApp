package com.kenruizinoue.flashcardapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.di.MyApplication
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDao
import com.kenruizinoue.flashcardapp.utils.hideKeyboard
import com.kenruizinoue.flashcardapp.viewModel.BaseViewModelFactory
import com.kenruizinoue.flashcardapp.viewModel.QuestionDetailViewModel
import kotlinx.android.synthetic.main.fragment_question_detail.*
import javax.inject.Inject

class QuestionDetailFragment : Fragment() {

    private var questionId = -1
    private lateinit var questionCardViewModel: QuestionDetailViewModel
    private lateinit var app: Context
    @Inject
    lateinit var questionCardDao: QuestionCardDao

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

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
            val builder = AlertDialog.Builder(activity as Context)

            builder.setTitle(getString(R.string.delete_question_card_title))
            builder.setMessage(getString(R.string.deletion_confirm_message))
            builder.setPositiveButton(getString(R.string.yes)) { _, _ ->
                questionCardViewModel.startDelete(questionId)
                showToast(app, R.string.question_deleted_message)
            }
            builder.setNegativeButton(getString(R.string.no)) { _, _ -> }

            val alertDialog = builder.create()

            alertDialog.setCancelable(false)
            alertDialog.show()
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

        app = requireNotNull(this.activity).application
        val baseViewModel = BaseViewModelFactory { QuestionDetailViewModel(questionCardDao) }
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
                hideKeyboard()
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