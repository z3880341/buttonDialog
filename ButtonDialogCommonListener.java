package com.yt.kangaroo.widgets.button_dialog;

public interface ButtonDialogCommonListener {
    void onShow();
    void onItemClick(int position);
    void onDismiss();
    void onError(String e);
}
