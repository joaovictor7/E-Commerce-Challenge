package com.e_commerce_challenge.ui.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e_commerce_challenge.databinding.ActivityShopBinding
import com.e_commerce_challenge.listeners.ProductListener
import com.e_commerce_challenge.model.Product
import com.e_commerce_challenge.ui.adapter.ProductAdapter

class ShopActivity : AppCompatActivity(), View.OnClickListener {

    private val mProductAdapter = ProductAdapter(false)
    private lateinit var mBinding: ActivityShopBinding
    private lateinit var mViewModel: ShopViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)
        mBinding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        actionBar()

        listeners()
        observers()
        adapters()
        setComponents()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        when (v) {
            mBinding.buttonFinalize ->  {
                mViewModel.generatePDF(mBinding.root)
                showToast("Compra finalizada e comprovante salvo.")
            }
        }
    }

    private fun listeners() {
        mProductAdapter.setListener(object : ProductListener {
            override fun onClick(product: Product) {
                mViewModel.removeProduct(product)
                showToast("Produto removido do carrinho.")
            }
        })

        mBinding.buttonFinalize.setOnClickListener(this)
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
        mProductAdapter.setProducts(mViewModel.productsShop)
    }

    private fun actionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}