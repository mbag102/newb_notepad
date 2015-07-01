package com.example.bacons.newbnotepad.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.example.bacons.newbnotepad.model.Note;
import com.example.bacons.newbnotepad.R;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by mbagliojr on 2/11/15.
 */
public class NotesArrayAdapter extends ArrayAdapter<Note> {

    private Context mContext;
    private List<Note> data;
    private int layoutResourceId;
    private boolean deleteEnabled;

    public boolean REACHED_THE_END = false;

    public NotesArrayAdapter(Context mContext, int layoutResourceId, List<Note> data, boolean deleteEnabled) {
        super(mContext, layoutResourceId, data);

        this.deleteEnabled = deleteEnabled;
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    public NotesArrayAdapter(Context mContext, int layoutResourceId, List<Note> data) {
        this(mContext, layoutResourceId, data, false);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    /*
     * The convertView argument is essentially a "ScrapView" as described is Lucas post
     * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
     * It will have a non-null value when ListView is asking you recycle the row layout.
     * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
     */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
            ((SwipeLayout) convertView).setSwipeEnabled(deleteEnabled);
        }

        if (position == getCount() - 1) {
            REACHED_THE_END = true;
        }

        // object item based on the position
        final Note note = data.get(position);

        if(note != null) {
            TextView noteTitle = (TextView) convertView.findViewById(R.id.title);
            noteTitle.setText(note.getTitle());

            SimpleDateFormat sdf = new SimpleDateFormat(mContext.getString(R.string.dateFormat));

            TextView noteDate = (TextView) convertView.findViewById(R.id.date);
            noteDate.setText(sdf.format(note.getCreatedDate()));


            if (deleteEnabled) {
                View left = convertView.findViewById(R.id.delete);
                left.setTag(position);
                left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        note.delete();
                        data.remove(Integer.parseInt(v.getTag().toString()));
                        ((SwipeLayout) v.getParent()).close();
                        notifyDataSetChanged();
                    }
                });

            }
        }


        //convertView.setBackgroundColor(Color.parseColor(CONTACTS_ROWCOLORS[position % CONTACTS_ROWCOLORS.length]));

        return convertView;

    }

}
