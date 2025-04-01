package com.example.userauth

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun Navigation(navController: NavHostController, auth: FirebaseAuth, db: FirebaseFirestore, context: Context){
    NavHost(navController = navController, startDestination ="startscreen"){
        composable("startscreen"){
            StartScreen(auth = auth, context = context, navController = navController)
        }
        composable("signup") {
            SignUpScreen(auth = auth, context=context,navController=navController)
        }
        composable("signin"){
            SignInScreen(auth = auth, context = context,navController=navController)
        }
        composable("home") {
            HomeScreen()
        }
        composable("userdetailsscreen"){
            UserDetailsCollecter(navController=navController, auth = auth, db = db  ,context=context)
        }
        composable("showuserdetails"){
            ShowUserDetails(navController=navController, auth = auth, db = db  ,context=context)
        }
        composable("useroptions"){
            UserOptionsScreen(navController = navController, auth = auth, db = db, context = context)
        }
    }
}
