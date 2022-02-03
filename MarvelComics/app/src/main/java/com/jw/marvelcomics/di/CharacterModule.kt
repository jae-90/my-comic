package com.jw.marvelcomics.di

import com.jw.marvelcomics.repository.CharacterListRepository
import com.jw.marvelcomics.repository.CharacterListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CharacterModule {

    @Binds
    abstract fun provideCharacterListRepository(
        characterListRepository: CharacterListRepositoryImpl
    ): CharacterListRepository
}