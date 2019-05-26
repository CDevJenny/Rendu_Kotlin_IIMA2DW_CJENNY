package jenny.clement.kotlinzoo

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import jenny.clement.kotlinzoo.model.Post

class PostActivity : AppCompatActivity() {

    lateinit var post: Post

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_text -> {
                val textFragment = TextFragment()
                val bundle = Bundle()
                bundle.putString("text", post.body)
                textFragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_layout, textFragment)
                        .commit()
            }
            R.id.navigation_commentaires -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_layout, CommentFragment())
                        .commit()
            }
            R.id.navigation_author -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_layout, AuthorFragment())
                        .commit()
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        post = intent.getSerializableExtra("post") as Post

        val textFragment = TextFragment()
        val bundle = Bundle()
        bundle.putString("text", post.body)
        textFragment.arguments = bundle

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
