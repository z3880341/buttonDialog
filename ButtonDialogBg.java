package com.yt.kangaroo.widgets.button_dialog;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

import com.yt.kangaroo.R;

public class ButtonDialogBg {

    protected static GradientDrawable dialogRectangleBg(@ColorInt int color){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setColor(color);
        return gradientDrawable;

    }

    protected static GradientDrawable dialogCornerRectangleBg(@ColorInt int color, float cornerRadius){
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(cornerRadius);
        gradientDrawable.setColor(color);
        return gradientDrawable;

    }

    protected static GradientDrawable itemRectangleBg(@ColorInt int color){
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(GradientDrawable.RECTANGLE);
        pressedDrawable.setColor(color);
        return pressedDrawable;

    }

    protected static GradientDrawable itemCornerRectangleBg(@ColorInt int color, float cornerRadius){
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(GradientDrawable.RECTANGLE);
        pressedDrawable.setColor(color);
        pressedDrawable.setCornerRadius(cornerRadius);
        return pressedDrawable;

    }

    protected static StateListDrawable itemRectangleBg(@ColorInt int pressedColor, @ColorInt int notPressedColor){
        int pressed = android.R.attr.state_pressed;
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(GradientDrawable.RECTANGLE);
        pressedDrawable.setColor(pressedColor);

        GradientDrawable notPressedDrawable = new GradientDrawable();
        notPressedDrawable.setShape(GradientDrawable.RECTANGLE);
        notPressedDrawable.setColor(notPressedColor);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{pressed}, pressedDrawable);
        stateListDrawable.addState(new int[]{-pressed}, notPressedDrawable);
        return stateListDrawable;

    }

    protected static StateListDrawable itemCornerRectangleBg(@ColorInt int pressedColor, @ColorInt int notPressedColor , float cornerRadius){
        int pressed = android.R.attr.state_pressed;
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(GradientDrawable.RECTANGLE);
        pressedDrawable.setColor(pressedColor);
        pressedDrawable.setCornerRadius(cornerRadius);

        GradientDrawable notPressedDrawable = new GradientDrawable();
        notPressedDrawable.setShape(GradientDrawable.RECTANGLE);
        notPressedDrawable.setColor(notPressedColor);
        notPressedDrawable.setCornerRadius(cornerRadius);

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{pressed}, pressedDrawable);
        stateListDrawable.addState(new int[]{-pressed}, notPressedDrawable);
        return stateListDrawable;

    }

}
