package kotlindemo_test.cn.com.user.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import kotlindemo_test.cn.com.user.R
import kotlindemo_test.cn.com.user.User
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mBtnLogin.setOnClickListener {
            login()
        }
        mBtnSignUp.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }

    }

    private fun login() {
        val user = User()
        user.username = mLoginUserName.text.toString()
        user.setPassword(mLoginPsw.text.toString())
        user.login(object : SaveListener<User>() {
            override fun done(user: User?, e: BmobException?) {
                if (user != null) {
                    Toast.makeText(this@LoginActivity, "登录成功", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

        })
    }
}
