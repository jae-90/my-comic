package com.jw.marvelcomics.di

import com.jw.marvelcomics.repository.CharacterListRepository
import com.jw.marvelcomics.repository.FakeCharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [CharacterModule::class]
)
@Module
class FakeCharacterModule {

    @Provides
    fun provideFakeCharacterRepository(): CharacterListRepository = FakeCharacterRepositoryImpl()
}