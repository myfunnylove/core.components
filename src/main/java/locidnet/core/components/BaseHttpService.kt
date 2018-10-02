package locidnet.core.components

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import locidnet.core.components.utils.MLog
import locidnet.core.components.utils.getResource
import okhttp3.*

import java.lang.Exception
import kotlin.concurrent.thread


class BaseHttpService(val uiThread : Handler,val context : Context, val request: Request,val client: OkHttpClient) : HandlerThread("Bringo_Courier"),IBaseHttpService {

     private constructor(builder: Builder) : this(builder.uiThread!!,builder.context,builder.request!!,builder.client!!)

     class Builder(val context : Context){

        var uiThread : Handler? = null
         private set
        var client : OkHttpClient? = null
         private set
        var request: Request? = null
         private set


         fun build() = BaseHttpService(this)


         fun threadMode(thread : Handler) = apply { uiThread =  thread }
         fun setClient(client : OkHttpClient) = apply { this@Builder.client =  client }
         fun setRequest(req : Request) = apply { request =  req }
    }

    companion object {
        val url = "http://apteka.getmuzon.com/api/web/v1"
        val main_url = "http://apteka.getmuzon.com/frontend/web"
        val tag ="ApiService:=> "
        private val JSON = MediaType.parse("application/json;charset=utf-8")

    }




    var bgThread : Handler? = null
    var bgGetThread : Handler? = null
    var bgJob  : BgJob? = null
    var bgJsonJob  : BgJsonJob? = null
    var getGetJob  : BGGetJob? = null
    var bgUploadJob  : BgUploadJob? = null

    var jsonDataString:FormBody?  =null
    var jsonData:String?  =null
    var route = ""
    override  fun prepare(){
        if(state == Thread.State.NEW){
            start()
        }
    }



    override fun dispose(){
        if (bgThread != null) bgThread = null

        if (bgJob != null) bgJob = null
        if (bgJsonJob != null) bgJsonJob = null
        if (bgUploadJob != null) bgUploadJob = null

        quit()

    }

    @Synchronized override fun postQuery(callback : ApiCallback, data : FormBody, route : String, type: Int){
        MLog.d(tag,"post request metodiga kirdi...")

        jsonDataString = data
        this@BaseHttpService.route = route

        if (bgJob == null){
            bgJob = BgJob(callback)
            bgThread = Handler(looper,bgJob)

        }

        bgThread?.obtainMessage(type)!!.sendToTarget()

    }

    @Synchronized override fun postJsonQuery(callback : ApiCallback, data : String, route : String, type: Int){
        MLog.d(tag,"post request metodiga kirdi...")

        jsonData = data
        this@BaseHttpService.route = route

        if (bgJsonJob == null){
            bgJsonJob = BgJsonJob(callback)
            bgThread = Handler(looper,bgJsonJob)

        }

        bgThread?.obtainMessage(type)!!.sendToTarget()

    }



    override fun postUploadQuery(callback : ApiCallback, data : String, route : String, type: Int){
        MLog.d(tag,"post request metodiga kirdi...")

        jsonData = data
        this@BaseHttpService.route = route

        if (bgUploadJob == null){
            bgUploadJob = BgUploadJob(callback)
            bgThread = Handler(looper,bgUploadJob)

        }

        bgThread?.obtainMessage(type)!!.sendToTarget()

    }


    @Synchronized override fun getQuery(callback: ApiCallback, route : String, type: Int){
        this@BaseHttpService.route = route

        if (getGetJob == null){
            getGetJob = BGGetJob(callback)
            bgGetThread = Handler(looper,getGetJob)

        }

        bgGetThread?.obtainMessage(type)!!.sendToTarget()

    }

    inner class BGGetJob(val callback : ApiCallback) : Handler.Callback{


        override fun handleMessage(p0: Message?): Boolean {

            val type = p0!!.what

            try {


                val request = Request.Builder()


                MLog.d(tag,"jonatilayotgan malumotlarni korish...")

//                val body = RequestBody.create(JSON,jsonDataString)


                request
                        .url(url +route)


                /*val token = Prefs.instance(App.preferences).getUserToken()
                if (!token.isEmpty()){

                    request.addHeader("Auth-Token",token)
                }
                val mls = System.currentTimeMillis()
                request.addHeader("timepoint","${mls}")
                val beforeMd = url+route+"${mls}AptekaApi"
                MLog.d(tag,"before md5: $beforeMd")
                val md5 = FunctionsJava.md5(beforeMd)

                MLog.d(tag,"md5: $md5")
                request.addHeader("key",md5)*/
                val response = client.newCall(request.build()).execute()
                MLog.d(tag,"$route - dan response keldi...")

                if (response.isSuccessful){

                    MLog.d(tag,"$route - dan response successfull")
                    if (response.body() != null) {

                        val responseString = response.body()!!.string()
                        MLog.d(tag,"$route - dan response body $responseString")


                        uiThread.post{
                            callback.onSuccess(responseString,type)
                        }

                    }else{
                        uiThread.post {
                            callback.onFail(R.string.network_error.getResource(context),type)
                        }
                    }
                }else{
                    MLog.d(tag,"$route - dan response unsucessfull javob keldi...")
                    MLog.d(tag,"$route - dan response code ${response.code()}")

                    uiThread.post {
                        callback.onFail(R.string.network_error.getResource(context),type)
                    }
                }
            }catch (e :Exception){
                MLog.d(tag,"$route - dan response exception javob keldi...")
                MLog.d(tag,"$route- exception ${e}")
                uiThread.post {
                    callback.onFail(R.string.network_error.getResource(context),type)
                }
            }
            return true
        }

    }

