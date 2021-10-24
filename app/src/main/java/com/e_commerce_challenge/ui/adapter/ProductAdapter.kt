package com.e_commerce_challenge.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e_commerce_challenge.databinding.RowProductBinding
import com.e_commerce_challenge.listeners.ProductListener
import com.e_commerce_challenge.model.Product
import com.e_commerce_challenge.ui.viewholder.ProductViewHolder

class ProductAdapter(private val mAdd: Boolean) : RecyclerView.Adapter<ProductViewHolder>() {

    private lateinit var mListener: ProductListener
    private var mProducts = listOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            RowProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProductViewHolder(binding, mListener, mAdd)
    }

    override fun onBindViewHolder(holderMenu: ProductViewHolder, position: Int) {
        holderMenu.bind(mProducts[position])
    }

    override fun getItemCount(): Int {
        return mProducts.count()
    }

    fun setListener(listener: ProductListener) {
        mListener = listener
    }

    fun setProducts(products: List<Product>) {
        mProducts = products.sortedBy { it.name }
        notifyDataSetChanged()
    }
}