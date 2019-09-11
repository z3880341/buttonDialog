package com.yt.kangaroo.widgets.button_dialog;

import java.util.Set;

public interface ButtonDialogManyListener {
    void onShow();
    void onCurrentItemClick(int position);
    void onSelectItem(Set<Integer> selecetPositionSet);
    void onDismiss();
    void onError(String e);
}
