package com.android_reforcement.extraFonctions;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class AppelToast {
    public static void displayCustomToast(Context context, String toastText) {

        final Toast toast;

        toast = Toast.makeText(context, toastText, Toast.LENGTH_LONG);
        // On peut gérer le positionnement et un décallage (offset)
        toast.setGravity(Gravity.TOP | Gravity.CENTER, 20, 30);
        toast.show();

    }
}
