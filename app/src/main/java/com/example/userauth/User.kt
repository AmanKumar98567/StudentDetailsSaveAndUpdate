package com.example.userauth

//data class User(
//    var name:String="",
//    var registrationNo:String="",
//    var rollNo:String="",
//    var phoneNo:String=""
//)

data class User(
    val name: String = "",
    val registrationNo: String = "",
    val rollNo: String = "",
    val phoneNo: String = "",
    val email: String = "",  // Add email field
    val password: String = ""  // Add password field
)
