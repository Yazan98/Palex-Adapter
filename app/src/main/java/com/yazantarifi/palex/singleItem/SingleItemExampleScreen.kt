package com.yazantarifi.palex.singleItem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.yazantarifi.palex.R
import com.yazantarifi.palex.adapter.PalexRecyclerViewInit
import com.yazantarifi.palex.adapter.PalexSingleItemAdapter
import com.yazantarifi.palex.adapter.listeners.PalexAdapterPaginationCallback
import kotlinx.android.synthetic.main.screen_single_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleItemExampleScreen: AppCompatActivity() {

    private val adapterInstance by lazy {
        ItemExampleAdapter(getItems(), this@SingleItemExampleScreen)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_single_item)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        singleItemRecyclerView?.apply {
            PalexRecyclerViewInit.initVerticalView(this@SingleItemExampleScreen, this, adapterInstance)
            adapterInstance.addRecyclerViewInstance(this@apply)
            adapterInstance.addPaginationListener(object: PalexAdapterPaginationCallback {
                override fun onNextPageRequest() {
                    println("III on New Page Request")
                    adapterInstance.addItems(getItems())
                }
            })
        }
    }

    private fun getItems(): ArrayList<ItemExample> {
        return ArrayList<ItemExample>().apply {
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Lorem Ipsum is simply dummy", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "g Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and "))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "y text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Where can I get some?", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
            add(ItemExample("Title Here", "Description Here"))
        }
    }

}