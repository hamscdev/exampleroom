package com.hamscdev.roomexample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.hamscdev.roomexample.R
import com.hamscdev.roomexample.adapter.ProductAdapter
import com.hamscdev.roomexample.data.model.ModelRoom
import com.hamscdev.roomexample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {



    val viewModel: MainViewModel by viewModels()
    lateinit var alertDialog: AlertDialog
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       initial()
    }

    fun initial(){
        recyclerView = findViewById(R.id.recycleLayout)
        findViewById<FloatingActionButton>(R.id.buttonAdd).setOnClickListener {
            createInfo(isEditar = false)
        }
        viewModel.apiModel.observe(this) {
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ProductAdapter(it,this)
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.createTres()
    }





    fun createInfo(isEditar: Boolean, item: ModelRoom? = null){
        alertDialog = AlertDialog.Builder(this).create()
        val view = layoutInflater.inflate(R.layout.activity_form_add, null)
        alertDialog.setCancelable(false)
        val nameProduct = view.findViewById<TextInputEditText>(R.id.nameProduct)
        val costProduct = view.findViewById<TextInputEditText>(R.id.costProduct)
        val descriptionProduct = view.findViewById<TextInputEditText>(R.id.description)
        val button = view.findViewById<AppCompatButton>(R.id.saveInformation)
        val close = view.findViewById<ImageButton>(R.id.closeModal)
        val title = view.findViewById<TextView>(R.id.titleSection)
        alertDialog.setView(view)

        title.setText(if (isEditar) "Editar Producto" else "Agregar Producto")
        close.setOnClickListener {
            alertDialog.dismiss()
        }
            if (isEditar) {
                nameProduct.setText(item!!.nameProduct)
                costProduct.setText(item!!.costoProduct.toString())
                descriptionProduct.setText(item!!.descriptionProduct)

                button.setOnClickListener {
                    if (viewModel.validationObligatorio(nameProduct, costProduct, descriptionProduct)){
                        alertDialog.dismiss()
                    viewModel.editProduct(
                        ModelRoom(
                            id = item!!.id,
                            nameProduct = nameProduct.text.toString(),
                            descriptionProduct = descriptionProduct.text.toString(),
                            costoProduct = costProduct.text.toString().toDouble()
                        )
                    )
                }

                }
            } else {

                    button.setOnClickListener {
                        if (viewModel.validationObligatorio(nameProduct, costProduct, descriptionProduct)) {
                        viewModel.onSave(
                            cost = costProduct.text.toString().toDouble(),
                            nameProduct = nameProduct.text.toString(),
                            description = descriptionProduct.text.toString()
                        )
                            alertDialog.dismiss()
                        }

                }
            }
        alertDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::alertDialog.isInitialized) {
            alertDialog.dismiss()
        }
    }





}