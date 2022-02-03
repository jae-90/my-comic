package com.jw.marvelcomics.ui.comic

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.jw.marvelcomics.R
import com.jw.marvelcomics.databinding.FragmentComicBinding
import com.jw.marvelcomics.util.Constants.SPIDER_MAN_COMIC_BOOK_ID
import com.jw.marvelcomics.util.DataState
import com.jw.marvelcomics.util.convertToHttpsUriString
import com.jw.marvelcomics.util.setImage
import com.jw.marvelcomics.viewmodel.ComicViewModel
import com.jw.marvelcomics.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ComicFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: ComicViewModel by viewModels()

    private lateinit var binding: FragmentComicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getComicResponse(SPIDER_MAN_COMIC_BOOK_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_comic, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.comicResponse.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    // loading..
                    // add a spinner or progressbar
                }
                is DataState.Success -> {
                    val response = dataState.data
                    binding.comicImage.setImage(response.data.results[0].thumbnail.getUrl())

                    binding.comicContent.comicTitle.text = response.data.results[0].title
                    binding.comicContent.comicDescription.text =
                        Html.fromHtml(
                            response.data.results[0].textObjects[0].text, Html.FROM_HTML_MODE_COMPACT
                        )

                    mainViewModel.charactersUrl =
                        response.data.results[0].characters.collectionURI.convertToHttpsUriString()
                }
                is DataState.Error -> {
                    // handle errors
                    // network availability, exceptions, etc
                    Log.e(TAG, "----> Error: ${dataState.exception.message}")
                }
            }
        }

        binding.fab.setOnClickListener {
            if (mainViewModel.charactersUrl.isEmpty()) {
                Log.d(TAG, "----> Character fetch url is empty")
            } else {
                val action = ComicFragmentDirections
                    .actionComicFragmentToCharacterListFragment()
                view.findNavController().navigate(action)
            }
        }
    }

    companion object {
        private val TAG: String = ComicFragment::class.java.simpleName
    }
}