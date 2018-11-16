package kotlindemo_test.cn.com.ruan_kotlin.utils

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlindemo_test.cn.com.ruan_kotlin.R
import kotlinx.android.synthetic.main.item_second_layout.view.*

/**
 * @packagename kotlindemo_test.cn.com.luuo_kotlin.utils
 * @details
 * @date 2018/11/13
 * @author ruandong
 */
class SecondAdapter(var list: List<Person>?, var context: Context?) :
    RecyclerView.Adapter<SecondAdapter.MyHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_second_layout, viewGroup, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }

    override fun onBindViewHolder(myHolder: MyHolder, position: Int) {
        val person = list!![position]
        Glide.with(context!!).load(person.url).into(myHolder.ivItem)
        myHolder.itemView.tv_name.text = person.name
        myHolder.itemView.tv_sex.text = person.sex
        myHolder.itemView.tv_age.text = person.age
        myHolder.itemView.tv_id.text = person.objectId
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivItem = itemView.findViewById<ImageView>(R.id.mIvItem)
    }

}