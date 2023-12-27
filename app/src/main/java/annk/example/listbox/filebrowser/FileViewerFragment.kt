package annk.example.listbox.filebrowser

import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import java.io.File
import java.lang.IllegalArgumentException
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.Arrays


class FileViewerFragment() : Fragment(R.layout.folder_viewer) {
    private var folderName : TextView? = null
    private var action: Button? = null
    private var listView: RecyclerView? = null
    private var dirList : Array<File> = arrayOf()
    private var file : File? = null
    init {
        Log.i("file_viewer", "file viewer start")
        setPath(File(Environment.getExternalStorageDirectory().getAbsolutePath()))
    }
    fun setPath(file: File) {
        this.file = file
        if (!file.isDirectory) throw IllegalArgumentException("Not a directory");
        Log.i(null, file.exists().toString())
        dirList = file.listFiles() ?: arrayOf();
        Log.i(null, Arrays.toString(dirList))
        listView?.adapter?.notifyDataSetChanged()
        folderName?.setText(file.absolutePath)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState) ?: throw IllegalStateException("Must not be null")
        folderName = view.findViewById(R.id.folder_name);
        folderName?.setText(file!!.absolutePath)
        action = view.findViewById(R.id.folder_action)
        listView = view.findViewById(R.id.folder_list)
        val linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        listView!!.layoutManager = linearLayoutManager
        listView!!.adapter = object : Adapter<FileView>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileView {
                return FileView(LayoutInflater.from(context).inflate(R.layout.file_entry, parent, false)) {
                    setPath(it)
                }
            }

            override fun getItemCount(): Int {
                return dirList.size
            }

            override fun onBindViewHolder(holder: FileView, position: Int) {
                holder.setFile(dirList[position])
            }
        }
        listView!!.adapter!!.notifyDataSetChanged()
        return view
    }

}