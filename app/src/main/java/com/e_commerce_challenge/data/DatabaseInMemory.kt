package com.e_commerce_challenge.data

import com.e_commerce_challenge.model.Product

class DatabaseInMemory {

    companion object {
        private val mProducts = mutableListOf<Product>()

        fun getAll(): List<Product> {
            return mProducts
        }

        fun addProduct(product: Product) {
            mProducts.add(product)
        }

        fun removeProduct(product: Product) {
            mProducts.remove(product)
        }
    }
}