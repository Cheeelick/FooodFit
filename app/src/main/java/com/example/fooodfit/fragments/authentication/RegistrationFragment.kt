package com.example.fooodfit.fragments.authentication

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.fooodfit.R
import com.example.fooodfit.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "MainActivity"

class RegistrationFragment: Fragment(R.layout.fragment_registration) {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        emailFocusListener()
        passwordFocusListener()

        binding.buttonAuthorization.setOnClickListener{
            validateLoginCredentials()
        }


        return binding.root
    }


    private fun validateLoginCredentials(){
        val validEmail = binding.emailContainerText.helperText == null
        val validPassword = binding.passwordContainerText.helperText == null

        if (validEmail && validPassword){
            registration()
        }else{
            Toast.makeText(context, "Ooops...", Toast.LENGTH_SHORT).show()
        }
    }


    private fun emailFocusListener(){
        binding.emailEditText.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.emailContainerText.helperText = validEmail()
            }
        }
    }


    private fun validEmail(): String?{
        val email = binding.emailEditText.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return "Invalid Email"
        }
        return null
    }


    private fun passwordFocusListener(){
        binding.passwordEditText.setOnFocusChangeListener{_, focused ->
            if (!focused){
                binding.passwordContainerText.helperText = validPassword()
            }
        }
    }


    private fun validPassword(): String?{
        val password = binding.passwordEditText.text.toString()

        if (password.length < 8){
            return "Minimum 8 character password"
        }
        if (!password.matches(".*[A-Z].*".toRegex())){
            return "Must contain 1 upper-case character"
        }
        if (!password.matches(".*[a-z].*".toRegex())){
            return "Must contain 1 lower-case character"
        }
        return null
    }


    private fun registration(){
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(
                        R.id.action_registrationFragment_to_mainFragment2,
                        bundleOf(),
                        navOptions {
                            anim {
                                enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                                popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                                popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                                exit = androidx.navigation.ui.R.anim.nav_default_enter_anim
                            }
                        })
                } else {
                    Log.d(TAG, task.exception.toString())
                    Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                }
            }
    }
}