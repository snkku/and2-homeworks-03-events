package ru.netology.nmedia

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.utils.Shorter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val shorter = Shorter()

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )

        with(binding){
            author.text = post.author
            published.text = post.published
            content.text = post.content
            viewedCountText.text = shorter.short(post.viewed.toDouble())
            if (post.likedByMe)
                imLikes.setImageResource(R.drawable.image_liked)

            likesCountText.text = shorter.short(post.likes.toDouble())
            shareCountText.text = shorter.short(post.shared.toDouble())

            root.setOnClickListener {
                Log.d(TAG, "onCreate: Root clicked")
            }

            avatar.setOnClickListener {
                Log.d(TAG, "onCreate: Avatar clicked")
            }

            imLikes.setOnClickListener {
                Log.d(TAG, "onCreate: Likes clicked")
                post.likedByMe = !post.likedByMe
                imLikes.setImageResource(
                    if (post.likedByMe) R.drawable.image_liked else R.drawable.image_like
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likesCountText.text = shorter.short(post.likes.toDouble())
            }
            imShare.setOnClickListener {
                Log.d(TAG, "Share button pressed")
                post.shared ++
                shareCountText.text = shorter.short(post.shared.toDouble())
            }
        }
    }
}