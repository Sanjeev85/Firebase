package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Realtimedb : AppCompatActivity() {
    private lateinit var usersRef: DatabaseReference
    private lateinit var userList: ArrayList<User>
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realtimedb)

        usersRef = FirebaseDatabase.getInstance().getReference("players")
        userList = ArrayList()

        recyclerView = findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this)

//        usersRef.child("user-4").removeValue()
//        saveAuthors()
        getData()
    }

    private fun getData() {
        usersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                if (snapshot.exists()) {
                    for (currUser in snapshot.children) {
                        val empData = currUser.getValue(User::class.java)!!
                        userList.add(empData)
                    }
                    val mAdapter = UserAdapter(userList)
                    recyclerView.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun saveAuthors() {
        // take in put from user
        val userId = "user-4"
        val username = "Doe"
        val email = "john@example.com"
        val user = User(userId, username, email)
        usersRef.child(userId).setValue(user)
    }
}