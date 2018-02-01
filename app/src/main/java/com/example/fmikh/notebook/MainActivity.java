package com.example.fmikh.notebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by fmikh on 28.01.2018.
 */

public class MainActivity extends Activity {

    public static String NOTE = "com.example.fmikh.notebook.ContentActivity";
    public static int CONTENT_REQUEST = 1;

    private ArrayAdapter<Note> arrayAdapter;
    private static List<Note> listNote = new ArrayList<Note>();

    private ListView listViewNotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button buttonAdd = findViewById(R.id.add);
        listViewNotes = findViewById(R.id.list);
        listViewNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = (Note) adapterView.getAdapter().getItem(i);
                note.setNumber(i);
                showNote(note);
            }
        });

        listViewNotes.setEmptyView(findViewById(R.id.empty_list));
        arrayAdapter = new ArrayAdapter<Note>(getApplicationContext(), R.layout.listview, listNote);
        listViewNotes.setAdapter(arrayAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note();
                note.setName(getResources().getString(R.string.new_note));
                showNote(note);
            }
        });
    }

    private void showNote(Note note) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra(NOTE, note);
        startActivityForResult(intent, CONTENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CONTENT_REQUEST) {

            Note note = null;
            switch (resultCode) {
                case RESULT_CANCELED:

                    break;

                case ContentActivity.RESULT_SAVE:
                    note = (Note) data.getSerializableExtra(NOTE);
                    note.setDate(new Date());
                    addNote(note);
                    break;

                case ContentActivity.RESULT_DELETE:
                    note = (Note) data.getSerializableExtra(NOTE);
                    deleteNote((Note) data.getSerializableExtra(NOTE));
                    break;

                default:
                    break;
            }
        }
    }

    private void addNote(Note note) {
        if (note.getNumber() == null) {
            listNote.add(note);
        } else {
            listNote.set(note.getNumber(), note);
        }

        Collections.sort(listNote);
        arrayAdapter.notifyDataSetChanged();
    }

    private void deleteNote(Note note) {
        listNote.remove(note.getNumber().intValue());
        arrayAdapter.notifyDataSetChanged();

    }
}
