package locidnet.core.components

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(){

    companion object {

        fun <T : BaseActivity?> start(activity: BaseActivity, tag: Int, cls: Class<T>, bundle: Bundle? = null) {

            val intent = Intent(activity, cls)
            intent.putExtra("data", bundle)
            activity.startActivityForResult(intent, tag)

//            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

        }
        fun <T : BaseActivity?> startWithAnim(activity: BaseActivity, tag: Int, cls: Class<T>, anim1 : Int, anim2 : Int,bundle: Bundle? = null) {

            val intent = Intent(activity, cls)
            intent.putExtra("data", bundle)
            activity.startActivityForResult(intent, tag)

            activity.overridePendingTransition(anim1,anim2)

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setupViews()
        inject()
        initVM()

    }

    override fun onStart() {
        super.onStart()
        onBus()
    }

    override fun onStop() {
        super.onStop()
        offBus()
    }

    abstract fun getTag() : String

    abstract fun getLayout() : Int

    abstract fun setupViews()

    abstract fun initVM()

    abstract fun showLoading()

    abstract fun hideLoading()

    abstract fun success(data : String)

    abstract fun fail(data : String)

    abstract fun onBus()

    abstract fun offBus()

    abstract fun inject()







}