package com.example.bacons.newbnotepad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bacons.newbnotepad.R;
import com.example.bacons.newbnotepad.adapters.NotesArrayAdapter;
import com.example.bacons.newbnotepad.model.Note;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class NotesListActivity extends AppCompatActivity {

    private ListView notesList;
    private NotesArrayAdapter notesArrayAdapter;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        notesList = (ListView) findViewById(R.id.notes_list);

        notes = new ArrayList<Note>();

        notesArrayAdapter = new NotesArrayAdapter(this, R.layout.note_list_view_item, notes, true);
        notesList.setAdapter(notesArrayAdapter);
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(NotesListActivity.this, AddEditNoteActivity.class);
                intent.putExtra("noteId", notes.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        notes.clear();

        Iterator<Note> notesIter = Note.findAll(Note.class);

        if(notesIter != null) {
            while (notesIter.hasNext()) {
                notes.add(notesIter.next());
            }
        }

        notesArrayAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add_note) {
            startActivity(new Intent(this, AddEditNoteActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
