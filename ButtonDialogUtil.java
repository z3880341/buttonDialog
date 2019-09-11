package com.yt.kangaroo.widgets.button_dialog;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yt.kangaroo.utils.L;

import java.util.Set;

public class ButtonDialogUtil {
    private static final String TAG = "ButtonDialogUtil";
    private ButtonDialog mButtonDialog;
    private PopupWindow mPopupWindow;
    private RecyclerView mRecyclerView;
    private LinearLayout mLinearLayout;
    private ButtonDialogAdapter mButtonDialogAdapter;


    protected ButtonDialogUtil(ButtonDialog buttonDialog){
        this.mButtonDialog = buttonDialog;
        checkData();

    }

    private void checkData(){
        if (mButtonDialog.dataType == 0){
            onErrorListener("error: 没有itemList数据");
            return;

        }
        if (mButtonDialog.itemStringList == null
                && mButtonDialog.itemDrawableList == null
                && mButtonDialog.itemPressedDrawableList == null
                && mButtonDialog.itemNotPressedDrawableList == null){
            onErrorListener("error: 没有itemCotent数据");
            return;

        }
        if (mButtonDialog.dataType == 1 && mButtonDialog.itemStringList.isEmpty()){
            onErrorListener("error: itemStringList数据为为空");
            return;
        }
        if (mButtonDialog.dataType == 2 && mButtonDialog.itemDrawableList.isEmpty()){
            onErrorListener("error: itemDrawableList数据为为空");
            return;
        }
        if (mButtonDialog.dataType == 3){
            if (mButtonDialog.itemStringList.isEmpty()){
                onErrorListener("error: itemStringList数据为空");
                return;
            }
            if (mButtonDialog.itemDrawableList.isEmpty()){
                onErrorListener("error: itemDrawableList数据为空");
                return;

            }
            if (mButtonDialog.itemStringList.size() != mButtonDialog.itemDrawableList.size()){
                onErrorListener("error: itemStringList与itemDrawableList数据长度不一致 itemStringList长度="+mButtonDialog.itemStringList.size()
                        +" itemDrawableList长度="+mButtonDialog.itemDrawableList.size());
                return;
            }
        }
        if (mButtonDialog.dataType == 4){
            if (mButtonDialog.itemPressedDrawableList.isEmpty()){
                onErrorListener("error: itemPressedDrawableList数据为空");
                return;
            }
            if (mButtonDialog.itemNotPressedDrawableList.isEmpty()){
                onErrorListener("error: itemNotPressedDrawableList数据为空");
                return;
            }
            if (mButtonDialog.itemPressedDrawableList.size() != mButtonDialog.itemNotPressedDrawableList.size()){
                onErrorListener("error: itemStringList与itemDrawableList数据长度不一致 " +
                        " itemPressedDrawableList长度=" + mButtonDialog.itemPressedDrawableList.size() +
                        " itemNotPressedDrawableList长度=" + mButtonDialog.itemNotPressedDrawableList.size());
                return;
            }
        }
        if (mButtonDialog.dataType == 5){
            if (mButtonDialog.itemStringList.isEmpty()){
                onErrorListener("error: itemStringList数据为空");
                return;
            }
            if (mButtonDialog.itemPressedDrawableList.isEmpty()){
                onErrorListener("error: itemPressedDrawableList数据为空");
                return;
            }
            if (mButtonDialog.itemNotPressedDrawableList.isEmpty()){
                onErrorListener("error: itemNotPressedDrawableList数据为空");
                return;
            }
            if (mButtonDialog.itemStringList.size() != mButtonDialog.itemPressedDrawableList.size()
                    || mButtonDialog.itemPressedDrawableList.size() != mButtonDialog.itemNotPressedDrawableList.size()){
                onErrorListener("error: itemStringList与itemStringList与itemDrawableList数据长度不一致 " +
                        " itemStringList长度=" + mButtonDialog.itemStringList.size() +
                        " itemPressedDrawableList长度=" + mButtonDialog.itemPressedDrawableList.size() +
                        " itemNotPressedDrawableList长度=" + mButtonDialog.itemNotPressedDrawableList.size());
                return;

            }


        }
        initSize();
        initView();

    }

