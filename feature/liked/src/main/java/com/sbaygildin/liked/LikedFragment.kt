package com.sbaygildin.liked

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sbaygildin.liked.R
import com.sbaygildin.core.BaseFragment
import com.sbaygildin.core.databinding.FragmentExampleBinding
import com.sbaygildin.liked.databinding.FragmentLikedBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LikedFragment : BaseFragment(R.layout.fragment_liked) {
    private val viewModel: LikedViewModel by viewModels()
    private var _binding: FragmentLikedBinding? = null
    private val binding get() = _binding!!

    private var isSortedByDate = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLikedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LikedMediaAdapter(emptyList()) { item ->
            val id = item.imdbId
            (activity as com.sbaygildin.navigation.Navigator).navigateLikedToMediaDetailsWithId(id)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.favouriteItems.collect { items ->
                adapter.updateItems(items)
            }
        }
        binding.btnSort.setOnClickListener {
            if (isSortedByDate) {
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.toast_sorted_by_date),
                    Toast.LENGTH_SHORT
                ).show()
                binding.abcIcon.setImageResource(R.drawable.baseline_abc_24)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.favouriteItems.collect { items ->
                        adapter.updateItems(items)
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    context?.resources?.getString(R.string.toast_sorted_by_title),
                    Toast.LENGTH_SHORT
                ).show()
                binding.abcIcon.setImageResource(R.drawable.baseline_access_time_24)

                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.favouriteItemsByName.collect { items ->
                        adapter.updateItems(items)
                    }
                }
            }
            isSortedByDate = !isSortedByDate

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
