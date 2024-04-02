package  com.jatin.thinkspeadcloudmonitoring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jatin.thinkspeadcloudmonitoring.RecyclerViewActivity
import com.jatin.thinkspeadcloudmonitoring.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var usersRef: DatabaseReference
    //    private lateinit var uri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)

        binding.buttonSignUp.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
//            val name = binding.editTextName.text.toString().trim()
//            val phnNum = binding.editTextContactNumber.text.toString().trim()
//            val experience = binding.editTextExperience.text.toString()
//            val expertise = binding.editTextExpertise.text.toString()


            if (email.isEmpty() && password.isEmpty() ) {//&& name.isEmpty()) {// && phnNum.isEmpty() && experience.isEmpty() && expertise.isEmpty()) {
                Toast.makeText(this, "All fields are compulsory!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase Authentication: Create a new user with email and password
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign-up success
                        Toast.makeText(this, "Sign-up successful", Toast.LENGTH_SHORT).show()
                        database = FirebaseDatabase.getInstance()
                        usersRef = database.getReference("users")

                        // Create a new user
                        val uid = auth.currentUser?.uid
//                        val newUser = User(name, email)
//                        usersRef.child(uid!!).setValue(newUser)
                        val intent = Intent(this, RecyclerViewActivity::class.java)
                        startActivity(intent)

                        // You can navigate to the next screen or perform additional tasks here
                    } else {
                        // If sign-up fails, display a message to the user.
                        Toast.makeText(this, "Sign-up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}