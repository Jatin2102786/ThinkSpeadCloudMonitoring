package  com.jatin.thinkspeadcloudmonitoring
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.WindowCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
//        binding.buttonSignIn.setOnClickListener {
//            val intent = Intent(this,SignUpActivity::class.java)
//            startActivity(intent)
//        }

        window.statusBarColor = getColor(R.color.primary)

// To ensure your status bar icons (battery/time) stay visible:
// If your bar color is DARK, use false. If your bar color is LIGHT, use true.
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false


        binding.buttonSignIn.setOnClickListener {

            binding.pgBar.visibility = View.VISIBLE
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()


            if (email.isEmpty() || password.isEmpty()) {
                binding.pgBar.visibility = View.GONE

                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase Authentication: Sign in with email and password
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign-in success
                        binding.pgBar.visibility = View.GONE

                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()



                        // Navigate to the next activity (e.g., MainActivity)
                        val intent = Intent(this, RecyclerViewActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        binding.pgBar.visibility = View.GONE

                        // If sign-in fails, display a message to the user.
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }

    }
}
