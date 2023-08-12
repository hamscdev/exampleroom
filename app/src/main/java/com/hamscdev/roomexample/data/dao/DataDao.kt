package com.hamscdev.roomexample.data.model.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hamscdev.roomexample.data.model.ModelRoom


@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addModel(model: ModelRoom)

    @Query("SELECT * FROM model_table ORDER BY id")
     fun getListModel(): List<ModelRoom>

    @Delete
    suspend fun deleteModel(model: ModelRoom)

    @Update
    suspend fun putModel(model: ModelRoom)


}