    private void initSize(){
        DisplayMetrics displayMetrics = mButtonDialog.context.getResources().getDisplayMetrics();
        if (mButtonDialog.dialogWidth == 0){
            mButtonDialog.dialogWidth = displayMetrics.widthPixels;

        }
        if (mButtonDialog.dialogHeight == 0){
            mButtonDialog.dialogHeight = displayMetrics.heightPixels/12;

        }
        if (mButtonDialog.itemButtonWidth == 0){
            switch (mButtonDialog.dataType){
                case 1:
                    mButtonDialog.itemButtonWidth = displayMetrics.widthPixels/mButtonDialog.itemStringList.size();
                    break;

                case 2:
                    mButtonDialog.itemButtonWidth = displayMetrics.widthPixels/mButtonDialog.itemDrawableList.size();
                    break;

                case 3:
                    mButtonDialog.itemButtonWidth = displayMetrics.widthPixels/mButtonDialog.itemStringList.size();
                    break;

                default:
                    break;
            }

        }
        if (mButtonDialog.itemButtonHeight == 0){
            mButtonDialog.itemButtonHeight = displayMetrics.heightPixels/12;

        }
    }

    private void initView(){
        mLinearLayout = new LinearLayout(mButtonDialog.context);
        mLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView = new RecyclerView(mButtonDialog.context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mButtonDialog.context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mButtonDialogAdapter = new ButtonDialogAdapter(mButtonDialog, this);
        mRecyclerView.setAdapter(mButtonDialogAdapter);
        mLinearLayout.addView(mRecyclerView);
        if (mButtonDialog.dialogCorner){
            mLinearLayout.setBackground(ButtonDialogBg.dialogCornerRectangleBg(mButtonDialog.dialogBgColor, mButtonDialog.dialogCornerRadius));

        }else {
            mLinearLayout.setBackground(ButtonDialogBg.dialogRectangleBg(mButtonDialog.dialogBgColor));

        }
        mLinearLayout.setGravity(Gravity.CENTER);
        initPopupWindow(mLinearLayout);
    }

    private void initPopupWindow(View view){
        mPopupWindow = new PopupWindow(view, mButtonDialog.dialogWidth, mButtonDialog.dialogHeight);
//        if (mButtonDialog.dialogCorner){
//            mPopupWindow.setBackgroundDrawable(ButtonDialogBg.dialogCornerRectangleBg(mButtonDialog.dialogBgColor, mButtonDialog.dialogCornerRadius));
//
//        }else {
//            mPopupWindow.setBackgroundDrawable(ButtonDialogBg.dialogRectangleBg(mButtonDialog.dialogBgColor));
//
//        }
        mPopupWindow.setFocusable(mButtonDialog.focusable);
        mPopupWindow.setOutsideTouchable(mButtonDialog.outsideTouchable);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                onDismissListener();
            }
        });

    }

    /**
     * 显示监听回调方法
     */
    protected void onShowListener(){
        switch (mButtonDialog.dialogModeEnum){
            case COMMON:
                if (mButtonDialog.buttonDialogCommonListener != null) {
                    mButtonDialog.buttonDialogCommonListener.onShow();
                }
                break;

            case SINGLE:
                if (mButtonDialog.buttonDialogSingleListener != null){
                    mButtonDialog.buttonDialogSingleListener.onShow();

                }
                break;

            case MANY:
                if (mButtonDialog.buttonDialogManyListener != null) {
                    mButtonDialog.buttonDialogManyListener.onShow();
                }
                break;

            default:
                break;
        }
    }

    /**
     * item点击回调方法(勾选模式)
     * @param position
     * @param isSelection
     */
    protected void onItemClickListener(int position ,boolean isSelection){
        if (mButtonDialog.clickItemDismiss){
            dismiss();
        }
        if (mButtonDialog.dialogModeEnum == DialogModeEnum.SINGLE){
            if (mButtonDialog.buttonDialogSingleListener != null){
                mButtonDialog.buttonDialogSingleListener.onItemClick(position, isSelection);
            }
        }
    }

    /**
     * item点击回调方法(点选模式)
     * @param position
     */
    protected void onItemClickListener(int position){
        if (mButtonDialog.clickItemDismiss){
            dismiss();
        }
        switch (mButtonDialog.dialogModeEnum){
            case COMMON:
                if (mButtonDialog.buttonDialogCommonListener != null) {
                    mButtonDialog.buttonDialogCommonListener.onItemClick(position);
                }
                break;

            case MANY:
                if (mButtonDialog.buttonDialogManyListener != null) {
                    mButtonDialog.buttonDialogManyListener.onCurrentItemClick(position);
                }
                break;

            default:
                break;
        }
    }

    /**
     * 选中item回调(复选模式)
     * @param selecetPositionSet
     */
    protected void onSelectItemListener(Set<Integer> selecetPositionSet){
        if (mButtonDialog.dialogModeEnum == DialogModeEnum.MANY && mButtonDialog.buttonDialogManyListener != null){
            mButtonDialog.buttonDialogManyListener.onSelectItem(selecetPositionSet);
        }
    }

    /**
     * dialog消失回调
     */
    protected void onDismissListener(){
        switch (mButtonDialog.dialogModeEnum){
            case COMMON:
                if (mButtonDialog.buttonDialogCommonListener != null) {
                    mButtonDialog.buttonDialogCommonListener.onDismiss();
                }
                break;

            case SINGLE:
                if (mButtonDialog.buttonDialogSingleListener != null){
                    mButtonDialog.buttonDialogSingleListener.onDismiss();

                }
                break;

            case MANY:
                if (mButtonDialog.buttonDialogManyListener != null) {
                    mButtonDialog.buttonDialogManyListener.onDismiss();
                }
                break;

            default:
                break;
        }
    }

    /**
     * 异常回调
     * @param e
     */
    protected void onErrorListener(String e){
        switch (mButtonDialog.dialogModeEnum){
            case COMMON:
                if (mButtonDialog.buttonDialogCommonListener != null) {
                    mButtonDialog.buttonDialogCommonListener.onError(e);
                }
                break;

            case SINGLE:
                if (mButtonDialog.buttonDialogSingleListener != null){
                    mButtonDialog.buttonDialogSingleListener.onError(e);

                }
                break;

            case MANY:
                if (mButtonDialog.buttonDialogManyListener != null) {
                    mButtonDialog.buttonDialogManyListener.onError(e);
                }
                break;

            default:
                break;
        }

    }

    protected void show(View view, DialogDirectionEnum direction){
        switch (direction){
            case TOP:
                mPopupWindow.showAsDropDown(view, mButtonDialog.marginX, mButtonDialog.marginY, Gravity.TOP);
                break;

            case BOTTOM:
                mPopupWindow.showAsDropDown(view, mButtonDialog.marginX, mButtonDialog.marginY, Gravity.BOTTOM);
                break;
            default:
                break;
        }
        onShowListener();
        mButtonDialogAdapter.notifyDataSetChanged();
    }

    /**
     * 单选模式,选中item显示
     * @param view
     * @param direction
     * @param selectionPosition
     */
    protected void singleSelectiontShow(View view, DialogDirectionEnum direction, int selectionPosition){
        if (mButtonDialog.dialogModeEnum != DialogModeEnum.SINGLE){
            onErrorListener("必需DialogModeEnum.SINGLE单选模式,才能设置预先选中值");
            return;
        }
        mButtonDialogAdapter.setSelectiontPosition(selectionPosition);
        switch (direction){
            case TOP:
                mPopupWindow.showAsDropDown(view, mButtonDialog.marginX, mButtonDialog.marginY, Gravity.TOP);
                break;

            case BOTTOM:
                mPopupWindow.showAsDropDown(view, mButtonDialog.marginX, mButtonDialog.marginY, Gravity.BOTTOM);
                break;
            default:
                break;
        }
        onShowListener();
    }

    /**
     * 复选模式,选中item数组显示
     * @param view
     * @param direction
     * @param selectionItemset
     */
    protected void manySelectiontShow(View view, DialogDirectionEnum direction, Set<Integer> selectionItemset){
        if (mButtonDialog.dialogModeEnum != DialogModeEnum.MANY){
            onErrorListener("必需DialogModeEnum.MANY复选模式,才能设置预先选中值数组");
            return;
        }
        mButtonDialogAdapter.setSelectiontItemSet(selectionItemset);
        switch (direction){
            case TOP:
                mPopupWindow.showAsDropDown(view, mButtonDialog.marginX, mButtonDialog.marginY, Gravity.TOP);
                break;

            case BOTTOM:
                mPopupWindow.showAsDropDown(view, mButtonDialog.marginX, mButtonDialog.marginY, Gravity.BOTTOM);
                break;
            default:
                break;
        }
        onShowListener();
    }

    protected boolean isShowing(){
        return mPopupWindow.isShowing();
    }

    protected void dismiss(){
        mPopupWindow.dismiss();

    }

    protected void destroy(){
        if (mPopupWindow != null){
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        if (mRecyclerView != null){
            mRecyclerView = null;
        }
        if (mButtonDialogAdapter != null){
            mButtonDialogAdapter = null;
        }
        if (mButtonDialog != null){
            mButtonDialog = null;
        }

    }
}
