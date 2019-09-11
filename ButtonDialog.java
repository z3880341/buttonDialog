package com.yt.kangaroo.widgets.button_dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.ColorInt;
import java.util.List;
import java.util.Set;

public class ButtonDialog {
    protected Context context;
    protected DialogModeEnum dialogModeEnum = DialogModeEnum.COMMON;//按键模式 默认是普通模式
    protected boolean clickItemDismiss = true;//点击后是否退出
    protected int dialogWidth = 0;
    protected int dialogHeight = 0;
    protected int marginX = 0;
    protected int marginY = 0;
    protected boolean dialogCorner = false;//对话框是否圆角
    protected float dialogCornerRadius = 0; //圆角半径
    protected int itemButtonWidth = 0;
    protected int itemButtonHeight = 0;
    protected int dialogBgColor = Color.WHITE;
    protected int itemBgColor = Color.WHITE;
    protected int itemPressedBgColor = Color.WHITE;
    protected boolean itemCorner = false;//item是否圆角
    protected float itemCornerRadius = 0; //圆角半径
    protected int itemTextColor = Color.BLACK;
    protected List<String> itemStringList = null;
    protected List<Drawable> itemDrawableList = null;
    protected List<Drawable> itemPressedDrawableList = null;
    protected List<Drawable> itemNotPressedDrawableList = null;
    protected int dataType = 0; //0没有数据 1=只有字符串数据 2=只有图片数据 3=字符串加图片 4=按下图片和没按下图片组合 5=字符串和按下图片和没按下图片组合
    protected ButtonDialogCommonListener buttonDialogCommonListener = null;
    protected ButtonDialogSingleListener buttonDialogSingleListener = null;
    protected ButtonDialogManyListener buttonDialogManyListener = null;
    protected boolean isItemSelectionCancel = true; //选中后,再次点击是否还可以取消选中
    protected boolean outsideTouchable = false;
    protected boolean focusable = false;

    /**
     * 实例化对话框
     * @param context
     * @param modeEnum 对话框模式 目前提供
     *                 DialogModeEnum.COMMON 普通模式
     *                 DialogModeEnum.SINGLE 单选模式
     *                 DialogModeEnum.MANY   复选模式
     *
     */
    public ButtonDialog(Context context, DialogModeEnum modeEnum){
        this.context = context;
        this.dialogModeEnum = modeEnum;

    }

    /**
     * 设置是否点击item后Dialog会自动消失
     * @param whether
     * @return
     */
    public ButtonDialog setClickItemDismiss(boolean whether){
        this.clickItemDismiss = whether;
        return this;

    }

    /**
     * 设置Dialog的大小
     * @param dialogWidth
     * @param dialogHeight
     * @return
     */
    public ButtonDialog setDialogSize(int dialogWidth, int dialogHeight){
        this.dialogWidth = dialogWidth;
        this.dialogHeight = dialogHeight;
        return this;

    }

    /**
     * 设置与显示View的X上的距离
     * @param marginX
     * @return
     */
    public ButtonDialog setMarginX(int marginX) {
        this.marginX = marginX;
        return this;
    }

    /**
     * 设置与显示View的Y上的距离
     * @param marginY
     * @return
     */
    public ButtonDialog setMarginY(int marginY) {
        this.marginY = marginY;
        return this;
    }

    /**
     * 设置对话框圆角
     * @param whether 是否圆角
     * @param cornerRadius 圆角半径值
     * @return
     */
    public ButtonDialog setDialogCorner(boolean whether, float cornerRadius){
        this.dialogCorner = whether;
        this.dialogCornerRadius = cornerRadius;
        return this;

    }

    /**
     * 设置对话框的背景颜色
     * @param color
     * @return
     */
    public ButtonDialog setDialogBgColor(@ColorInt int color){
        this.dialogBgColor = color;
        return this;

    }

    /**
     * 设置item的大小
     * @param itemButtonWidth
     * @param itemButtonHeight
     * @return
     */
    public ButtonDialog setItemButtonSize(int itemButtonWidth, int itemButtonHeight){
        this.itemButtonWidth = itemButtonWidth;
        this.itemButtonHeight = itemButtonHeight;
        return this;

    }

    /**
     * 设置item的背景颜色
     * @param itemBgColor 未按下的背景颜色
     * @param itemPressedBgColor  按下的背景颜色
     * @return
     */
    public ButtonDialog setItemBgColor(@ColorInt int itemBgColor, @ColorInt int itemPressedBgColor){
        this.itemBgColor = itemBgColor;
        this.itemPressedBgColor = itemPressedBgColor;
        return this;

    }

    /**
     * 设置item圆角
     * @param whether 是否圆角
     * @param itemCornerRadius 圆角半径值
     * @return
     */
    public ButtonDialog setItemCorner(boolean whether, float itemCornerRadius){
        this.itemCorner = whether;
        this.itemCornerRadius = itemCornerRadius;
        return this;

    }

    /**
     * 设置item的文本颜色
     * @param itemTextColor
     * @return
     */
    public ButtonDialog setItemTextColor(@ColorInt int itemTextColor){
        this.itemTextColor = itemTextColor;
        return this;

    }

