package com.ayush.dicontainer.data

interface UserRepository {
    suspend fun fetchData()
}