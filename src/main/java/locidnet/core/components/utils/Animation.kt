package locidnet.core.components.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Handler
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.view.animation.*
import android.view.animation.Animation


object Animation{

    fun slideDown(view: View) {
        view.animate()
                .translationY(view.height.toFloat())
                .alpha(0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        // superfluous restoration
                        view.visibility = View.GONE
                        view.alpha = 1f
                        view.translationY = 0f
                    }
                })
    }

    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        view.alpha = 0f

        if (view.height > 0) {
            slideUpNow(view)
        } else {
            // wait till height is measured
            view.post { slideUpNow(view) }
        }
    }

    private fun slideUpNow(view: View) {
        view.translationY = view.height.toFloat()
        view.animate()
                .translationY(0f)
                .alpha(1f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        view.visibility = View.VISIBLE
                        view.alpha = 1f
                    }
                })
    }

//    fun animateFab(fab : AppCompatImageView){
//
//        fab.clearAnimation()
//
//        val shrink = ScaleAnimation(0.2f,1f,0.2f,1f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f)
//        shrink.duration = 150
//        shrink.interpolator = DecelerateInterpolator()
//        shrink.setAnimationListener(object : Animation.AnimationListener{
//            override fun onAnimationRepeat(p0: Animation?) {
//
//            }
//
//            override fun onAnimationEnd(p0: Animation?) {
//
//                val addIcon = VectorDrawableCompat.create(App.getInstance().resources, R.drawable.ic_action_white, App.getInstance().theme)
//
//                fab.setImageDrawable(addIcon)
//
//
////                val scaleDown = ScaleAnimation(0.2f,1f,0.2f,1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
////                scaleDown.duration = 150
////                scaleDown.interpolator = DecelerateInterpolator()
////                fab.startAnimation(scaleDown)
//            }
//
//            override fun onAnimationStart(p0: Animation?) {
//            }
//
//        })
//
//        Handler().postDelayed({
//            fab.visibility = View.VISIBLE
//            fab.startAnimation(shrink)
//        },500)
//    }


    fun scaleMainShadow(view : View , top : AppCompatImageButton,right : AppCompatImageButton,left : AppCompatImageButton,action : AppCompatImageView,leftX : Float){
        if (view.visibility == View.VISIBLE){
            view.clearAnimation()
            top.clearAnimation()
            right.clearAnimation()
            left.clearAnimation()
            val shrink = ScaleAnimation(1f,0f,1f,0f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f)
            shrink.duration = 300
            shrink.interpolator = AccelerateDecelerateInterpolator()
            shrink.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationRepeat(p0: Animation?) {


                }

                override fun onAnimationEnd(p0: Animation?) {



//                val scaleDown = ScaleAnimation(0.2f,1f,0.2f,1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
//                scaleDown.duration = 150
//                scaleDown.interpolator = DecelerateInterpolator()
//                fab.startAnimation(scaleDown)
                }

                override fun onAnimationStart(p0: Animation?) {

                }

            })

            Handler().postDelayed({
                view.visibility = View.GONE


//                top.animate()
//                        .translationY(top.height.toFloat() * 2)
//                        .setDuration(300)
//                        .setInterpolator(AccelerateInterpolator())
//                        .start()

                right.animate()
                        .translationY((right.height.toFloat() + (right.height.toFloat() / 2)))
                        .translationX(-leftX)
                        .setDuration(300)
                        .setInterpolator(AccelerateInterpolator())
                        .start()

                left.animate()
                        .translationY((left.height.toFloat() + (left.height.toFloat() / 2)))
                        .translationX(leftX)
                        .setDuration(300)
                        .setInterpolator(AccelerateInterpolator())
                        .start()

                action.animate()
                        .translationY(0f).setDuration(300)
                        .setInterpolator(OvershootInterpolator())
                        .start()



                view.startAnimation(shrink)



//                button1.visibility = View.GONE
//                button1.startAnimation(shrink)
//
//                button2.visibility = View.GONE
//                button2.startAnimation(shrink)
//
//                button3.visibility = View.GONE
//                button3.startAnimation(shrink)
            },200)
        }else{
            view.clearAnimation()
            top.clearAnimation()
            right.clearAnimation()
            left.clearAnimation()
            val shrink = ScaleAnimation(0.2f,1f,0.2f,1f, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f)
            shrink.duration = 300
            shrink.interpolator = OvershootInterpolator()
            shrink.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {



//                val scaleDown = ScaleAnimation(0.2f,1f,0.2f,1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
//                scaleDown.duration = 150
//                scaleDown.interpolator = DecelerateInterpolator()
//                fab.startAnimation(scaleDown)
                }

                override fun onAnimationStart(p0: Animation?) {

                }

            })



            Handler().postDelayed({
                view.visibility = View.VISIBLE
                view.startAnimation(shrink)



//                top.animate()
//                        .translationY(-(top.height.toFloat() * 2))
//                        .setDuration(400)
//                        .setInterpolator(OvershootInterpolator())
//                        .start()

                right.animate()
                        .translationY(-(right.height.toFloat() + (right.height.toFloat() / 2)))
                        .translationX(leftX)
                        .setDuration(400)
                        .setInterpolator(OvershootInterpolator())
                        .start()

                left.animate()
                        .translationY(-(left.height.toFloat() + (left.height.toFloat() / 2)))
                        .translationX(-leftX)
                        .setDuration(400)
                        .setInterpolator(OvershootInterpolator())
                        .start()


                action.animate()
                        .translationY(-20f).setDuration(300)
                        .setInterpolator(OvershootInterpolator())
                        .start()
            },200)
        }
    }

}