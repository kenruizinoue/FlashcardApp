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
import com.kenruizinoue.flashcardapp.utils.hideKeyboard
import com.kenruizinoue.flashcardapp.viewModel.QuestionDetailViewModel
import kotlinx.android.synthetic.main.fragment_question_detail.*
import javax.inject.Inject

class QuestionDetailFragment : Fragment() {

    private var questionId = -1
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var questionDetailViewModel: QuestionDetailViewModel

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
                questionDetailViewModel.startDelete(questionId)
                showToast(requireNotNull(this.activity).application, R.string.question_deleted_message)
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
        questionDetailViewModel
            .getQuestionById(questionId).observe(viewLifecycleOwner, { drawUI(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionDetailViewModel = ViewModelProvider(this, viewModelFactory)
            .get(QuestionDetailViewModel::class.java)

        updateButton.setOnClickListener {
            val showNormalMessage = questionDetailViewModel.startUpdate(
                questionId,
                questionEditText.text.toString(),
                answerEditText.text.toString()
            )
            if (showNormalMessage) {
                showToast(requireNotNull(this.activity).application, R.string.card_updated_message)
                hideKeyboard()
                navigateToList()
            } else showToast(requireNotNull(this.activity).application, R.string.empty_message)
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