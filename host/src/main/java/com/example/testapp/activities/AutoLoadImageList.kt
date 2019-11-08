package com.example.testapp.activities

import android.graphics.Canvas
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.*
import kotlinx.android.synthetic.main.activity_auto_load_image_list.*

class AutoLoadImageList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_load_image_list)

        val manager = LinearLayoutManager(this)
        image_list.layoutManager = manager
        image_list.adapter = ImageAdapter()
        image_list.addItemDecoration(ImageDecoration())


    }


    inner class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

        override fun onViewRecycled(holder: ViewHolder) {
            super.onViewRecycled(holder)
//            holder.imageView.setImageRecycled()
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(this@AutoLoadImageList).inflate(R.layout.list_image_simple_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return images.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            holder.imageView.displayImage(images[position])
        }


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//            val imageView: AutoLoadImage = itemView.findViewById(R.id.image_item)
        }
    }


    inner class ImageDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = dp2px(2)
            outRect.right = dp2px(2)
            outRect.left = dp2px(2)
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
//            val childView = parent.getChildAt(2).findViewById<AutoLoadImage>(R.id.image_item)
//            parent.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//                childView.scrollBy(0, 5)
//            }
        }
    }
}
