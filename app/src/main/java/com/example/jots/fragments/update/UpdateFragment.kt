package com.example.jots.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.jots.R
import com.example.jots.model.LogEntry
import com.example.jots.viewmodel.LogEntryViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mLogEntryViewModel: LogEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mLogEntryViewModel = ViewModelProvider(this)[LogEntryViewModel::class.java]

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        val contentTextView = view.findViewById<EditText>(R.id.editTextUpdateLogEntryContent)
        val button = view.findViewById<Button>(R.id.add_buttonUpdate)

        contentTextView.setText(args.currentEntry.content)
        button.setOnClickListener {
        updateItem(view)
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem(view: View) {
        val content = view.findViewById<EditText>(R.id.editTextUpdateLogEntryContent).text.toString()

        if(!TextUtils.isEmpty(content)) {
            val updatedEntry = LogEntry(args.currentEntry.id, content, System.currentTimeMillis())
            mLogEntryViewModel.updateEntry(updatedEntry)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Succesfully updated!", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(requireContext(), "Please add content!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteEntry()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteEntry() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mLogEntryViewModel.deleteEntry(args.currentEntry)
            Toast.makeText(requireContext(), "Successfully removed!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") {_,_ ->

        }
        builder.setTitle("Delete this entry?")
        builder.setMessage("Are you sure you want to delete this entry?")
        builder.create().show()
    }
 }