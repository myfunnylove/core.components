package locidnet.core.components

import okhttp3.FormBody


interface ApiCallback{
    fun onSuccess(result :String,type : Int)
    fun onFail(failure : String,type : Int)

}



interface IBaseHttpService {

    fun prepare()

    fun dispose()

    fun postQuery(callback : ApiCallback, data : FormBody, route : String, type: Int)

    fun postJsonQuery(callback : ApiCallback, data : String, route : String, type: Int)


    fun postUploadQuery(callback : ApiCallback, data : String, route : String, type: Int)

    fun getQuery(callback: ApiCallback, route : String, type: Int)

}