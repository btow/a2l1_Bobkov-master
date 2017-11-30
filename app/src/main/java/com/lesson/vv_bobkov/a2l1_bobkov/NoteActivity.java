package com.lesson.vv_bobkov.a2l1_bobkov;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoteActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.etNotesTitle)
    EditText etNotesTitle;
    @BindView(R.id.etNotesText)
    EditText etNotesText;
    @BindView(R.id.tvTitleOfNote)
    TextView tvTitleOfNote;
    @BindView(R.id.tvNotesText)
    TextView tvNotesText;
    @BindView(R.id.btnOk)
    Button btnOk;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        ButterKnife.bind(this);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        if (App.NOTES_MODE) {
            etNotesTitle.setVisibility(View.GONE);
            etNotesText.setVisibility(View.GONE);
            tvTitleOfNote.setVisibility(View.VISIBLE);
            tvNotesText.setVisibility(View.VISIBLE);

            for (Map.Entry<Integer, NoteWithTitle> currentSelectedItem:
                 App.getSelectedItems().entrySet()) {

                tvTitleOfNote.setText(currentSelectedItem.getValue().getTitle());
                tvNotesText.setText(currentSelectedItem.getValue().getText());
            }
        } else {

            if (!App.selectedItemsIsEmpty()) {
                etNotesTitle.setText(App.getSelectedItem(0).getTitle());
                etNotesText.setText(App.getSelectedItem(0).getText());
            }
        }
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        switch (view.getId()) {

            case R.id.btnOk:

                if (!App.NOTES_MODE) {

                    if (App.noteWithTitleListIsEmpty()) App.getNoteWithTitleList().clear();

                    if (App.selectedItemsIsEmpty()) {

                        App.addNewNoteToNoteWithTitleList(
                                new NoteWithTitle(
                                        etNotesTitle.getText().toString(),
                                        etNotesText.getText().toString()
                                )
                        );
                    } else {

                        for (Map.Entry<Integer, NoteWithTitle> currentSelectedItem :
                                App.getSelectedItems().entrySet()) {
                            currentSelectedItem.getValue().setTitle(etNotesTitle.getText().toString());
                            currentSelectedItem.getValue().setText(etNotesText.getText().toString());
                            break;
                        }
                    }
                }
            case R.id.btnCancel:
                startActivity(intent);
                break;
        }
    }
}
