package com.example.jots.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.jots.viewmodel.LogEntryViewModel
import com.example.jots.R
import com.example.jots.model.LogEntry

class AddFragment: Fragment() {

    private lateinit var mLogEntryViewModel: LogEntryViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mLogEntryViewModel = ViewModelProvider(this)[LogEntryViewModel::class.java]

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
            insertDataToDatabase(view)
        }
        return view

    }

    private fun insertDataToDatabase(view: View?) {
        val content = view?.findViewById<EditText>(R.id.editTextLogEntryContent)?.text.toString()
        if(!TextUtils.isEmpty(content)) {
            val entry = LogEntry(0, content, System.currentTimeMillis())
            mLogEntryViewModel.addEntry(entry)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(), "Please add content!", Toast.LENGTH_SHORT).show()
        }

    }
}
