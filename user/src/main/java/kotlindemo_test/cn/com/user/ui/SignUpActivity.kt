package kotlindemo_test.cn.com.user.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import kotlindemo_test.cn.com.user.R
import kotlindemo_test.cn.com.user.User
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



        mSignUp.setOnClickListener {
            signUp()
        }


    }

    private fun signUp() {
        val user = User()
        user.username = mEditAccount.text.toString()
        user.setPassword(mEditPsw.text.toString())
        user.email = mEditMail.text.toString()
        user.signUp(object : SaveListener<User>() {
            override fun done(user: User?, e: BmobException?) {
                if (e == null) {
                    Toast.makeText(this@SignUpActivity, "success", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}
