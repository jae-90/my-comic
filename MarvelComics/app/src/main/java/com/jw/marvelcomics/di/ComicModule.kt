package com.jw.marvelcomics.di

import com.jw.marvelcomics.repository.ComicRepository
import com.jw.marvelcomics.repository.ComicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ComicModule {

    @Binds
    abstract fun provideComicRepository(comicRepository: ComicRepositoryImpl): ComicRepository
}