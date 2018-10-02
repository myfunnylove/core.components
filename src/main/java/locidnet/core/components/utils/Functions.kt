package locidnet.core.components.utils

import android.Manifest
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.graphics.drawable.VectorDrawableCompat
import android.support.v4.app.ActivityCompat
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import org.json.JSONArray
import java.lang.Exception
import java.util.*
import java.util.regex.Pattern


fun Int.getResource(context: Context) : String = context.getString(this)
fun Int.getDrawable(context: Context) : Drawable = context.resources.getDrawable(this)
fun Int.getIcon(context: Context): VectorDrawableCompat = VectorDrawableCompat.create(context.resources,this,context.theme)!!

fun Int.setIcon(context: Context) : VectorDrawableCompat = VectorDrawableCompat.create(context.resources,this,context.theme)!!
fun View.showScale(){
    this.setVisibility(View.VISIBLE);
    this.setScaleX(0f)
    this.alpha = 0f
    this.translationY = -100f
    this.setScaleY(0.8f)
    this.animate()
            .scaleX(1f).scaleY(1f)
            .alpha(1f)
            .translationY(0f)
            .setDuration(300)
            .setInterpolator(OvershootInterpolator())
            .start()
}

fun View.slideAnim(from : Float , to: Float,duration : Long ,visibility: Int){
    this@slideAnim.setVisibility(visibility)

    this.translationY = from
    this.animate()
            .translationY(to)
            .setDuration(duration)
            .setInterpolator(OvershootInterpolator())

            .start()
}
fun View.showOnlyScale(from : Float , to: Float,duration : Long = 800){
    this.setVisibility(View.VISIBLE);
    this.setScaleX(from)
    this.setScaleY(from)
    this.animate()
            .scaleX(to).scaleY(to)
            .setDuration(duration)
            .setInterpolator(OvershootInterpolator())
            .start()
}


fun View.backGroundChangeAnimation(id : Int, id2: Int){
    val animator = ObjectAnimator.ofInt(this,"backgroundResource", id,id2)
    animator.interpolator = BounceInterpolator()
    animator.setDuration(100)
    animator.start()
}
fun View.hideScale(){
    this.setVisibility(View.INVISIBLE);
    this.setAlpha(1f);
    this.setScaleX(1f);
    this.setScaleY(1f);
    this.animate()
            .alpha(0f)
            .scaleX(0f).scaleY(0f)
            .setDuration(300)
            .start();
}

//class BasePagerAdapter(manager : FragmentManager, var fragments : Array<BaseFragment>) : FragmentPagerAdapter(manager){
//
//    override fun getItem(position: Int): Fragment = fragments[position]
//
//    override fun getCount(): Int = fragments.size
//
//    override fun getPageTitle(position: Int): CharSequence? = fragments[position].getTitle()
//}



object Functions {

    val tag = "Functions"






    fun showKeyboard(mEtSearch : EditText,context : Context){
        mEtSearch.requestFocus()
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun hideKeyboard(mEtSearch : EditText,context : Context){
        mEtSearch.clearFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEtSearch.windowToken, 0)
    }

    fun getX(view : View) : Float{

        val location = IntArray(2)

        view.getLocationOnScreen(location)

        return location[0].toFloat()

    }

    fun getY(view : View) : Float{

        val location = IntArray(2)

        view.getLocationOnScreen(location)

        return location[1].toFloat()

    }


    fun checkLocationPermission(activity: Activity): Boolean {
        if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 7)
            return false
        }
        return true
    }

    fun checkPermissions(ctx : Activity):Boolean{
        val MULTIPLE_PERMISSIONS = 10
        val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE

        )
        var res = 0

        val listPermissionNeeded = ArrayList<String>()

        for (i in permissions){
            res = ContextCompat.checkSelfPermission(ctx,i)

            if (res != PackageManager.PERMISSION_GRANTED){
                listPermissionNeeded.add(i)
            }
        }

        if (listPermissionNeeded.isNotEmpty())
        {
            val arr:Array<String> = listPermissionNeeded.toTypedArray()
            ActivityCompat.requestPermissions(ctx,arr,MULTIPLE_PERMISSIONS)
            return false
        }else{
            return true
        }
    }


    fun isValidEmailAddress(email: String): Boolean {
        val ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
        val p = java.util.regex.Pattern.compile(ePattern)
        val m = p.matcher(email)
        return m.matches()
    }

    fun isValidUserName(username : String) : String{
        val pattern = Pattern.compile("[A-Za-z0-9_]+")
        if (username.length < 6 ){
            return "less than 6"
        }else if (username.length > 30){
            return "more than 30"
        }else if (!pattern.matcher(username).matches()){
            return "incorrect username"

        }else{
            return  ""
        }

    }

    fun isValidPassword(username : String) : String{
        val pattern = Pattern.compile("[A-Za-z0-9_]+")
        if (username.length < 6 ){
            return "less than 6"
        }else if (username.length > 20){
            return "more than 20"
        }else if (!pattern.matcher(username).matches()){
            return "incorrect password"

        }else{
            return  ""
        }

    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }




