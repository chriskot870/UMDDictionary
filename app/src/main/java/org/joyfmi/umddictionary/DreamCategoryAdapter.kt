/*
 * Copyright (C) 2022 Chris Kottaridis
 */
package org.joyfmi.umddictionary

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import java.io.File

/**
 * Adapter for the Dream Category Fragment
 */
class DreamCategoryAdapter(context: Context) :
    RecyclerView.Adapter<DreamCategoryAdapter.CategoryViewHolder>() {

    private val categories: List<String> = listOf("All",
        "Animals",
        "Body Parts",
        "Buildings and Rooms",
        "Clothing",
        "Colors",
        "Common Objects",
        "Jewels",
        "Miscellaneous",
        "Numbers",
        "People",
        "Vehicles",
        "Weather"
            )

    init {
        val path = File(context.filesDir, "symbols.xml")
        Log.v("TAG1",path.toString())
    }

    /**
     * Provides a reference for the views needed to display items in your list.
     */
    class CategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    /**
     * Creates new views with R.layout.item_view as its template
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamCategoryAdapter.CategoryViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = DreamSymbolAdapter
        return DreamCategoryAdapter.CategoryViewHolder(layout)
    }

    /**
     * Replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = categories.get(position)
        holder.button.text = item.toString()

        // Assigns a [OnClickListener] to the button contained in the [ViewHolder]
        holder.button.setOnClickListener {
            // Create an action from WordList to DetailList
            // using the required arguments
            val action = DreamCategoryFragmentDirections.actionDreamCategoryFragmentToDreamSymbolFragment(category = holder.button.text.toString())
            // Navigate using that action
            holder.view.findNavController().navigate(action)
        }
    }

    // Setup custom accessibility delegate to set the text read with
    // an accessibility service
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host?.context?.getString(R.string.look_up_symbols)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}