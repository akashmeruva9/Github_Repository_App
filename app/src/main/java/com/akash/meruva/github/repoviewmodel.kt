package com.akash.meruva.github

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.akash.meruva.github.repo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class repoViewmodel(applcation : Application): AndroidViewModel(applcation)
{
    private val repository: repoRepository
   val allrepos: LiveData<List<repo>>

    init {
        val dao = repoDatabase.getDatabase(applcation).getrepoDao()
         repository = repoRepository(dao)
        allrepos = repository.allrepos
    }

    fun dleterepo(repo : repo) = viewModelScope.launch(Dispatchers.IO)
    {
        repository.delete(repo)
    }

    fun Insertrepo( repo : repo) = viewModelScope.launch (Dispatchers.IO )
    {
        repository.insert(repo)
    }

    fun Updaterepo(repo : repo) = viewModelScope.launch ( Dispatchers.IO )
    {
        repository.update(repo)
    }
}