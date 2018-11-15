package kotlindemo_test.cn.com.ruan_kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import cn.bmob.v3.Bmob
import kotlindemo_test.cn.com.ruan_kotlin.fragment.HomeFragment
import kotlindemo_test.cn.com.ruan_kotlin.fragment.MyFragment
import kotlindemo_test.cn.com.ruan_kotlin.fragment.SecondFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG_HOME = "home"
    private val TAG_SECOND = "second"
    private val TAG_MY = "my"
    private var a = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Bmob.initialize(this, "f88ad68eb47c3d9040adebd581aa82c7")
        radio_group_tap.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_tab_home -> showFragment(
                    TAG_HOME,
                    HomeFragment()
                )
                R.id.rb_tab_second -> showFragment(
                    TAG_SECOND,
                    SecondFragment()
                )
                R.id.rb_tab_my -> showFragment(
                    TAG_MY,
                    MyFragment()
                )
            }
        }
        rb_tab_home.isChecked = true
    }

    private fun showFragment(tag: String, fragment: Fragment) {
        val fragmentsList = supportFragmentManager.fragments
        for (f in fragmentsList) {
            supportFragmentManager.beginTransaction().hide(f).commit()
        }
        if (fragmentsList.contains(fragment))
            supportFragmentManager.beginTransaction().show(fragment).commit()
        else
            supportFragmentManager.beginTransaction().add(R.id.fl_fragment_container, fragment, tag).commit()
    }

}