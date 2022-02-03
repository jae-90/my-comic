package com.jw.marvelcomics.di

import com.jw.marvelcomics.repository.ComicRepository
import com.jw.marvelcomics.repository.FakeComicRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [ComicModule::class]
)
@Module
class FakeComicModule {

    @Provides
    fun provideFakeComicRepository(): ComicRepository = FakeComicRepositoryImpl()
}