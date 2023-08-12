package com.hamscdev.roomexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hamscdev.roomexample.R
import com.hamscdev.roomexample.data.model.ModelRoom
import com.hamscdev.roomexample.view.MainActivity

class ProductAdapter(val list: List<ModelRoom>, val mainActivity: MainActivity ): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val costo = itemView.findViewById<TextView>(R.id.productCost)
        val description = itemView.findViewById<TextView>(R.id.descriptionProduct)
        val name = itemView.findViewById<TextView>(R.id.productName)
        val trash = itemView.findViewById<ImageButton>(R.id.eliminar)
        val edit = itemView.findViewById<ImageButton>(R.id.editar)

        fun render(item: ModelRoom, mainActivity: MainActivity){
            costo.setText("$" + item.costoProduct.toString())
            name.setText(item.nameProduct)
            description.setText(item.descriptionProduct)
            trash.setOnClickListener {
                mainActivity.viewModel.deleteProduct(item)
            }
            edit.setOnClickListener {
                mainActivity.createInfo(isEditar = true, item)
            }
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.product_adapter,parent,false))
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        holder.render(list[position], mainActivity)
    }

    override fun getItemCount(): Int {
        return  list.size
    }


}