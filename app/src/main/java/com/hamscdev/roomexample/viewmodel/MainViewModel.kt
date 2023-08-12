package com.hamscdev.roomexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.TextInputEditText
import com.hamscdev.roomexample.Aplication
import com.hamscdev.roomexample.data.model.ModelRoom
import com.hamscdev.roomexample.data.repository.CommerceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val repository: CommerceRepository = CommerceRepository(Aplication.dao.dataDao())
    val apiModel = MutableLiveData<List<ModelRoom>>()

    fun getList() {
             apiModel.postValue(repository.getProductList())
    }

    fun onSave(cost: Double, nameProduct: String, description: String) {
        viewModelScope.launch(Dispatchers.IO){
            repository.createProduct(ModelRoom(id= 0,nameProduct = nameProduct, costoProduct = cost, descriptionProduct = description))
            getList()
        }
    }

    fun deleteProduct(model: ModelRoom){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteProduct(model = model)
            getList()
        }
    }

    fun editProduct(model: ModelRoom){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateProduct(model)
            getList()
        }
    }


    fun createTres(){
        viewModelScope.launch(Dispatchers.IO) {

            var list: List<ModelRoom> = repository.getProductList()
            if (list.size == 0){ repository.createProduct(
                ModelRoom(
                    id = 0,
                    nameProduct = "PRODUCTO NUMERO 1",
                    costoProduct = 34.50,
                    descriptionProduct = "PRUEBAS DE PRODUCTO"
                )
            )
                repository.createProduct(
                    ModelRoom(
                        id = 0,
                        nameProduct = "PRODUCTO NUMERO 2",
                        costoProduct = 97.50,
                        descriptionProduct = "PRUEBAS DE PRODUCTO"
                    )
                )
                repository.createProduct(
                    ModelRoom(
                        id = 0,
                        nameProduct = "PRODUCTO NUMERO 3",
                        costoProduct = 1500.999,
                        descriptionProduct = "PRUEBAS DE PRODUCTO"
                    )
                )
                getList() } else {

                getList()
            }
        }

    }

    fun validationObligatorio(
        nameProduct: TextInputEditText,
        costProduct: TextInputEditText,
        descriptionProduct: TextInputEditText
    ): Boolean{

        if (nameProduct.text.toString().isNullOrEmpty() || nameProduct.text.toString().isNullOrBlank()){
            nameProduct.setError("Campo Obligatorio")
            return false
        }
        if (costProduct.text.toString().isNullOrEmpty() || costProduct.text.toString().isNullOrBlank()){
            costProduct.setError("Campo Obligatorio")
            return false
        }
        if (descriptionProduct.text.toString().isNullOrEmpty() || descriptionProduct.text.toString().isNullOrBlank()){
            descriptionProduct.setError("Campo Obligatorio")
            return false
        }
        return true
    }
}