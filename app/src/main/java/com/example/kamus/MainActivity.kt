package com.example.kamus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

// Latihan lanjutan 3: Navigation Component menggantikan pasangFragment() manual.
// Tidak perlu lagi mengecek savedInstanceState, karena NavController
// menyimpan dan memulihkan tab yang sedang dibuka secara otomatis.
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Id menu di bottom_nav_menu.xml sama dengan id destination di nav_graph.xml
        bottomNav.setupWithNavController(navHost.navController)
    }
}