    inner class BgJob(val callback : ApiCallback) : Handler.Callback{


        override fun handleMessage(p0: Message?): Boolean {
            val type = p0!!.what


            try {




                val response = client.newCall(request).execute()
                MLog.d(tag,"$route - dan response keldi...")

                if (response.isSuccessful){

                    MLog.d(tag,"$route - dan response successfull")
                    if (response.body() != null) {

                        val responseString = response.body()!!.string()
                        MLog.d(tag,"$route - dan response body $responseString")


                        uiThread.post{
                            callback.onSuccess(responseString,type)
                        }

                    }else{
                        uiThread.post {
                            callback.onFail(R.string.network_error.getResource(context),type)
                        }
                    }
                }else{
                    MLog.d(tag,"$route - dan response unsucessfull javob keldi...")
                    MLog.d(tag,"$route - dan response code ${response.code()}")

                    uiThread.post {
                        callback.onFail(R.string.network_error.getResource(context),type)
                    }
                }
            }catch (e :Exception){
                MLog.d(tag,"$route - dan response exception javob keldi...")
                MLog.d(tag,"$route- exception ${e}")
                uiThread.post {
                    callback.onFail(R.string.network_error.getResource(context),type)
                }
            }
            return true
        }

    }
    inner class BgJsonJob(val callback : ApiCallback) : Handler.Callback{


        override fun handleMessage(p0: Message?): Boolean {
            val type = p0!!.what


            try {




                val response = client.newCall(request).execute()
                MLog.d(tag,"$route - dan response keldi...")

                if (response.isSuccessful){

                    MLog.d(tag,"$route - dan response successfull")
                    if (response.body() != null) {

                        val responseString = response.body()!!.string()
                        MLog.d(tag,"$route - dan response body $responseString")


                        uiThread.post{
                            callback.onSuccess(responseString,type)
                        }

                    }else{
                        uiThread.post {
                            callback.onFail(R.string.network_error.getResource(context),type)
                        }
                    }
                }else{
                    MLog.d(tag,"$route - dan response unsucessfull javob keldi...")
                    MLog.d(tag,"$route - dan response code ${response.code()}")

                    uiThread.post {
                        callback.onFail(R.string.network_error.getResource(context),type)
                    }
                }
            }catch (e :Exception){
                MLog.d(tag,"$route - dan response exception javob keldi...")
                MLog.d(tag,"$route- exception ${e}")
                uiThread.post {
                    callback.onFail(R.string.network_error.getResource(context),type)
                }
            }
            return true
        }

    }


    inner class BgUploadJob(val callback : ApiCallback) : Handler.Callback{


        override fun handleMessage(p0: Message?): Boolean {
            val type = p0!!.what


            try {




                val response = client.newCall(request).execute()
                MLog.d(tag,"$route - dan response keldi...")

                if (response.isSuccessful){

                    MLog.d(tag,"$route - dan response successfull")
                    if (response.body() != null) {

                        val responseString = response.body()!!.string()
                        MLog.d(tag,"$route - dan response body $responseString")


                        uiThread.post{
                            callback.onSuccess(responseString,type)
                        }

                    }else{
                        uiThread.post {
                            callback.onFail(R.string.network_error.getResource(context),type)
                        }
                    }
                }else{
                    MLog.d(tag,"$route - dan response unsucessfull javob keldi...")
                    MLog.d(tag,"$route - dan response code ${response.code()}")

                    uiThread.post {
                        callback.onFail(R.string.network_error.getResource(context),type)
                    }
                }
            }catch (e :Exception){
                MLog.d(tag,"$route - dan response exception javob keldi...")
                MLog.d(tag,"$route- exception ${e}")
                uiThread.post {
                    callback.onFail(R.string.network_error.getResource(context),type)
                }
            }
            return true
        }

    }


}