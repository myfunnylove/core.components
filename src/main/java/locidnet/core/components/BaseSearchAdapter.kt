package locidnet.core.components

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import locidnet.core.components.custom.SearchFilter.AbstractFilter
import locidnet.core.components.custom.SearchFilter.IFilter

abstract class BaseSearchAdapter<T,E : RecyclerView.ViewHolder>(val context : Context, var data : List<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() , IFilter<T> {

    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    public var originalList: List<T>? = data

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        return createHolder(inflater.inflate(getLayout(),parent,false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]
        initData(holder, position,item)

    }

    override fun getFilteredResults(): AbstractFilter<T> {

        return object : AbstractFilter<T>(data) {


            override fun getFilteredResults(constraint: String): List<T> {
                val results = ArrayList<T>()


                for (item in originalList!!) {




                        results.add(searchText(item))

                }
                return results
            }

            override  fun refresh(abcList: List<T>) {
                data = ArrayList(abcList)
                this@BaseSearchAdapter.notifyDataSetChanged()
            }
        }

    }

    abstract fun searchText(t : T) : T

    abstract fun initData(holder: RecyclerView.ViewHolder, position: Int,item : T)

    abstract fun getLayout() : Int

    abstract fun createHolder(view :View) : E

    abstract fun update(list : List<T>)


}