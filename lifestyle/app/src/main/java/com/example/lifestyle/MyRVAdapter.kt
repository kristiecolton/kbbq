package com.example.lifestyle


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyle.R
import java.lang.ClassCastException

class MyRVAdapter : RecyclerView.Adapter<MyRVAdapter.ViewHolder>() {

    private var mListItems: List<String>? = null
    private var mContext: Context? = null
    private var mDataPasser: DataPasser? = null


    fun MyRVAdapter(inputList: List<String>) {
        mListItems = inputList
    }

    fun setListData(inputList: List<String>) {
        this.mListItems = inputList
    }

    class ViewHolder(var itemLayout: View) : RecyclerView.ViewHolder(
        itemLayout
    ) {
        var itemTvData: TextView

        init {
            itemTvData = itemLayout.findViewById<View>(R.id.tv_data) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        try {
            mDataPasser =  mContext as DataPasser
        } catch (e: ClassCastException) {
            throw ClassCastException(mContext.toString() + " must implement DataPasser")
        }

        val layoutInflater = LayoutInflater.from(mContext)
        val myView: View = layoutInflater.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(myView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTvData.text = mListItems!![holder.adapterPosition]
        holder.itemLayout.setOnClickListener { mDataPasser!!.passData(holder.adapterPosition) }
    }

    override fun getItemCount(): Int {
        return mListItems!!.size
    }

    interface DataPasser {
        fun passData(position: Int)
    }

}

