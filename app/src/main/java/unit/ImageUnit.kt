package unit

import android.net.Uri

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.image.ImageInfo
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder

/**
 * Created by anxiao on 2017/8/10.
 * fresco 操作相关
 */

object ImageUnit {


    /**
     * 根据控件大小展示图片
     *
     * @param uri
     * @param draweeView
     * @param pxWidth
     * @param pxHeight
     */
    fun showThumb(uri: Uri, draweeView: SimpleDraweeView, pxWidth: Int, pxHeight: Int) {
        val request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(ResizeOptions(pxWidth, pxHeight))
                .build()

        val controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.controller)
                .setControllerListener(BaseControllerListener())
                .build()
        draweeView.controller = controller
    }
}
