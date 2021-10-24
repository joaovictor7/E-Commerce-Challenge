package com.e_commerce_challenge.ui.shop

import android.app.Application
import android.graphics.pdf.PdfDocument
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.e_commerce_challenge.data.DatabaseInMemory
import com.e_commerce_challenge.model.Product
import java.io.File
import java.io.FileOutputStream

class ShopViewModel(application: Application) : AndroidViewModel(application) {

    private val mApplication = application

    var productsShop = DatabaseInMemory.getAll()
        private set

    private val mRefreshScreen = MutableLiveData<Boolean>()
    val refreshScreen: LiveData<Boolean> = mRefreshScreen

    fun removeProduct(product: Product) {
        DatabaseInMemory.removeProduct(product)
        mRefreshScreen.value = true
    }

    fun generatePDF(view: View) {
        val document = PdfDocument();

        val pageInfo = PdfDocument.PageInfo.Builder(100, 100, 1).create();

        // start a page
        val page = document.startPage(pageInfo);

        // draw something on the page
        view.draw(page.canvas)

        // finish the page
        document.finishPage(page);
        // add more pages
        // write the document content
        val file = File(mApplication.filesDir.absolutePath, "shop.pdf")
        document.writeTo(FileOutputStream(file))

        // close the document
        document.close()
    }
}