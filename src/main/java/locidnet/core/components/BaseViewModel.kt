package locidnet.core.components

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    protected val loading = MutableLiveData<Boolean>()

    protected val success = MutableLiveData<String>()

    protected val fail = MutableLiveData<String>()



    inner class ApiCallback : locidnet.core.components.ApiCallback{
        override fun onSuccess(result: String,type:Int) {
            loading.value = false




            try{
                success(result,type)
            }catch (e :Exception){
                fail("",type)

            }





        }

        override fun onFail(failure: String,type:Int) {


            loading.value = false

            fail(failure,type)

        }

    }

    abstract fun success(result : String, type : Int)

    abstract fun fail(result : String, type : Int)

    abstract fun attach(baseActivity: BaseActivity)

}