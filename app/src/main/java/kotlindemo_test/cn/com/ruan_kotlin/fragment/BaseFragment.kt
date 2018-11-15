package kotlindemo_test.cn.com.ruan_kotlin.fragment

import android.support.v4.app.Fragment
import android.widget.Toast

open class BaseFragment : Fragment() {

    open fun initView() {

    }


    fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    open fun queryData(){}

}
