package android.sportsnews.ui.fragments

import android.sportsnews.databinding.FragmentTopHeadlinesBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHeadlinesFragment : Fragment() {

    private val viewModel by viewModels<TopHeadlinesViewModel>()
    lateinit var topHeadlinesAdapter: TopHeadlinesAdapter
    private var _binding: FragmentTopHeadlinesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopHeadlinesBinding.inflate(inflater, container, false)
        viewModel.getLog()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.topHeadlines.observe(viewLifecycleOwner){
            topHeadlinesAdapter.differ.submitList(it.articles.toList())
        }
    }

    private fun setupRecyclerView() {
        topHeadlinesAdapter = TopHeadlinesAdapter()
        binding.rvAllNewsFragment.apply {
            adapter = topHeadlinesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }
}