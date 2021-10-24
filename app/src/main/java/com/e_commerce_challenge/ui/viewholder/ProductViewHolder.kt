package com.e_commerce_challenge.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.e_commerce_challenge.databinding.RowProductBinding
import com.e_commerce_challenge.listeners.ProductListener
import com.e_commerce_challenge.model.Product

class ProductViewHolder(
    binding: RowProductBinding,
    listener: ProductListener,
    private val mAdd: Boolean
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private val mBinding = binding
    private val mListener = listener
    private lateinit var mProduct: Product

    override fun onClick(v: View?) {
        mListener.onClick(mProduct)
    }

    fun bind(product: Product) {
        mProduct = product
        listeners()
        setComponents()
    }

    private fun listeners() {
        mBinding.buttonAdd.setOnClickListener(this)
        mBinding.buttonDelete.setOnClickListener(this)
    }

    private fun setComponents() {
        val price = "R$${mProduct.price}"
        mBinding.textProductName.text = mProduct.name
        mBinding.textPrice.text = price

        if (mAdd) {
            mBinding.buttonDelete.visibility = View.GONE
            mBinding.buttonAdd.visibility = View.VISIBLE
        } else {
            mBinding.buttonDelete.visibility = View.VISIBLE
            mBinding.buttonAdd.visibility = View.GONE
        }
    }
}