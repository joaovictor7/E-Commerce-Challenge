package com.e_commerce_challenge.ui.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e_commerce_challenge.R
import com.e_commerce_challenge.ui.adapter.ProductAdapter
import com.e_commerce_challenge.databinding.ActivityProductBinding
import com.e_commerce_challenge.listeners.ProductListener
import com.e_commerce_challenge.model.Product
import com.e_commerce_challenge.ui.shop.ShopActivity

class ProductActivity : AppCompatActivity() {

    private val mProductAdapter = ProductAdapter(true)
    private lateinit var mBinding: ActivityProductBinding
    private lateinit var mViewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        mBinding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        listeners()
        observers()
        adapters()
        setComponents()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shop, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_shop -> {
                startActivity(Intent(this, ShopActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun listeners() {
        mProductAdapter.setListener(object : ProductListener {
            override fun onClick(product: Product) {
                mViewModel.saveProduct(product)
                showToast("Produto adicionado ao carrinho.")
            }
        })

        mBinding.editFindProduct.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mViewModel.filterProducts(s.toString().trim())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun observers() {
        mViewModel.refreshScreen.observe(this, {
            setComponents()
        })
    }

    private fun adapters() {
        val recycler = mBinding.recyclerProducts
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = mProductAdapter
    }

    private fun setComponents() {
        mProductAdapter.setProducts(mViewModel.filteredProducts)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}