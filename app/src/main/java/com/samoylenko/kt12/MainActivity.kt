package com.samoylenko.kt12

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.samoylenko.kt12.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(
            onLikeListener = {
                viewModel.likeById(it.id)
            },
            onShareListener = {
                viewModel.shareById(it.id)
            }
        )

        binding.listItem.adapter = adapter
        viewModel.data.observe(this, { posts ->
            adapter.submitList(posts)
        })
    }

}
