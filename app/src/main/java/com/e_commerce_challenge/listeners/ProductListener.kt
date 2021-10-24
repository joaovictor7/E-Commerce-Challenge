package com.e_commerce_challenge.listeners

import com.e_commerce_challenge.model.Product

interface ProductListener {

    fun onClick(product: Product)
}