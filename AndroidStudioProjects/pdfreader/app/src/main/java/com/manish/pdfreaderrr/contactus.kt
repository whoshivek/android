package com.manish.pdfreaderrr

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.google.firebase.firestore.FirebaseFirestore
import com.manish.pdfreaderrr.R
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_contactus.*
import kotlinx.android.synthetic.main.fragment_contactus.view.*


class contactus : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_contactus, container, false)
val name = v.name.text
        val email = v.email.text
        val phone = v.phone.text
        val message = v.message.text



        v.submit.setOnClickListener {
            if (email.isNullOrBlank() or   phone.isNullOrBlank() or  name.isNullOrBlank() or message.isNullOrBlank())
            {
                activity?.let { it1 -> Toasty.warning(it1,"Fill Required Details", Toasty.LENGTH_SHORT).show() }
            }
            else
            {
                val map = hashMapOf(
                    "name" to name.toString(),
                "phone" to phone.toString(),
                    "message" to message.toString(),
                "email" to email.toString()
                )
                val document = "${email.toString()}..${phone.toString()}..${message.toString()}..by..${name.toString()}"
                FirebaseFirestore.getInstance().collection("user").document(document).set(map)
                    .addOnCompleteListener {
                    if (it.isSuccessful)
                    {
                        Toasty.success(requireContext() , "Submitted" ,Toasty.LENGTH_SHORT).show()
                    }

                     else
                    {
                        it.exception?.message?.let { it1 ->
                            Toasty.error(requireContext() ,
                                it1,Toasty.LENGTH_SHORT).show()
                        }
                    }
                    }

            }

        }


    return v
    }


}