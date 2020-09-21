package com.gmail.part1.satrio.ini_tugas_kotlin.Adapter

import android.content.Context
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import com.gmail.part1.satrio.ini_tugas_kotlin.Pojo.Berita_Pojo
import com.gmail.part1.satrio.ini_tugas_kotlin.R
import com.squareup.picasso.Picasso
import android.support.customtabs.CustomTabsIntent

class Berita_Adapter : RecyclerView.Adapter<Berita_Adapter.ViewHolder> {

    var mNewsData: List<Berita_Pojo.Article>? = null
    var mContext: Context? = null

    constructor(context: Context, data: List<Berita_Pojo.Article>?) {
        this.mContext = context
        this.mNewsData = data
    }


    override fun getItemCount(): Int {
        return mNewsData!!.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        setScaleAnimation(holder.itemView)

        val pojo: Berita_Pojo.Article = mNewsData!!.get(position)

        Picasso.with(mContext)
                .load(pojo.urlToImage)
                .into(holder.ivPreview)

        holder.tvTitle.text = pojo.title
        holder.tvDesc.text = pojo.description as CharSequence?
        holder.tvDate.text = pojo.publishedAt
        holder.tvSrc.text = pojo.source.name
        holder.cvLayout.setOnClickListener {


            val url = pojo.url
            val builder = CustomTabsIntent.Builder()
            builder.setStartAnimations(mContext!!, R.anim.slide_right,R.anim.slide_right)
            builder.setExitAnimations(mContext!!, R.anim.slide_left, R.anim.slide_left)
            val customTabsIntent = builder.build()
            builder.setToolbarColor(ContextCompat.getColor(mContext!!, R.color.colorPrimary))
            customTabsIntent.launchUrl(mContext!!, Uri.parse(url))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext).inflate(R.layout.list_berita, parent, false)

        return ViewHolder(inflater)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        var ivPreview = itemView?.findViewById<View>(R.id.ivPreview) as ImageView
        var cvLayout = itemView?.findViewById<View>(R.id.cvLayout) as CardView
        var tvTitle = itemView?.findViewById<View>(R.id.tvTitle) as TextView
        var tvDesc = itemView?.findViewById<View>(R.id.tvDesc) as TextView
        var tvDate = itemView?.findViewById<View>(R.id.tvDate) as TextView
        var tvSrc = itemView?.findViewById<View>(R.id.tvSrc) as TextView
    }

    private fun setScaleAnimation(view: View) {
        val anim = ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        anim.duration = 500
        view.startAnimation(anim)
    }
}