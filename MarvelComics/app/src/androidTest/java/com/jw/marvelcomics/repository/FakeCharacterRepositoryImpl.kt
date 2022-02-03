package com.jw.marvelcomics.repository

import com.jw.marvelcomics.repository.api.model.Character
import com.jw.marvelcomics.repository.api.model.Characters
import com.jw.marvelcomics.repository.api.model.CharactersCollectionData
import com.jw.marvelcomics.repository.api.model.Thumbnail
import com.jw.marvelcomics.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeCharacterRepositoryImpl @Inject constructor(): CharacterListRepository {

    override fun getCharacters(url: String): Flow<CharactersDataState> {
        return flow {
            emit(
                DataState.Success(
                    CharactersCollectionData(
                        Characters(listOf(
                            Character("name1", Thumbnail("path1", "extension1")),
                            Character("name2", Thumbnail("path2", "extension2")),
                            Character("name3", Thumbnail("path3", "extension3"))
                        ))
                    )
                )
            )
        }
    }
}