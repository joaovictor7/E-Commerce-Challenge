package com.e_commerce_challenge.ui.product

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e_commerce_challenge.data.DatabaseInMemory
import com.e_commerce_challenge.model.Product

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private val mProducts =
        listOf(
            Product(1, "Maçã", 2.54),
            Product(2, "Pêra", 5.54),
            Product(3, "Banana", 4.24),
            Product(4, "Abacaxi", 7.87),
            Product(5, "Manga", 1.99)
        )

    var filteredProducts = mProducts
        private set

    private val mRefreshScreen = MutableLiveData<Boolean>()
    val refreshScreen: LiveData<Boolean> = mRefreshScreen

    fun saveProduct(product: Product) {
        DatabaseInMemory.addProduct(product)
    }

    fun filterProducts(productName: String) {
        filteredProducts = mProducts.filter { it.name.contains(productName, true) }
        mRefreshScreen.value = true
    }
}