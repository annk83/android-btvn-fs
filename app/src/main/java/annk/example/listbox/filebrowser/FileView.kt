package annk.example.listbox.filebrowser

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.io.File
import java.util.function.Consumer

class FileView(private val view : View, fileConsumer: Consumer<File>) : ViewHolder(view) {
    private val image : ImageView  = view.findViewById(R.id.file_entry_icon)
    private val name : TextView = view.findViewById(R.id.file_entry_name)
    private var file : File? = null
    init {
        view.setOnClickListener {
           if(file!=null && file!!.isDirectory)
               fileConsumer.accept(file!!)
        }
    }
    fun setFile(file : File) {
        this.file = file
        if(file.isDirectory) image.setImageResource(R.drawable.folder)
        else {
            if(file.name.endsWith("png") || file.name.endsWith("jpeg") || file.name.endsWith("jpg"))
                image.setImageResource(R.drawable.image)
            else if(file.name.endsWith("txt"))
                image.setImageResource(R.drawable.text_cino)
            else image.setImageResource(R.drawable.file)
        }
        name.text = file.name
    }
}