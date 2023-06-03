package com.appbase.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.appbase.R
import com.appbase.databinding.FragmentFirstBinding
import com.appbase.demoTest.AppViewModel
import com.appbase.demoTest.LoginEvent
import com.appbase.utils.collectFlow
import com.appbase.utils.extensionFunctions.showToast
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {


    private var _binding: FragmentFirstBinding? = null
    private lateinit var viewModel: AppViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[AppViewModel::class.java]
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            etEmail.addTextChangedListener { email ->
                email?.apply {
                    val event = LoginEvent.EmailEvent(this.toString())
                    viewModel.onEvent(event)
                }
            }
            etPassword.addTextChangedListener { password ->
                password?.apply {
                    val event = LoginEvent.PasswordEvent(this.toString())
                    viewModel.onEvent(event)
                }
            }
            btnLogin.setOnClickListener {
                val event = LoginEvent.Login
                viewModel.onEvent(event)
            }
        }


        observer()
    }

    private fun observer() {
        collectFlow(viewModel.formEventObserver) {
            binding.apply {
                loginState = it
                it.message?.apply {
                    showToast(this)
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}