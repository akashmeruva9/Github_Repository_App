package com.akash.meruva.github

import androidx.lifecycle.LiveData

class repoRepository(private val repoDAO: repoDAO){

    val allrepos : LiveData<List<repo>> = repoDAO.getAllrepos()

    suspend fun insert(repo : repo)
    {
        repoDAO.insert(repo)
    }

    suspend fun delete(repo : repo)
    {
        repoDAO.delete(repo)
    }

    suspend fun update(repo : repo)
    {
        repoDAO.updaterepo(repo)
    }

}