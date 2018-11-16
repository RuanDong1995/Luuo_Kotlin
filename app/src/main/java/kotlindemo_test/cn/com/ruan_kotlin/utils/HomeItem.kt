package kotlindemo_test.cn.com.ruan_kotlin.utils

import cn.bmob.v3.BmobObject

/**
 * @packagename kotlindemo_test.cn.com.ruan_kotlin.utils
 * @details
 * @date 2018/11/16
 * @author ruandong
 */
class HomeItem(tableName: String?) : BmobObject(tableName) {
    var imageURL: String? = null
    var imageTitle: String? = null
    var imageContent: String? = null
    var imageName: String? = null
}