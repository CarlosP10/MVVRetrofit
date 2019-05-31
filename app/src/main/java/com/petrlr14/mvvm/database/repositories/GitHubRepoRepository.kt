package com.petrlr14.mvvm.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.petrlr14.mvvm.database.daos.GitHubDAO
import com.petrlr14.mvvm.database.entities.GitHubRepo
import com.petrlr14.mvvm.service.GitHubService
import kotlinx.coroutines.Deferred
import retrofit2.Response

class GitHubRepoRepository (private val repoDao:GitHubDAO, private val gitHubService:GitHubService){

    @WorkerThread
    suspend fun insert(repo:GitHubRepo){
        repoDao.insert(repo)
    }

    fun getAll():LiveData<List<GitHubRepo>>{
        return repoDao.getAllRepos()
    }

    @WorkerThread
    suspend fun nuke(){
        return repoDao.nukeTable()
    }

    fun retrieveReposAsync(user:String): Deferred<Response<List<GitHubRepo>>> {
        return gitHubService.getRepos(user)
    }

}