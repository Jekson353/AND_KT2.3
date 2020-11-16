package com.samoylenko.kt12

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts: List<Post> = listOf(
        Post(
            id = 1,
            author = "Пушкин А.С.",
            content = "У Лукоморья дуб зеленый, Златая цепь на дубе том...",
            published = "01 апреля 1985 года",
            sharing = 0,
            like = 995,
            countVisability = 10,
            likedByMe = false
        ),
        Post(
            id = 2,
            author = "Лермонтов Н.Ю.",
            content = "У Лукоморья дуб зеленый, Златая цепь на дубе том...",
            published = "01 апреля 1985 года",
            sharing = 0,
            like = 2,
            countVisability = 230,
            likedByMe = false
        ),
        Post(
            id = 8,
            author = " Неизвестный автор",
            content = "И знать не знаю, как вас звали...",
            published = "01 апреля 2020 года",
            sharing = 0,
            like = 15,
            countVisability = 6,
            likedByMe = false
        ),
        Post(
            id = 9,
            author = "Грамилов С.В.",
            content = "У Лукоморья дуб зеленый, Златая цепь на дубе том...",
            published = "01 апреля 1944 года",
            sharing = 0,
            like = 995,
            countVisability = 10,
            likedByMe = false
        )
    )
    private val data: MutableLiveData<List<Post>> = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                if (it.likedByMe) {
                    it.copy(likedByMe = !it.likedByMe, like = it.like - 1)
                } else {
                    it.copy(likedByMe = !it.likedByMe, like = it.like + 1)
                }

            }
        }
        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                it.copy(sharing = it.sharing + 1)
            }
        }
        data.value = posts
    }
}