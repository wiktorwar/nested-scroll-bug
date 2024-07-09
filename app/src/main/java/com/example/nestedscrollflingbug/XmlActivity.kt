package com.example.nestedscrollflingbug

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.unit.dp
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class XmlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_xml)
        val viewPager = findViewById<ViewPager>(R.id.custom_feed_pager)
        val tabs = findViewById<TabLayout>(R.id.custom_feed_tabs)
        tabs.setupWithViewPager(viewPager)
        viewPager.adapter = object : PagerAdapter() {
            override fun getCount(): Int = 2

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                return ComposeView(this@XmlActivity).apply {
                    setContent {
                        Surface(modifier = Modifier.fillMaxSize()
                            .nestedScroll(rememberNestedScrollInteropConnection())
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .verticalScroll(rememberScrollState())
                                ,
                            ) {
                                repeat(15) {
                                    Box(
                                        modifier = Modifier
                                            .height(150.dp)
                                            .fillMaxWidth()
                                            .background(Color.Red)
                                    )
                                    Spacer(modifier = Modifier.height(50.dp))
                                }
                            }
                        }
                    }
                }.also { container.addView(it) }
            }

            override fun isViewFromObject(view: View, obj: Any): Boolean =
                view == obj

            override fun getPageTitle(position: Int): CharSequence? {
                return "Tab ${position + 1}"
            }
        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
}