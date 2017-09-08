package com.ots.tdd.onthespectrum;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Ethan on 9/7/2017.
 */

public class ProfileTextWatcher implements TextWatcher {

    private EditText editText;

    public ProfileTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Auto-generated method stub
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Auto-generated method stub
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        this.editText.removeTextChangedListener(this);
        this.editText.setText(this.editText.toString());
        this.editText.addTextChangedListener(this);
    }
}