//    fun checkResponse(jsonString : String) : RPCResponse{
//
//        var rpcResponse : RPCResponse? = null
//
//        try {
//
//            val isJson = JSONObject(jsonString)
//
//            if (isJson.has("result")){
//
//                val inResult = JSONObject(isJson.optString("result"))
//
//                if (inResult.optBoolean("ok")){
//
////                    if (!inResult.optString("data").isNullOrEmpty()){
////                        rpcResponse = RPCResponse(1,inResult.optString("data"),RPC_VALS.UPLOAD_PHOTO.hashCode())
////                    }else{
//                           rpcResponse = RPCResponse(1,inResult.toString(),isJson.optInt("id"))
////                    }
//
//
//                }else{
//
//
//                    if (isJson.optString("data").isEmpty()) {
//                        rpcResponse = RPCResponse(1,inResult.toString(),isJson.optInt("id"))
//
//                    }else{
//
//                        rpcResponse = RPCResponse(-1, R.string.system_error.getResource(), isJson.optInt("id"))
//
//
//                    }
//                }
//
//            }else if (isJson.optBoolean("ok")){
//
//                rpcResponse = RPCResponse(1,isJson.optString("data"),RPC_VALS.UPLOAD_PHOTO.hashCode())
//
//
//            }else  if (isJson.has("error")){
//                rpcResponse = RPCResponse(0, isJson.optString("error"),isJson.optInt("id"))
//
//
//
//            }else{
//                rpcResponse = RPCResponse(-1, R.string.system_error.getResource(),-1)
//
//            }
//
//
//        }catch (  e:Exception){
//            MLog.d(tag,"${e}")
//
//            rpcResponse = RPCResponse(-1, R.string.system_error.getResource(),-1)
//
//        }
//
//        return rpcResponse!!
//    }
//
//    fun getErrorMessage(body: String) : String{
//
//        try {
//
//            val obj = JSONObject(body)
//
//            val message = JSONObject(obj.optString("message"))
//
//            val text = message.optString(Prefs.instance(App.preferences).getLanguageLocale())
//
//            return text
//
//        }catch (e :Exception){
//            return R.string.system_error.getResource()
//        }
//
//    }
//
//    fun circleParams() : RoundingParams{
//        val roundingParams = RoundingParams.fromCornersRadius(5f)
//       return roundingParams.setRoundAsCircle(true)
//    }

