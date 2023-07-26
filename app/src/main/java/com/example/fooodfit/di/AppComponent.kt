package com.example.fooodfit.di

import com.example.fooodfit.MainActivity
import com.example.fooodfit.fragments.authentication.RegistrationFragment
import dagger.Component

@Component
interface AppComponent {
    fun inject(fragment: RegistrationFragment)
}