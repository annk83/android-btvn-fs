package annk.example.listbox.filebrowser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val trans = supportFragmentManager.beginTransaction()
            trans.add(R.id.frag, FileViewerFragment())
            trans.commit()
    }
}