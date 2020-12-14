package ekel.app.videoscartoon.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

import com.squareup.picasso.Picasso
import ekel.app.videoscartoon.ContainChanel
import ekel.app.videoscartoon.NetworkUtils
import ekel.app.videoscartoon.R
import ekel.app.videoscartoon.model.AllChanel
import ekel.app.videoscartoon.model.DetailChanel

import java.util.ArrayList

class DetailChanelAdapter(var c: Context, var listData :List<DetailChanel>) : RecyclerView.Adapter<DetailChanelAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var gambar: ImageView
        var contain_view: LinearLayout

        init {
            title= view.findViewById<View>(R.id.titleChanel) as TextView
            gambar = view.findViewById<View>(R.id.imageChanel) as ImageView
            contain_view = view.findViewById<View>(R.id.container_Chanel) as LinearLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_chanel, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val chanel = listData[position]

        Picasso.with(c).load(chanel.image).fit().centerCrop().into(holder.gambar)
        holder.title.text = chanel.title
        holder.contain_view.setOnClickListener {
            if (!NetworkUtils.isInternetAvailable(c)) return@setOnClickListener NetworkUtils.showNetworkAlertDialog(c)

            val intent = Intent(c, ContainChanel::class.java)
            intent.putExtra("url", listData[position].url)
            intent.putExtra("title", listData[position].title)
            startActivity(c, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}