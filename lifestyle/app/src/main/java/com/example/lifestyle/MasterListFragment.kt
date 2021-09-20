package com.example.lifestyle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestyle.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MasterListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MasterListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var fragmentView : View = inflater.inflate(R.layout.fragment_masterlist_layout, container, false)

        //Get the recycler view
        mRecyclerView = fragmentView.findViewById<View>(R.id.rv_Master) as RecyclerView

        //Tell Android that we know the size of the recyclerview doesn't change
        mRecyclerView!!.setHasFixedSize(true)

        //Set the layout manager

        //Set the layout manager
        layoutManager = LinearLayoutManager(activity)
        mRecyclerView!!.layoutManager = layoutManager

        //Get data from main activity

        //Get data from main activity
        val customListData: CustomListData = requireArguments().getParcelable("item_list")!!
        val inputList: List<String> = customListData.getItemList() as List<String>

        //Set the adapter
        mAdapter = MyRVAdapter()
        (mAdapter as MyRVAdapter).setListData(inputList);
        mRecyclerView!!.adapter = mAdapter

//        retainInstance = true
        return fragmentView
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_masterlist_layout, container, false)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MasterListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MasterListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}