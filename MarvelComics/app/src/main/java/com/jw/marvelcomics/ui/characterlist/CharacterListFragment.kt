package com.jw.marvelcomics.ui.characterlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jw.marvelcomics.databinding.FragmentCharacterListBinding
import com.jw.marvelcomics.repository.api.model.Character
import com.jw.marvelcomics.util.DataState
import com.jw.marvelcomics.viewmodel.CharacterListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val viewModel: CharacterListViewModel by viewModels()
    private val args by navArgs<CharacterListFragmentArgs>()

    private lateinit var characterListAdapter: CharacterListAdapter
    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        observeCharacterCollectionDataResponse()
        requestCharacterCollectionDataResponse()
    }

    private fun setupRecyclerView() {
        characterListAdapter = CharacterListAdapter(
            mutableListOf(),
            object : CharacterListAdapter.OnItemClickedListener {
                override fun onItemSelected(position: Int, item: Character) {
                    val action =
                        CharacterListFragmentDirections
                            .actionCharacterListFragmentToCharacterDetailFragment(item)
                    view?.findNavController()?.navigate(action)
                }
            }
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterListAdapter
        }
    }

    private fun observeCharacterCollectionDataResponse() {
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
                    // network availability, custom exceptions, etc
                    Log.e(TAG, "----> Error: ${dataState.exception.message}")
                }
            }
        }
    }

    private fun requestCharacterCollectionDataResponse() {
        args.characterUrl?.let {
            viewModel.getCharactersCollectionResponse(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG: String = CharacterListFragment::class.java.simpleName
    }
}