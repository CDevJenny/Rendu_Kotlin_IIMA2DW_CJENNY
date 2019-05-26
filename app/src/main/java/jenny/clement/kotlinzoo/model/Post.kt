package jenny.clement.kotlinzoo.model

import java.io.Serializable

data class Post (
    var userId: Int,
    var id: Int,
    var title: String,
    var body: String
) : Serializable

