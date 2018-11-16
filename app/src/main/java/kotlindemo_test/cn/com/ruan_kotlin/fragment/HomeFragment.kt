package kotlindemo_test.cn.com.ruan_kotlin.fragment


import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlindemo_test.cn.com.ruan_kotlin.R
import kotlindemo_test.cn.com.ruan_kotlin.utils.HomeItem
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_list.view.*
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : BaseFragment() {

    private var homeView: View? = null
    private var list = ArrayList<HomeItem>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queryImage()
    }

    override fun initView() {
    }

    fun queryImage() {
        val bmobQuery = BmobQuery<HomeItem>("home")
        bmobQuery.findObjectsByTable(object : QueryListener<JSONArray>() {
            override fun done(jsonArray: JSONArray?, e: BmobException?) {
                if (e == null) {
                    toast("success")
                    val gson = Gson()
                    list = gson.fromJson(jsonArray.toString(), object : TypeToken<List<HomeItem>>() {}.type)
                    rv_home.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    rv_home.adapter = HomeAdapter(list, context!!)
                }
            }
        })
    }

    /**
     *
     */
    inner class HomeAdapter(var list: List<HomeItem>?, var context: Context?) :
        RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
        override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): HomeHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_home_list, viewGroup, false)
            return HomeHolder(view)
        }

        override fun getItemCount(): Int {
            return if (list == null) 0 else list!!.size
        }

        override fun onBindViewHolder(myHolder: HomeHolder, position: Int) {
            val item = list!![position]
            myHolder.bind(item)

        }

        inner class HomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(item: HomeItem) {
                Glide.with(context!!).load(item.imageURL).apply(RequestOptions().centerInside()).into(itemView.mItemImage)
                itemView.mItemName.text = item.imageName
            }

        }
    }


}
