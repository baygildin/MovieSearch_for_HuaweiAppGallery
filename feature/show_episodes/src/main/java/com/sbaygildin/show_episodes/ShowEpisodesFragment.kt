package com.sbaygildin.show_episodes

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sbaygildin.core.BaseFragment
import com.sbaygildin.show_episodes.databinding.FragmentShowEpisodesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ShowEpisodesFragment : BaseFragment(R.layout.fragment_show_episodes) {

    private var _binding: FragmentShowEpisodesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShowEpisodesViewModel by viewModels()
    private val args: ShowEpisodesFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShowEpisodesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodes.collect { result ->
                result?.fold(
                    onSuccess = { episodesInfo ->
                        binding.episodesContainer.removeAllViews()
                        for (episode in 1..episodesInfo.episodes.size) {
                            val button = Button(requireContext()).apply {
                                text = "${getString(R.string.txt_episode_preffix)} $episode"
                                setOnClickListener {
                                    (activity as com.sbaygildin.navigation.Navigator).navigateShowEpisodesToShowEpisode(
                                        episodesInfo.title,
                                        args.seasonNumber.toString(),
                                        episode.toString()
                                    )
                                }
                            }

                            val layoutParams = ViewGroup.MarginLayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            ).apply {
                                topMargin = 15
                            }
                            button.layoutParams = layoutParams
                            button.setTextColor(resources.getColor(R.color.main_text_color, null))
                            button.textSize = 15f
                            button.gravity = Gravity.CENTER
                            button.setBackgroundResource(R.drawable.border_background)
                            binding.episodesContainer.addView(button)
                        }
                    },
                    onFailure = { error ->
                        Log.e("ShowEpisodesFragment", "Error fetching episodes", error)
                    }
                )
            }
        }

        val seasonNumber = args.seasonNumber.toString()

        if (seasonNumber.isNotEmpty()) {
            viewModel.fetchEpisodes(args.title, seasonNumber)
        } else {
            Log.e("ShowEpisodesFragment", "Invalid season number: ${args.seasonNumber}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
