package locidnet.core.components.utils

import android.util.Log


object MLog{

    fun d(tag : String, msg : String){
        Log.d("BASEAPP_LOG::",tag +"::"+msg+"\n")
    }
}