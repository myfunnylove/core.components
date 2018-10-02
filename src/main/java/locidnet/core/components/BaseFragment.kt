package locidnet.core.components

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(){

    lateinit var rootView : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(getLayout(),container,false)
        initView()

        return rootView
    }

    abstract fun getTitle() : String

    abstract fun getLayout() : Int

    abstract fun initView()

    abstract fun success(data : String)

    abstract fun fail(data : String)

}