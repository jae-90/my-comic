package com.jw.marvelcomics.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.jw.marvelcomics.R
import com.jw.marvelcomics.databinding.FragmentCharacterDetailBinding
import com.jw.marvelcomics.util.setImage
import com.jw.marvelcomics.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentCharacterDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_character_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.character?.thumbnail?.getUrl()
            ?.let { binding.characterDetailImage.setImage(it) }

        binding.characterDetailName.text = mainViewModel.character?.name
    }

    companion object {
        private val TAG: String = CharacterDetailFragment::class.java.simpleName
    }
}