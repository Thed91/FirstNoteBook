package com.example.fmikh.notebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by fmikh on 28.01.2018.
 */

public class ContentActivity extends Activity {

    public static final int RESULT_SAVE = 100;
    public static final int RESULT_DELETE = 101;

    private static final int NAME_LENGTH = 20;

    private EditText text;
    private Note note;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        text = (EditText)findViewById(R.id.content_text);
        note = (Note)getIntent().getSerializableExtra(MainActivity.NOTE);
        text.setText(note.getContent());

        Button button_back = (Button)findViewById(R.id.back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button button_save = (Button)findViewById(R.id.content_save);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDocument();
                finish();
            }
        });
        Button button_delete = (Button)findViewById(R.id.content_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage(R.string.confirm_delete);

                builder.setPositiveButton(R.string.delete,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                setResult(RESULT_DELETE, getIntent());
                                finish();

                            }
                        });
                builder.setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    private void saveDocument(){
        StringBuilder sb = new StringBuilder(text.getText());

        if (sb.length() > NAME_LENGTH) {
            sb.delete(NAME_LENGTH, sb.length()).append("...");
        }

        String tmpName = sb.toString().trim().split("\n")[0];

        String name = (tmpName.length() > 0) ? tmpName : note.getName();

        note.setName(name);

        if (note.getContent()!=null && text.getText().toString().equals(note.getContent())){
            setResult(RESULT_CANCELED, getIntent());
        }else{
            note.setContent(sb.toString());
            setResult(RESULT_SAVE, getIntent());
        }
    }
}
