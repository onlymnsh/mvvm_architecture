package com.example.architecture.domain.database

import com.example.architecture.domain.database.datastore.DataStoreManager
import com.example.architecture.domain.database.db.dao.RoomDao
import com.example.architecture.domain.model.User
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dm: DataStoreManager,
    private val roomDao: RoomDao
) : ILocalDataSource {
    override suspend fun saveUserByDataStore(user: User) {
        dm.set("first_name", user.firstName)
        dm.set("last_name", user.lastName)
    }

    override suspend fun getUserByDataStore(): User {

        val firstName: String? = dm.get("first_name", "None").first()
        val lastName: String? = dm.get("last_name", "None").first()

        return User(firstName = "$firstName", lastName = "$lastName")
    }

    override suspend fun saveUserByRoom(user: User) {
        roomDao.saveUser(user)
    }

    override suspend fun getUserByRoom(): User {
        return roomDao.getUser()
    }


}

