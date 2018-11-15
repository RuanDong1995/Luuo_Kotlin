package kotlindemo_test.cn.com.ruan_kotlin.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlindemo_test.cn.com.ruan_kotlin.R

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment() {

    private var homeView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeView = inflater.inflate(R.layout.fragment_home, container, false)
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun initView() {

//        val person = Person("luuo")
//        person.name = "ruandong"
//        person.sex = "male"
//        person.age = "23"
//        person.save(object : SaveListener<String>() {
//            override fun done(objetcId: String?, e: BmobException?) {
//                if (e == null) toast("save success") else toast("save failed")
//            }
//
//        })
    }


}
