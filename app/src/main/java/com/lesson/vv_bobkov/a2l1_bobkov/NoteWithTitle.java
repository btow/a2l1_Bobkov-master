package com.lesson.vv_bobkov.a2l1_bobkov;

/**
 * Created by samsung on 28.11.2017.
 */

class NoteWithTitle {

    private String mTitle, mText;

    public NoteWithTitle(final String title, final String text) {
        mTitle = title;
        mText = text;
    }

    public NoteWithTitle(int notesNo) {
        mTitle = App.getApp().getString(notesNo);
        mText = "";
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public void editNote(NoteWithTitle noteWithTitle) {
        mTitle = noteWithTitle.getTitle();
        mText = noteWithTitle.getText();
    }
}
