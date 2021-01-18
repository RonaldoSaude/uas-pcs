package com.akuaku.githubaku.ui.ini

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.akuaku.githubaku.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide

class RinciUserAktif : AppCompatActivity() {
    companion object {
        const val USERNAME = "username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: RinciUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(USERNAME)
        val bundle = Bundle()
        bundle.putString(USERNAME, username)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(RinciUserViewModel::class.java)

        username?.let { viewModel.setUserDetail(it) }
        viewModel.getUserDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvFollowers.text = "${it.followers} Followers"
                    tvFollowing.text = "${it.following} Following"

                    Glide.with(this@RinciUserAktif)
                        .load(it.avatar_url)
                        .into(tvProfile)
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdaptor(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }

    }
}
