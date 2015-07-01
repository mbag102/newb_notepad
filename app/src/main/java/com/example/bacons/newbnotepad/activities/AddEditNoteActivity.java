package com.example.bacons.newbnotepad.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.bacons.newbnotepad.R;
import com.example.bacons.newbnotepad.model.Note;

import java.util.Date;


public class AddEditNoteActivity extends AppCompatActivity {

    private EditText title;
    private EditText content;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        title = (EditText) findViewById(R.id.note_title);
        content = (EditText) findViewById(R.id.note_content);

        long noteId = getIntent().getLongExtra("noteId", -1);

        if(noteId >= 0) {

            note = Note.findById(Note.class, noteId);
            title.setText(note.getTitle());
            content.setText(note.getContent());
        } else {
            note = new Note();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_edit_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_btn) {

            note.setContent(content.getText().toString());
            note.setTitle(title.getText().toString());
            note.setCreatedDate(new Date());
            note.save();

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
