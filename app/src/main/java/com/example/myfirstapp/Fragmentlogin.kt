package com.example.myfirstapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.example.myfirstapp.databinding.FragmentFragmentloginBinding
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class Fragmentlogin : Fragment() {
    lateinit var binding: FragmentFragmentloginBinding
    lateinit var email: EditText
    lateinit var pass: EditText
    lateinit var backbtn: ImageView
    lateinit var loginbtn: Button
    lateinit var rt:TextView
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        val model = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        model.serverresponse.observe(viewLifecycleOwner){ serverResponse ->
            if(serverResponse!=null) {
                Toast.makeText(requireContext(), "Logged in Failure", Toast.LENGTH_SHORT).show()
                rt.visibility = View.VISIBLE
                rt.setText("Failed to Login")
            } else {
                Toast.makeText(requireContext(), "Logged in Successful", Toast.LENGTH_SHORT).show()
                activity?.let {
                    val intent = Intent(it, MainActivity::class.java)
                    it.startActivity(intent)
                }
            }
        }
        backbtn.setOnClickListener {
            activity?.let{
                val intent = Intent (it, MainActivity::class.java)
                it.startActivity(intent)
            }
        }
        loginbtn.setOnClickListener {
            if (email.text.toString().trim().isEmpty()) {
                // email.requestFocus()
                email.setError("Enter Email")
            } else if (!isEmailValid(email.text.toString())) {
                //email.requestFocus()
                email.setError("Enter Valid Email")
            } else if (pass.text.toString().isEmpty()) {
                // pass.requestFocus()
                pass.setError("Enter Password")
            } else {
                model.login(email.text.toString(),pass.text.toString())
            }

            return@setOnClickListener
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFragmentloginBinding.inflate(layoutInflater)
        loginbtn= binding.loginbtn
        backbtn =binding.imageView2
        email=binding.email
        pass=binding.pass
        rt=binding.responseTV
        return binding.root

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragmentlogin.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragmentlogin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}