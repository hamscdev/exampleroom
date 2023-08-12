package com.hamscdev.roomexample.data.repository


import android.util.Log
import com.hamscdev.roomexample.data.model.ModelRoom
import com.hamscdev.roomexample.data.model.dao.DataDao

class CommerceRepository(private val dD: DataDao) {
    fun getProductList(): List<ModelRoom> = dD.getListModel()

    suspend fun createProduct(model: ModelRoom){
        dD.addModel(model)
    }

    suspend fun deleteProduct(model: ModelRoom){
        dD.deleteModel(model = model)
    }

    suspend fun updateProduct(model: ModelRoom){
        dD.putModel(model = model)
    }


}