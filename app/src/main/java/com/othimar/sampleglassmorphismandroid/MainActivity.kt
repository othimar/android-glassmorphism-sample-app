package com.othimar.sampleglassmorphismandroid

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val blurView = findViewById<BlurView>(R.id.blur_view)

        /*clipToOutline ensure that the content will no the content will not show outside the borders,
         specially at the rounded corners*/
        blurView.clipToOutline = true

        val root = findViewById<ViewGroup>(R.id.root)

        //Hide system bar and action bar
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, root).let{controller->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        supportActionBar?.hide()

        //The blurRadius
        val blurRadius = 6f

        /*There are two ways to initialize the blurview depending on the version of android. RenderEffectBlur does not work on older versions.*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            blurView.setupWith(root, RenderEffectBlur())
                .setBlurRadius(blurRadius)
        }else{
            blurView.setupWith(root, RenderScriptBlur(this))
                .setBlurRadius(blurRadius)
        }

    }
}