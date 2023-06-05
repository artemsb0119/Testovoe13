package com.example.testovoe13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.activity.viewModels
import androidx.databinding.adapters.ActionMenuViewBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.testovoe13.databinding.ActivityMenuBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

class MenuActivity : AppCompatActivity(), Cupon.OnButtonClickListener {

    private lateinit var tabLayout: TabLayout
    private lateinit var binding: ActivityMenuBinding

    val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = findViewById(R.id.tabLayout)

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "Esportes A-Z"
        tab1.setIcon(R.drawable.esportes)
        tabLayout.addTab(tab1)

        val tab2: TabLayout.Tab = tabLayout.newTab()
        tab2.text = "Ao vivo"
        tab2.setIcon(R.drawable.vivo)
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab()
        tab3.text = "Cupom"
        tab3.setIcon(R.drawable.cupom)
        tabLayout.addTab(tab3)

        val tab4: TabLayout.Tab = tabLayout.newTab()
        tab4.text = "Promocoes"
        tab4.setIcon(R.drawable.promocoes)
        tabLayout.addTab(tab4)

        val tab5: TabLayout.Tab = tabLayout.newTab()
        tab5.text = "Apostas"
        tab5.setIcon(R.drawable.apostas)
        tabLayout.addTab(tab5)

        val tab6: TabLayout.Tab = tabLayout.newTab()
        tab6.text = "Virtuais"
        tab6.setIcon(R.drawable.virtuais)
        tabLayout.addTab(tab6)

        val tab7: TabLayout.Tab = tabLayout.newTab()
        tab7.text = "Cassino"
        tab7.setIcon(R.drawable.cassino)
        tabLayout.addTab(tab7)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                when (position) {
                    2 -> {
                        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_layout)

                        if (currentFragment != null) {
                            supportFragmentManager.beginTransaction()
                                .hide(currentFragment)
                                .commit()
                        }

                        val fragmentManager = supportFragmentManager
                        val fragmentTransaction = fragmentManager.beginTransaction()
                        fragmentTransaction.add(R.id.frame_layout, Cupon())
                        fragmentTransaction.addToBackStack(null)
                        fragmentTransaction.commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // Код, который будет выполняться при отмене выбора вкладки
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_layout)

                if (currentFragment !is Cupon) {
                    val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_layout)

                    if (currentFragment != null) {
                        supportFragmentManager.beginTransaction()
                            .hide(currentFragment)
                            .commit()
                    }

                    val fragmentManager = supportFragmentManager
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.add(R.id.frame_layout, Cupon())
                    fragmentTransaction.addToBackStack(null)
                    fragmentTransaction.commit()
                }
            }
        })
        replaceFragment(Home())
        val observer = Observer<List<Match>> { matches ->
            // Обновление иконки в TabLayout с использованием количества записей
            val tab = tabLayout.getTabAt(2) // Индекс нужной вкладки в TabLayout
            val count = matches.size // Количество записей
            updateTabIcon(tab, count) // Метод для обновления иконки
        }

        sharedViewModel.getMatches().observe(this, observer)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, Home(), "HomeFragment")
        fragmentTransaction.commit()
    }

    override fun onButtonClick() {
        replaceFragment(Home())
    }

    private fun updateTabIcon(tab: Tab?, count: Int) {
        val badgeDrawable = tab?.orCreateBadge
        if (count > 0) {
            badgeDrawable?.number = count
            badgeDrawable?.isVisible = true
        } else {
            badgeDrawable?.isVisible = false
        }
    }
}