    /**
     * 设置item的内容为文本(纯文本模式)
     * @param itemStringList
     * @return
     */
    public ButtonDialog setItemTextContent(List<String> itemStringList){
        this.dataType = 1;
        this.itemStringList = itemStringList;
        return this;

    }

    /**
     * 设置item的内容为图片(纯图片模式)
     * @param itemDrawableList
     * @return
     */
    public ButtonDialog setItemDrawableContent(List<Drawable> itemDrawableList){
        this.dataType = 2;
        this.itemDrawableList = itemDrawableList;
        return this;

    }

    /**
     * 设置item的内容为文字+图片(文字和图片模式)
     * @param itemStringList
     * @param itemDrawableList
     * @return
     */
    public ButtonDialog setItemTextAndDrawableContent(List<String> itemStringList, List<Drawable> itemDrawableList){
        this.dataType = 3;
        this.itemStringList = itemStringList;
        this.itemDrawableList = itemDrawableList;
        return this;

    }

    /**
     * 设置item内容为未按下的图片 + 按下图片的组合(纯图片带点击效果模式)
     * @param itemPressedDrawableList
     * @param itemNotPressedDrawableList
     * @return
     */
    public ButtonDialog setItemPressedDrawableContent(List<Drawable> itemPressedDrawableList, List<Drawable> itemNotPressedDrawableList){
        this.dataType = 4;
        this.itemPressedDrawableList = itemPressedDrawableList;
        this.itemNotPressedDrawableList = itemNotPressedDrawableList;
        return this;

    }

    /**
     * 设置item内容为文字 + 未按下的图片 + 按下图片的组合(文字/图片带点击效果模式)
     * @param itemStringList
     * @param itemPressedDrawableList
     * @param itemNotPressedDrawableList
     * @return
     */
    public ButtonDialog setItemTextAndPressedDrawableContent(List<String> itemStringList, List<Drawable> itemPressedDrawableList, List<Drawable> itemNotPressedDrawableList){
        this.dataType = 5;
        this.itemStringList = itemStringList;
        this.itemPressedDrawableList = itemPressedDrawableList;
        this.itemNotPressedDrawableList = itemNotPressedDrawableList;
        return this;

    }

    /**
     * 设置普通模式的监听
     * @param buttonDialogCommonListener
     * @return
     */
    public ButtonDialog setButtonDialogCommonListener(ButtonDialogCommonListener buttonDialogCommonListener){
        this.buttonDialogCommonListener = buttonDialogCommonListener;
        return this;

    }

    /**
     * 设置单选模式的监听
     * @param buttonDialogSingleListener
     * @return
     */
    public ButtonDialog setDialogSingleListener(ButtonDialogSingleListener buttonDialogSingleListener) {
        this.buttonDialogSingleListener = buttonDialogSingleListener;
        return this;
    }

    /**
     * 设置复选模式的监听
     * @param buttonDialogManyListener
     * @return
     */
    public ButtonDialog setButtonDialogManyListener(ButtonDialogManyListener buttonDialogManyListener){
        this.buttonDialogManyListener = buttonDialogManyListener;
        return this;
    }

    /**
     * 设置item选中后再次点击可以取消选中
     * @param itemSelectionCancel
     * @return
     */
    public ButtonDialog setItemSelectionCancel(boolean itemSelectionCancel) {
        this.isItemSelectionCancel = itemSelectionCancel;
        return this;
    }

    /**
     * 设置点击外部取消Dialog
     * @param outsideTouchable
     */
    public ButtonDialog setOutsideTouchable(boolean outsideTouchable) {
        this.outsideTouchable = outsideTouchable;
        return this;
    }

    /**
     * 设置聚焦
     * @param focusable
     * @return
     */
    public ButtonDialog setFocusable(boolean focusable){
        this.focusable = focusable;
        return this;

    }

    public Build build(){
        return new Build(this);

    }


    public class Build{
        private ButtonDialogUtil mButtonDialogUtil;

        private Build(ButtonDialog buttonDialog){
            mButtonDialogUtil = new ButtonDialogUtil(buttonDialog);

        }

        /**
         * 显示Dialog
         * @param view
         * @param direction
         */
        public void show(View view, DialogDirectionEnum direction){
            mButtonDialogUtil.show(view, direction);

        }

        /**
         * 单选模式下,带选中item 显示Dialog
         * @param view
         * @param direction
         * @param selectionPosition
         */
        public void singleSelectiontShow(View view, DialogDirectionEnum direction, int selectionPosition){
            mButtonDialogUtil.singleSelectiontShow(view, direction, selectionPosition);

        }

        /**
         * 复选模式下,带选中item数组 显示Dialog
         * @param view
         * @param direction
         * @param selectionItemset
         */
        public void manySelectiontShow(View view, DialogDirectionEnum direction, Set<Integer> selectionItemset){
            mButtonDialogUtil.manySelectiontShow(view, direction, selectionItemset);

        }

        /**
         * 对话框是否显示
         * @return
         */
        public boolean isShowing(){
            return mButtonDialogUtil.isShowing();

        }

        public void dismiss(){
            mButtonDialogUtil.dismiss();

        }

        public void destroy(){
            mButtonDialogUtil.destroy();

        }
    }
}
