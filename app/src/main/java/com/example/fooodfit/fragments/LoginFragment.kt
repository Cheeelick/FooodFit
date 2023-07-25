package com.example.fooodfit.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fooodfit.R
import com.example.fooodfit.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment: Fragment(R.layout.fragment_login){

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val email = binding.emailEditText.text
        val password = binding.passwordEditText.text
        auth = Firebase.auth

//        if (auth.currentUser != null){
//            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
//        }

        binding.buttonAuthorization.setOnClickListener{
            if (TextUtils.isEmpty(email.toString()) && TextUtils.isEmpty(password.toString())){
                Toast.makeText(context, "Please check your email or password correct", Toast.LENGTH_SHORT).show()
            }else{
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }
        }

        binding.registrationText.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



