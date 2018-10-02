package locidnet.core.components.custom

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by myfunnylove on 10.01.2018.
 */
abstract class MusicListScroll : RecyclerView.OnScrollListener() {


    var previousTotal = 0
    var loading = true
    var visibleThreshold = 10
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var layoutManager : LinearLayoutManager? =null
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)


        if (layoutManager == null) layoutManager = recyclerView.layoutManager as LinearLayoutManager
        visibleItemCount = recyclerView.childCount
        totalItemCount = layoutManager!!.itemCount
        firstVisibleItem = layoutManager!!.findFirstVisibleItemPosition()
        if (layoutManager!!.findFirstCompletelyVisibleItemPosition() == 0){
            listOnTop(true)
        }else{
            listOnTop(false)
        }
        if (loading) {
            if (totalItemCount > previousTotal) {

                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {

            loadPage()
            loading = true
        }
    }

    fun reset(){
        previousTotal = 0
        loading = true
        visibleThreshold = 10
        firstVisibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
    }


    abstract fun loadPage()

    abstract fun listOnTop(swipe : Boolean)
}