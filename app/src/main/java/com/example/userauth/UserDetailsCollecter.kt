package com.example.userauth

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.google.firebase.firestore.SetOptions

@Composable
fun UserDetailsCollecter(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore, context: Context){
    var name by remember { mutableStateOf("") }
    var registrationNo by remember { mutableStateOf("") }
    var rollNo by remember { mutableStateOf("") }
    var phoneNo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Provide Your Detail.",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6200EE) // Custom purple color
            )
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = registrationNo,
            onValueChange = { registrationNo = it },
            label = { Text("Registration No") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = rollNo,
            onValueChange = { rollNo = it },
            label = { Text("Roll No") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = phoneNo,
            onValueChange = { phoneNo = it },
            label = { Text("Phone No") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = {
            var user:User = User(name=name,registrationNo=registrationNo,rollNo=rollNo,phoneNo=phoneNo)

            SaveUserDetails(navController=navController,auth =auth , db =db , context =context,user=user )
            //navController.navigate("showuserdetails")
            Handler(Looper.getMainLooper()).postDelayed({
                Toast.makeText(context, "User data updated successfully", Toast.LENGTH_LONG).show()
                navController.navigate("showuserdetails")
            }, 2000) // 1000 milliseconds = 1 second
            
        }) {
            Text(text="Save")

        }
    }
}
//
////@Composable  // do not needs to be composable .
//fun SaveUserDetails(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore, context: Context, user:User){
//    val currentUser = auth.currentUser
//    if (currentUser != null) {
//        val userId = currentUser.uid  // Get logged-in user's UID
//
//        db.collection("users").document(userId)
//            .set(user, SetOptions.merge()) // Merge existing data
//            .addOnSuccessListener {
//                //println("User data saved successfully!")
//                Toast.makeText(context,"User Data saved successfully",Toast.LENGTH_LONG).show()
//                //navController.navigate("showuserdetails")
//            }
//            .addOnFailureListener { e ->
//                //println("Error saving user data: ${e.message}")
//                Toast.makeText(context,"Error saving user data try again",Toast.LENGTH_LONG).show()
//            }
//    } else {
//        //println("No authenticated user found.")
//        Toast.makeText(context,"No user Found",Toast.LENGTH_LONG).show()
//    }
//    return
//}
fun SaveUserDetails(
    navController: NavHostController,
    auth: FirebaseAuth,
    db: FirebaseFirestore,
    context: Context,
    user: User
) {
    val currentUser = auth.currentUser
    if (currentUser != null) {
        val userId = currentUser.uid  // Get logged-in user's UID
        val userDocRef = db.collection("users").document(userId)

        // Fetch existing document
        userDocRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Retrieve existing email and password
                    val existingEmail = document.getString("email")
                    val existingPassword = document.getString("password")

                    // Merge new user data with existing email and password
                    val updatedUserData = user.copy(
                        email = existingEmail ?: user.email,
                        password = existingPassword ?: user.password
                    )

                    userDocRef.set(updatedUserData, SetOptions.merge()) // Merge with existing data
                        .addOnSuccessListener {
                            Toast.makeText(context, "User data updated successfully", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Error updating user data: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                } else {
                    // Document does not exist, create a new one
                    userDocRef.set(user)
                        .addOnSuccessListener {
                            Toast.makeText(context, "New user data saved successfully", Toast.LENGTH_LONG).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(context, "Error saving user data: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error checking user data: ${e.message}", Toast.LENGTH_LONG).show()
            }
    } else {
        Toast.makeText(context, "No user found", Toast.LENGTH_LONG).show()
    }
}
