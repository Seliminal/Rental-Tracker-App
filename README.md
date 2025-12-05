Overview: 

An android application with simple UI, built using Android Studio that allows users to easily record, view, and manage rental properties. The app allows for users to add, edit, and view entries; title, location, price, and image url. This application is built with Kotlin, and utilizes Room Database to store files locally and coroutines to ensure smooth performance.


Features: 

-Add, Edit, View, Delete Rentals;

 Title, Location, Price, Image URL

 
-Room Database

 For saving rental information locally

 
-RecyclerView Display

 For smooth scrolling and an intuitive layout

 
-Loading Spinner

 For a better UX while loading data

 
-Coroutines and lifecycleScope

 For efficient performance while background threads are running

 
 -Glide
 
  For loading image URLs




 Language: Kotlin
 
 Architecture: MVVM (Model View ViewModel)
 
 Storage: Room database
 
 UI: RecyclerView, CardView, EditText, ProgressBar
 
 Async: Coroutines (lifecycleScope)
 
 IDE: Android Studio



 com.example.rentalhousetracker
 
│

 data

── Rental.kt

── RentalDao.kt

── RentalDatabase.kt


 ui

── HomeActivity.kt

── RentalListActivity.kt

── RentalEditActivity.kt



 adapter

── RentalAdapter.kt
    