//    fun getBottomDialog(context : Context,text : String,isProgress : Boolean)  : Dialog {
//
//        val bottomDialog = Dialog(context, R.style.BottomDialog)
//        val contentView = LayoutInflater.from(context).inflate(R.layout.item_bottom_dialog_circle, null)
//        bottomDialog.setContentView(contentView)
//
//        if (isProgress){
//            bottomDialog.setCancelable(false)
//            bottomDialog.setCanceledOnTouchOutside(false)
//            contentView.findViewById<ProgressBar>(R.id.progress).visibility = View.VISIBLE
//            contentView.findViewById<TextView>(R.id.errorText).text = R.string.loading.getResource()
//        }else{
//            bottomDialog.setCancelable(true)
//
//            bottomDialog.setCanceledOnTouchOutside(true)
//            contentView.findViewById<ProgressBar>(R.id.progress).visibility = View.GONE
//            contentView.findViewById<TextView>(R.id.errorText).text = text
//        }
//
//        val params = contentView.getLayoutParams() as ViewGroup.MarginLayoutParams
//        params.width = context.getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(context, 16f)
//        params.bottomMargin = DensityUtil.dp2px(context, 8f)
//        contentView.setLayoutParams(params)
//
//        bottomDialog.getWindow().setGravity(Gravity.BOTTOM)
//
//        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation)
//
//        return bottomDialog
//    }
//
//    fun changeLangDialog(context : Context, listener : View.OnClickListener)  : Dialog {
//
//        val bottomDialog = Dialog(context, R.style.BottomDialog)
//        val contentView = LayoutInflater.from(context).inflate(R.layout.item_change_lang, null)
//        bottomDialog.setContentView(contentView)
//
//        bottomDialog.setCancelable(true)
//
//        bottomDialog.setCanceledOnTouchOutside(true)
//        val rucheck = contentView.findViewById<AppCompatImageView>(R.id.ruCheck)
//        val uzCheck = contentView.findViewById<AppCompatImageView>(R.id.uzCheck)
//        val ru = contentView.findViewById<ViewGroup>(R.id.ru)
//        val uz = contentView.findViewById<ViewGroup>(R.id.uz)
//
//        ru.setOnClickListener(listener)
//        uz.setOnClickListener(listener)
//
//        if (Prefs.instance(App.preferences).getLanguageLocale() == "ru"){
//            uzCheck.visibility = View.INVISIBLE
//            rucheck.visibility = View.VISIBLE
//        }else{
//            uzCheck.visibility = View.VISIBLE
//            rucheck.visibility = View.INVISIBLE
//        }
//
//        val params = contentView.getLayoutParams() as ViewGroup.MarginLayoutParams
//        params.width = context.getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(context, 16f)
//        params.bottomMargin = DensityUtil.dp2px(context, 8f)
//        contentView.setLayoutParams(params)
//
//        bottomDialog.getWindow().setGravity(Gravity.BOTTOM)
//
//        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation)
//
//        return bottomDialog
//    }
//
//    fun selectTypePopolnitDialog(context : Context, listener : View.OnClickListener)  : Dialog {
//
//        val bottomDialog = Dialog(context, R.style.BottomDialog)
//        val contentView = LayoutInflater.from(context).inflate(R.layout.item_select_type_popolnit, null)
//        bottomDialog.setContentView(contentView)
//
//        bottomDialog.setCancelable(true)
//
//        bottomDialog.setCanceledOnTouchOutside(true)
//        val ru = contentView.findViewById<ViewGroup>(R.id.ru)
//        val uz = contentView.findViewById<ViewGroup>(R.id.uz)
//
//        ru.setOnClickListener(listener)
//        uz.setOnClickListener(listener)
//
//
//
//        val params = contentView.getLayoutParams() as ViewGroup.MarginLayoutParams
//        params.width = context.getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(context, 16f)
//        params.bottomMargin = DensityUtil.dp2px(context, 8f)
//        contentView.setLayoutParams(params)
//
//        bottomDialog.getWindow().setGravity(Gravity.BOTTOM)
//
//        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation)
//
//        return bottomDialog
//    }
//
//
//    fun selectSafeDialog(context : Context, listener : View.OnClickListener)  : Dialog {
//
//        val bottomDialog = Dialog(context, R.style.BottomDialog)
//        val contentView = LayoutInflater.from(context).inflate(R.layout.item_select_type_safe, null)
//        bottomDialog.setContentView(contentView)
//
//        bottomDialog.setCancelable(true)
//
//        bottomDialog.setCanceledOnTouchOutside(true)
//        val ru = contentView.findViewById<ViewGroup>(R.id.password)
//        val uz = contentView.findViewById<ViewGroup>(R.id.pincode)
//
//        ru.setOnClickListener(listener)
//        uz.setOnClickListener(listener)
//
//        val pincodeText = contentView.findViewById<TextView>(R.id.pincodeText)
//        if (Prefs.instance(App.preferences).getPin().isEmpty()){
//            pincodeText.text = R.string.set_pincode.getResource()
//        }else{
//            pincodeText.text = R.string.change_pincode.getResource()
//        }
//
//
//        val params = contentView.getLayoutParams() as ViewGroup.MarginLayoutParams
//        params.width = context.getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(context, 16f)
//        params.bottomMargin = DensityUtil.dp2px(context, 8f)
//        contentView.setLayoutParams(params)
//
//        bottomDialog.getWindow().setGravity(Gravity.BOTTOM)
//
//        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation)
//
//        return bottomDialog
//    }
//
//
//    fun changeCurrDialog(context : Context, listener : View.OnClickListener)  : Dialog {
//
//        val bottomDialog = Dialog(context, R.style.BottomDialog)
//        val contentView = LayoutInflater.from(context).inflate(R.layout.item_change_curr, null)
//        bottomDialog.setContentView(contentView)
//
//        bottomDialog.setCancelable(true)
//
//        bottomDialog.setCanceledOnTouchOutside(true)
//        val uzsCheck = contentView.findViewById<AppCompatImageView>(R.id.uzsCheck)
//        val usdCheck = contentView.findViewById<AppCompatImageView>(R.id.usdCheck)
//        val rubCheck = contentView.findViewById<AppCompatImageView>(R.id.rubCheck)
//        val uzs = contentView.findViewById<ViewGroup>(R.id.uzs)
//        val usd = contentView.findViewById<ViewGroup>(R.id.usd)
//        val rub = contentView.findViewById<ViewGroup>(R.id.rub)
//
//        uzs.setOnClickListener(listener)
//        usd.setOnClickListener(listener)
//        rub.setOnClickListener(listener)
//
//        if (Prefs.instance(App.preferences).getCurrency() == "uzs"){
//            uzsCheck.visibility = View.VISIBLE
//            usdCheck.visibility = View.INVISIBLE
//            rubCheck.visibility = View.INVISIBLE
//        }else if(Prefs.instance(App.preferences).getCurrency() == "usd"){
//
//            uzsCheck.visibility = View.INVISIBLE
//            usdCheck.visibility = View.VISIBLE
//            rubCheck.visibility = View.INVISIBLE
//        }else {
//            uzsCheck.visibility = View.INVISIBLE
//            usdCheck.visibility = View.INVISIBLE
//            rubCheck.visibility = View.VISIBLE
//        }
//
//        val params = contentView.getLayoutParams() as ViewGroup.MarginLayoutParams
//        params.width = context.getResources().getDisplayMetrics().widthPixels - DensityUtil.dp2px(context, 16f)
//        params.bottomMargin = DensityUtil.dp2px(context, 8f)
//        contentView.setLayoutParams(params)
//
//        bottomDialog.getWindow().setGravity(Gravity.BOTTOM)
//
//        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation)
//
//        return bottomDialog
//    }
//    fun extractAndSaveUserData(body : String) : Boolean {
//
//        try{
//            val js = JSONObject(body)
//            val data = JSONObject(js.optString("data"))
//            MLog.d(tag,"extracted user $data")
//            val prefs = Prefs.instance(App.preferences)
//            val userData = prefs.getUserData()
//
//            if (!data.optString("about").isNullOrEmpty()){
//                userData?.about =data.optString("about")
//            }
//            if (!data.optString("birthday").isNullOrEmpty()){
//                userData?.birthday =data.optString("birthday")
//            }
//
//            if (!data.optString("address").isNullOrEmpty()){
//                userData?.address =data.optString("address")
//            }
//
//            if (!data.optString("email").isNullOrEmpty()){
//                userData?.mail =data.optString("email")
//            }
//            if (!data.optString("name").isNullOrEmpty()){
//                userData?.name =data.optString("name")
//            }
//            if (!data.optString("username").isNullOrEmpty()){
//                userData?.username =data.optString("username")
//            }
//            if (!data.optString("followings").isNullOrEmpty()){
//                userData?.followings =data.optInt("followings",0)
//            }
//
//            userData?.sex =data.optInt("sex")
//
//            prefs.setUserData(userData!!)
//
//            return true
//        }catch ( e:Exception){
//            return false
//        }
//
//    }


    class ProgressDialog : DialogFragment() {

        companion object {
            var fragment : ProgressDialog ? = null
            fun instance(bundle : Bundle?) : ProgressDialog{


                if (fragment == null){


                     fragment = ProgressDialog()

                }
                val bundle = Bundle()

                fragment!!.arguments = bundle
                return fragment!!
            }
        }

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return super.onCreateDialog(savedInstanceState)
        }
    }




}