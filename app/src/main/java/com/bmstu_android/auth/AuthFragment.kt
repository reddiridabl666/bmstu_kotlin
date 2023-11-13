package com.bmstu_android.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bmstu_android.auth.databinding.FragmentAuthBinding
import java.security.MessageDigest


const val LOGIN = "admin"
const val PASSWORD_HASH = "5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5"

class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        binding.submitBtn.setOnClickListener {
            val ok = checkCredentials(binding.loginInput.text.toString(), binding.passwordInput.text.toString())
            if (ok) {
                val bundle = Bundle()
                bundle.putString("login", LOGIN)
                findNavController().navigate(R.id.action_authFragment_to_mainFragment, bundle)
            } else {
                Toast.makeText(context, "Auth failed...", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun sha256(input: String): String {
        val bytes = input.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        return login == LOGIN && sha256(password) == PASSWORD_HASH
    }
}