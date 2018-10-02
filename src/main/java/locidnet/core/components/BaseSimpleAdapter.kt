package locidnet.core.components

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseSimpleAdapter<T,E : RecyclerView.ViewHolder>(val context : Context, val data : List<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return createHolder(inflater.inflate(getLayout(),parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        initData(holder, position,item)

    }


    abstract fun getLayout() : Int

    abstract fun createHolder(view :View) : E

    abstract fun update(list : List<T>)

    abstract fun initData(holder: RecyclerView.ViewHolder, position: Int,item : T)


}
