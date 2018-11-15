package kotlindemo_test.cn.com.user

import cn.bmob.v3.BmobUser

/**
 * @packagename kotlindemo_test.cn.com.user
 * @details
 * @date 2018/11/15
 * @author ruandong
 */
class User : BmobUser() {

    private var sex: Boolean? = null
    private var nick: String? = null
    private var age: Int? = null
}