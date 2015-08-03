package com.google.eldeveloper13.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class FileListAdapater extends ArrayAdapter<File> {

    private File currentDir;

    private class ViewHolder {
        TextView txtFileName;
    }

    public FileListAdapater(Context context, File currentDir, List<File> files) {
        super(context, -1, files);
        this.currentDir = currentDir;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_file_row, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.txtFileName = (TextView) convertView.findViewById(R.id.textview_fileName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        File file = getItem(position);
        if (file.equals(currentDir.getParentFile())) {
            viewHolder.txtFileName.setText("..");
        } else if (file.isDirectory()){
            viewHolder.txtFileName.setText(file.getName()+"/");
        } else {
            viewHolder.txtFileName.setText(file.getName());
        }

        return convertView;
    }
}
