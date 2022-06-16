package com.akash.meruva.github

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface repoDAO
{
    @Insert
    fun insert(repo : repo)

    @Delete
    fun delete(repo : repo)

    @Query("Select * from repo_table order by id ASC")
    fun getAllrepos() : LiveData<List<repo>>

    @Update
    fun updaterepo(repo : repo)

}