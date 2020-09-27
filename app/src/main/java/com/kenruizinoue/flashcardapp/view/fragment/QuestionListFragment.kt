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
import com.kenruizinoue.flashcardapp.view.recyclerView.CardAdapter
import com.kenruizinoue.flashcardapp.viewModel.QuestionListViewModel
import kotlinx.android.synthetic.main.fragment_question_list.*
import javax.inject.Inject

class QuestionListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var questionListViewModel: QuestionListViewModel
    private lateinit var cardAdapter: CardAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionListViewModel = ViewModelProvider(this, viewModelFactory)
            .get(QuestionListViewModel::class.java)

        cardAdapter = CardAdapter(arrayListOf(), object : ItemClickListener {
            override fun onItemClicked(questionId: Int) {
                navigateToDetail(questionId)
            }
        })

        questionRecyclerView.apply {
            adapter = cardAdapter

        }

        addFab.setOnClickListener {
            findNavController().navigate(R.id.addQuestionDest, null)
        }

        questionListViewModel.getUsers().observe(viewLifecycleOwner, { cards ->
            cardAdapter.updateCards(cards)
        })

    }

    private fun navigateToDetail(questionId: Int) {
        val action = QuestionListFragmentDirections.actionQuestionListDestToQuestionDetailFragment(questionId)
        findNavController().navigate(action)
    }
}

interface ItemClickListener {
    fun onItemClicked(questionId: Int)
}