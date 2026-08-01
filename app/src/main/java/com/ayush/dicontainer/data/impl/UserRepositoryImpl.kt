package com.ayush.dicontainer.data.impl

import com.ayush.dicontainer.data.ApiClient
import com.ayush.dicontainer.data.UserRepository

class UserRepositoryImpl(
    private val apiClient: ApiClient
): UserRepository {
    override suspend fun fetchData() {
        apiClient.get()
    }
}