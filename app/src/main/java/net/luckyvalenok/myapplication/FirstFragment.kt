package net.luckyvalenok.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import net.luckyvalenok.myapplication.databinding.FirstFragmentBinding
import javax.inject.Inject

class FirstFragment : Fragment() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    private val cardAdapter = CardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity?.application as MainApplication).component.injectFirstFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FirstFragmentBinding = FirstFragmentBinding.inflate(inflater, container, false)

        binding.items.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                CardAdapter.SpacesItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL,
                    resources.getDimensionPixelSize(R.dimen.default_padding)
                )
            )
            adapter = cardAdapter
        }

        mainViewModel.cards.observe(viewLifecycleOwner) {
            cardAdapter.submitList(CardType.getFromListRequest(it))
        }
        mainViewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }
}