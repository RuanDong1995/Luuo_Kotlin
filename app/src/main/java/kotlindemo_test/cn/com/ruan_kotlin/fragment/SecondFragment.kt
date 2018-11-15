package kotlindemo_test.cn.com.ruan_kotlin.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlindemo_test.cn.com.ruan_kotlin.R
import kotlindemo_test.cn.com.ruan_kotlin.utils.Person
import kotlindemo_test.cn.com.ruan_kotlin.utils.SecondAdapter
import kotlindemo_test.cn.com.user.ui.LoginActivity
import kotlinx.android.synthetic.main.fragment_my.*
import kotlinx.android.synthetic.main.fragment_second.*
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 *
 */
class SecondFragment : BaseFragment() {

    private var list = ArrayList<Person>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queryData()
    }


    override fun initView() {

    }


    override fun queryData() {
        val bmobQuery = BmobQuery<Person>("luuo")

        bmobQuery.findObjectsByTable(object : QueryListener<JSONArray>() {
            override fun done(jsonArray: JSONArray?, e: BmobException?) {
                if (e == null) {
                    Log.d("testJson", jsonArray.toString())
                    val gson = Gson()
                    list = gson.fromJson(jsonArray.toString(), object : TypeToken<List<Person>>() {}.type)
                    rv_second.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    rv_second.adapter = SecondAdapter(list, context)
                }
            }

        })
    }


}
