package com.jw.marvelcomics.ui.characterlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jw.marvelcomics.R
import com.jw.marvelcomics.databinding.FragmentCharacterListBinding
import com.jw.marvelcomics.repository.api.model.Character
import com.jw.marvelcomics.util.DataState
import com.jw.marvelcomics.viewmodel.CharacterListViewModel
import com.jw.marvelcomics.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val viewModel: CharacterListViewModel by viewModels()

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var characterListAdapter: CharacterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCharactersCollectionResponse(mainViewModel.charactersUrl)
        characterListAdapter = CharacterListAdapter(
            mutableListOf(),
            object : CharacterListAdapter.OnItemClickedListener {
                override fun onItemSelected(position: Int, item: Character) {
                    mainViewModel.character = item
                    val action =
                        CharacterListFragmentDirections
                            .actionCharacterListFragmentToCharacterDetailFragment()
                    view?.findNavController()?.navigate(action)
                }
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_character_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterListAdapter
        }

        viewModel.charactersCollectionData.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    // loading..
                    // add a spinner or progressbar
                }
                is DataState.Success -> {
                    val response = dataState.data
                    characterListAdapter.submitList(response.data.results)
                }
                is DataState.Error -> {
                    // handle errors
                    // network availability, exceptions, etc
                    Log.e(TAG, "----> Error: ${dataState.exception.message}")
                }
            }
        }
    }

    companion object {
        private val TAG: String = CharacterListFragment::class.java.simpleName
    }
}