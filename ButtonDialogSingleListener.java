package com.yt.kangaroo.widgets.button_dialog;

public interface ButtonDialogSingleListener {
    void onShow();
    void onItemClick(int position, boolean isSelection);
    void onDismiss();
    void onError(String e);
}
