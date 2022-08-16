package com.beemdevelopment.aegis.helpers;

import android.view.View;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.beemdevelopment.aegis.R;

import java.text.BreakIterator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextDrawableHelper {

    private TextDrawableHelper() {

    }

    public static TextDrawable generate(String text, String fallback, View view) {
        if (text == null || text.isEmpty()) {
            if (fallback == null || fallback.isEmpty()) {
                text = "";
            }else{
                text = fallback;
            }
        }

        //When the text is empty, the generated TextDrawable uses a grey background (level 300).
        int def_color=view.getResources().getColor(R.color.text_drawable_def_color);
        List<Integer> bg_colors=Arrays.stream(view.getResources().getIntArray(R.array.text_drawable_colors)).boxed().collect(Collectors.toList());
        ColorGenerator _generator = ColorGenerator.create(bg_colors);
        int color = text.isEmpty()? def_color:_generator.getColor(text);
        return TextDrawable.builder().beginConfig()
                .width(view.getLayoutParams().width)
                .height(view.getLayoutParams().height)
                .endConfig()
                .buildRound(getFirstGrapheme(text).toUpperCase(), color);
    }

    private static String getFirstGrapheme(String text) {
        BreakIterator iter = BreakIterator.getCharacterInstance();
        iter.setText(text);

        int start = iter.first(), end = iter.next();
        if (end == BreakIterator.DONE) {
            return "";
        }

        return text.substring(start, end);
    }
}
