package kotlindemo_test.cn.com.ruan_kotlin.utils

import cn.bmob.v3.BmobObject

/**
 * @packagename kotlindemo_test.cn.com.luuo_kotlin.utils
 * @details
 * @date 2018/11/13
 * @author ruandong
 */
open class Person(
    tableName: String?
) : BmobObject(tableName) {
    var url : String? = null
    var name: String? = null
    var sex: String? = null
    var age: String? = null
}