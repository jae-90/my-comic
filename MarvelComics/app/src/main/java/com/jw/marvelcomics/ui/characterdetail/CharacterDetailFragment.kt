package com.jw.marvelcomics.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.jw.marvelcomics.databinding.FragmentCharacterDetailBinding
import com.jw.marvelcomics.util.setImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val args by navArgs<CharacterDetailFragmentArgs>()

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.character?.let { character ->
            character.thumbnail.getUrl().let { binding.characterDetailImage.setImage(it) }
            binding.characterDetailName.text = args.character?.name
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private val TAG: String = CharacterDetailFragment::class.java.simpleName
    }
}