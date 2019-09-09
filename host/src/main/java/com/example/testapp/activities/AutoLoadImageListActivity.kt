package com.example.testapp.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anmnight.imageloader.AutoLoadImage
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_list.*

class AutoLoadImageListActivity : Activity() {

    private val tag = "AutoLoadImage"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val adapter = ImageListAdapter()
        val layoutManager = LinearLayoutManager(this)

        list.layoutManager = layoutManager
        list.adapter = adapter
        val path = "https://up.enterdesk.com/edpic_source/53/a6/78/53a678b383be6eb9607e875339b1d052.jpg"
        val array = arrayListOf(path, path, path, path)
        adapter.addImages(array)


    }

    private inner class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

        val images = arrayListOf<String>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val itemView = layoutInflater.inflate(R.layout.list_item_autodownloadimage, null, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return images.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val url = images[position]
            holder.imageView.displayImage(url)
        }


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: AutoLoadImage = itemView.findViewById(R.id.item_image)
        }

        fun addImages(imgs: List<String>) {
            images.addAll(imgs)
        }
    }


}
