package com.example.kirito.simplecalculate;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by kirito on 2017.01.16.
 */

public class CustomButton extends Button {
    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        setTextSize(12f);
    }

}
