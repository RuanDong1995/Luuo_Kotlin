package kotlindemo_test.cn.com.ruan_kotlin

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @packagename kotlindemo_test.cn.com.ruan_kotlin
 * @details 单例模式，对应于Java的双重锁校验
 * @date 2018/11/15
 * @author ruandong
 */
open class ImageLoader private constructor() {

    companion object {
        val instance: ImageLoader by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageLoader()
        }
    }

    fun loadImage(context: Context, url: String? = null, view: ImageView) {
        Glide.with(context).load(url).into(view)
    }

}