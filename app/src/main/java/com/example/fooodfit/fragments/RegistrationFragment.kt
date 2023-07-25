package com.example.fooodfit.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fooodfit.R
import com.example.fooodfit.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

private const val TAG = "RegistrationFragment"

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


        binding.buttonAuthorization.setOnClickListener{
            val email = binding.emailEditText.text?.trim()
            val password = binding.passwordEditText.text?.trim()

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                Toast.makeText(context, "Please enter your info", Toast.LENGTH_SHORT).show()
            }else{
                registration(email.toString(), password.toString())
            }
        }

        return binding.root
    }

    private fun registration(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context,"Account created", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registrationFragment_to_mainFragment2)
                } else {
                    Log.d(TAG, task.exception.toString())
                    Toast.makeText(context,"Authentication failed.", Toast.LENGTH_SHORT,).show()
                }
            }
    }
}