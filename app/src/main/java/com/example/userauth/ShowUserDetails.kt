package com.example.userauth

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun ShowUserDetails(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore, context: Context){
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var registrationNo by remember { mutableStateOf("") }
    var rollNo by remember { mutableStateOf("") }
    var phoneNo by remember { mutableStateOf("") }

    val userId = auth.currentUser?.uid ?: null
    if(userId !=null){
        val docRef = db.collection("users").document(userId)
        docRef.get().addOnSuccessListener { document->
            if(document.exists()){
                name = document.getString("name")?:"N/a"
                registrationNo = document.getString("registrationNo")?:"N/a"
                rollNo = document.getString("rollNo")?:"N/a"
                phoneNo = document.getString("phoneNo")?:"N/a"
                if (auth.currentUser != null) {
                    email = auth.currentUser?.email ?: "N/A"
                }


            }

        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text ="Aman : "+ name,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE), // Custom purple color
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .width(250.dp) // Set specific width
                .wrapContentSize()
        )
        Text(
            text = "Email : "+email,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE), // Custom purple color
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .width(250.dp) // Set specific width
                .wrapContentSize()
        )
        Text(
            text = "Reg No: " +registrationNo,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE), // Custom purple color
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .width(250.dp) // Set specific width
                .wrapContentSize()
        )
        Text(
            text =  "Roll " + rollNo,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE), // Custom purple color
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .width(250.dp) // Set specific width
                .wrapContentSize()
        )
        Text(
            text =  "Phone: " + phoneNo,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE), // Custom purple color
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            modifier = Modifier
                .width(250.dp) // Set specific width
                .wrapContentSize()
        )


    }
}