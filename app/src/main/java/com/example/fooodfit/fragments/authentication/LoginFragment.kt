package com.example.fooodfit.fragments.authentication

import android.os.Bundle
import android.text.TextUtils
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
//            findNavController().navigate(
//                R.id.action_loginFragment_to_mainFragment,
//                bundleOf(),
//                navOptions {
//                    anim {
//                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
//                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
//                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
//                        exit = androidx.navigation.ui.R.anim.nav_default_enter_anim
//                    }
//                })
//        }

        emailFocusListener()
        passwordFocusListener()

        binding.buttonAuthorization.setOnClickListener{
            if (TextUtils.isEmpty(email.toString()) && TextUtils.isEmpty(password.toString())){
                Toast.makeText(context, "Please check your email or password correct", Toast.LENGTH_SHORT).show()
            }else{
                validateLoginCredentials()
            }
        }

        binding.registrationText.setOnClickListener{
            findNavController().navigate(
                R.id.action_loginFragment_to_registrationFragment,
                bundleOf(),
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_enter_anim
                    }
                })
        }

        return binding.root
    }

    private fun validateLoginCredentials(){
        val validEmail = binding.emailContainerText.helperText == null
        val validPassword = binding.passwordContainerText.helperText == null

        if (validEmail && validPassword){
            findNavController().navigate(
                R.id.action_loginFragment_to_mainFragment,
                bundleOf(),
                navOptions {
                    anim {
                        enter = androidx.navigation.ui.R.anim.nav_default_enter_anim
                        popEnter = androidx.navigation.ui.R.anim.nav_default_pop_enter_anim
                        popExit = androidx.navigation.ui.R.anim.nav_default_pop_exit_anim
                        exit = androidx.navigation.ui.R.anim.nav_default_enter_anim
                    }
                })
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}



