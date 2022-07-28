package com.example.jots.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jots.viewmodel.LogEntryViewModel
import com.example.jots.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment(), SearchView.OnQueryTextListener{


//    private lateinit var mLogEntryViewModel: LogEntryViewModel
    private lateinit var adapter : ListAdapter
    private val mLogEntryViewModel: LogEntryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        adapter= ListAdapter()
        val recyclerView =  view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

//        mLogEntryViewModel = ViewModelProvider(this)[LogEntryViewModel::class.java]
        mLogEntryViewModel.getAllEntries().observe(viewLifecycleOwner, Observer { entry ->
            adapter.setData(entry)
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteAllEntries()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllEntries() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mLogEntryViewModel.deleteAllEntries()
            Toast.makeText(requireContext(), "Successfully removed everything!", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_,_ ->

        }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything ?")
        builder.create().show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
       return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null) {
            searchDatabase(query)
        }
        return true
    }
    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        mLogEntryViewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }
}