package  com.jatin.thinkspeadcloudmonitoring
import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.jatin.thinkspeadcloudmonitoring.R
import com.jatin.thinkspeadcloudmonitoring.RecyclerViewActivity
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivitySplashBinding
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()


        window.statusBarColor = getColor(R.color.white)

// To ensure your status bar icons (battery/time) stay visible:
// If your bar color is DARK, use false. If your bar color is LIGHT, use true.
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

//        Thread{
//            val source = ImageDecoder.createSource(
//                resources,R.drawable.cloud
//            )
//
//            val drawable = ImageDecoder.decodeDrawable(source)
//
//            binding.imgSplash.post {
//                binding.imgSplash.setImageDrawable(drawable)
//                (drawable as? AnimatedImageDrawable)?.start()
//            }
//        }.start()



        Handler(Looper.getMainLooper()).postDelayed({
            if (auth.currentUser != null) {
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
            }
            this.finish()
        }, 3000)

    }
}