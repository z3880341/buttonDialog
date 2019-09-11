package com.yt.kangaroo.widgets.button_dialog;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yt.kangaroo.utils.L;

import java.util.HashSet;
import java.util.Set;

public class ButtonDialogAdapter extends RecyclerView.Adapter<ButtonDialogAdapter.ViewHolder> {
    private ButtonDialog mButtonDialog;
    private Set<Integer> mSelecetPositionSet = new HashSet<>();
    private ButtonDialogUtil mButtonDialogUtil;
    private int mStatePressed = android.R.attr.state_pressed;
    private int IMAGE_BUTTON_TYPE = 99901;
    private Integer mCurrentSinglePosition = null;

    protected ButtonDialogAdapter(ButtonDialog buttonDialog, ButtonDialogUtil buttonDialogUtil){
        mButtonDialog = buttonDialog;
        mButtonDialogUtil = buttonDialogUtil;

    }

    /**
     * 设置单选选中
     * @param selectiontPosition
     */
    protected void setSelectiontPosition(int selectiontPosition){
        if (mButtonDialog.dialogModeEnum != DialogModeEnum.SINGLE){
            mButtonDialogUtil.onErrorListener("mButtonDialog.dialogModeEnum 不等于 DialogModeEnum.SINGLE,无法设置单选");
            return;
        }
        switch (mButtonDialog.dataType){
            case 1:
                if (selectiontPosition > mButtonDialog.itemStringList.size()){
                    mButtonDialogUtil.onErrorListener("position位置大于当前itemStringList长度");
                    return;
                }
                break;

            case 2:
                if (selectiontPosition > mButtonDialog.itemDrawableList.size()){
                    mButtonDialogUtil.onErrorListener("position位置大于当前itemDrawableList长度");
                    return;
                }
                break;

            case 3:
                if (selectiontPosition > mButtonDialog.itemStringList.size()){
                    mButtonDialogUtil.onErrorListener("position位置大于当前itemContentList长度");
                    return;
                }
                break;
            case 4:
                if (selectiontPosition > mButtonDialog.itemPressedDrawableList.size()){
                    mButtonDialogUtil.onErrorListener("position位置大于当前itemContentList长度");
                    return;
                }

                break;

            case 5:
                if (selectiontPosition > mButtonDialog.itemPressedDrawableList.size()){
                    mButtonDialogUtil.onErrorListener("position位置大于当前itemContentList长度");
                    return;
                }
                break;
            default:
                break;
        }
        mCurrentSinglePosition = selectiontPosition;
        notifyDataSetChanged();
    }

    /**
     * 设置复选选中
     * @param selecetPositionSet
     */
    protected void setSelectiontItemSet(Set<Integer> selecetPositionSet){
        if (mButtonDialog.dialogModeEnum != DialogModeEnum.MANY){
            mButtonDialogUtil.onErrorListener("mButtonDialog.dialogModeEnum 不等于 DialogModeEnum.MANY,无法设置复选");
            return;
        }
        switch (mButtonDialog.dataType){
            case 1:
                for (int position : selecetPositionSet){
                    if (position > mButtonDialog.itemStringList.size()){
                        mButtonDialogUtil.onErrorListener("PositionArray 位置大于当前itemStringList长度");
                        return;
                    }
                }
                break;

            case 2:
                for (int position : selecetPositionSet){
                    if (position > mButtonDialog.itemDrawableList.size()){
                        mButtonDialogUtil.onErrorListener("PositionArray 位置大于当前itemDrawableList长度");
                        return;
                    }
                }
                break;

            case 3:
                for (int position : selecetPositionSet){
                    if (position > mButtonDialog.itemStringList.size()){
                        mButtonDialogUtil.onErrorListener("PositionArray 位置大于当前itemContentList长度");
                        return;
                    }
                }
                break;

            case 4:
                for (int position : selecetPositionSet){
                    if (position > mButtonDialog.itemPressedDrawableList.size()){
                        mButtonDialogUtil.onErrorListener("PositionArray 位置大于当前itemContentList长度");
                        return;
                    }
                }
                break;

            case 5:
                for (int position : selecetPositionSet){
                    if (position > mButtonDialog.itemPressedDrawableList.size()){
                        mButtonDialogUtil.onErrorListener("PositionArray 位置大于当前itemContentList长度");
                        return;
                    }
                }
                break;

            default:
                break;
        }

        mSelecetPositionSet.clear();
        mSelecetPositionSet.addAll(selecetPositionSet);
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ButtonDialogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final ViewHolder viewHolder;
        if (viewType == IMAGE_BUTTON_TYPE){
            ImageButton imageButton = new ImageButton(mButtonDialog.context);
            imageButton.setMinimumWidth(mButtonDialog.itemButtonWidth);
            imageButton.setMaxWidth(mButtonDialog.itemButtonWidth);
            imageButton.setMinimumHeight(mButtonDialog.itemButtonHeight);
            imageButton.setMaxHeight(mButtonDialog.itemButtonHeight);
            imageButton.setTag(IMAGE_BUTTON_TYPE);
            viewHolder = new ViewHolder(imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mButtonDialog.dialogModeEnum == DialogModeEnum.COMMON){ //普通
                        mButtonDialogUtil.onItemClickListener(viewHolder.getAdapterPosition());

                    }else if (mButtonDialog.dialogModeEnum == DialogModeEnum.SINGLE){ //单选
                        if (mCurrentSinglePosition == null){ //首次选中
                            mCurrentSinglePosition = viewHolder.getAdapterPosition();
                            imageButtonSelection(viewHolder);
                            mButtonDialogUtil.onItemClickListener(viewHolder.getAdapterPosition(), true);
                            notifyDataSetChanged();
                            return;
                        }
                        if (mCurrentSinglePosition == viewHolder.getAdapterPosition()){ //已经被选中过了
                            if(!mButtonDialog.isItemSelectionCancel){ //无法选中取消就跳过
                                return;
                            }
                            mCurrentSinglePosition = null;
                            imageButtonNotSelection(viewHolder);
                            mButtonDialogUtil.onItemClickListener(viewHolder.getAdapterPosition(), false);
                            notifyDataSetChanged();
                            return;

                        }
                        mCurrentSinglePosition = viewHolder.getAdapterPosition();
                        imageButtonSelection(viewHolder);
                        mButtonDialogUtil.onItemClickListener(viewHolder.getAdapterPosition(), true);
                        notifyDataSetChanged();



                    } else if (mButtonDialog.dialogModeEnum == DialogModeEnum.MANY){ //复选
                        for (int selecetPosition : mSelecetPositionSet) { //查找当前是不是已经是选中状态
                            if (selecetPosition == viewHolder.getAdapterPosition()) {
                                if (!mButtonDialog.isItemSelectionCancel) {
                                    return;
                                }
                                mSelecetPositionSet.remove(viewHolder.getAdapterPosition());//已经是选中状态了,所以再次点击是取消选中
                                imageButtonNotSelection(viewHolder);
                                mButtonDialogUtil.onItemClickListener(viewHolder.getAdapterPosition());
                                mButtonDialogUtil.onSelectItemListener(mSelecetPositionSet);
                                return;
                            }
                        }
                        mSelecetPositionSet.add(viewHolder.getAdapterPosition());
                        imageButtonSelection(viewHolder);
                        mButtonDialogUtil.onItemClickListener(viewHolder.getAdapterPosition());
                        mButtonDialogUtil.onSelectItemListener(mSelecetPositionSet);
                    }
                }
            });

        }else {
            Button button = new Button(mButtonDialog.context);
            button.setTag(-1);
            button.setWidth(mButtonDialog.itemButtonWidth);
            button.setHeight(mButtonDialog.itemButtonHeight);
            button.setTextColor(mButtonDialog.itemTextColor);
            viewHolder = new ViewHolder(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mButtonDialog.dialogModeEnum == DialogModeEnum.COMMON){
                        mButtonDialogUtil.onItemClickListener(viewHolder.getAdapterPosition());

                    }else if (mButtonDialog.dialogModeEnum == DialogModeEnum.MANY){
                        mButtonDialogUtil.onItemClickListener(viewHolder.getAdapterPosition());
                        for (int selecetPosition : mSelecetPositionSet){ //查找当前是不是已经是选中状态
                            if (selecetPosition == viewHolder.getAdapterPosition()){
                                mSelecetPositionSet.remove(viewHolder.getAdapterPosition());//已经是选中状态了,所以再次点击是取消选中
                                buttonNotSelection(viewHolder);
                                mButtonDialogUtil.onSelectItemListener(mSelecetPositionSet);
                                return;
                            }
                        }
                        mSelecetPositionSet.add(viewHolder.getAdapterPosition());
                        buttonSelection(viewHolder);
                        mButtonDialogUtil.onSelectItemListener(mSelecetPositionSet);

                    }
                }
            });
        }

        return viewHolder;
    }

    /**
     * button选中
     * @param viewHolder
     */
    private void buttonSelection(ViewHolder viewHolder){
        if (mButtonDialog.itemCorner){ //判断是圆角背景
            viewHolder.button.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemPressedBgColor, mButtonDialog.itemCornerRadius));

        }else {
            viewHolder.button.setBackground(ButtonDialogBg.itemRectangleBg(mButtonDialog.itemPressedBgColor));

        }
    }
    /**
     * button没有选中
     * @param viewHolder
     */
    private void buttonNotSelection(ViewHolder viewHolder){
        if (mButtonDialog.itemCorner){
            viewHolder.button.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemBgColor, mButtonDialog.itemCornerRadius));

        }else {
            viewHolder.button.setBackground(ButtonDialogBg.itemRectangleBg(mButtonDialog.itemBgColor));

        }
    }

    /**
     * imageButton选中
     * @param viewHolder
     */
    private void imageButtonSelection(ViewHolder viewHolder){
        if (mButtonDialog.dataType == 4){
            Drawable pressedDrawable1 = mButtonDialog.itemPressedDrawableList.get(viewHolder.getAdapterPosition());
            pressedDrawable1.setBounds(0, 0, pressedDrawable1.getIntrinsicWidth(), pressedDrawable1.getIntrinsicHeight());
            viewHolder.imageButton.setImageDrawable(pressedDrawable1);
        }
        if (mButtonDialog.dataType == 5){
            Drawable pressedDrawable2 = mButtonDialog.itemPressedDrawableList.get(viewHolder.getAdapterPosition());
            pressedDrawable2.setBounds(0, 0, pressedDrawable2.getIntrinsicWidth(), pressedDrawable2.getIntrinsicHeight());
            viewHolder.imageButton.setImageDrawable(pressedDrawable2);

        }
        if (mButtonDialog.itemCorner){ //判断是圆角背景
            viewHolder.imageButton.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemPressedBgColor, mButtonDialog.itemCornerRadius));
        }else {
            viewHolder.imageButton.setBackground(ButtonDialogBg.itemRectangleBg(mButtonDialog.itemPressedBgColor));

        }

    }

    /**
     * imageButton没有选中
     * @param viewHolder
     */
    private void imageButtonNotSelection(ViewHolder viewHolder){
        if (mButtonDialog.dataType == 4){
            Drawable notPressedDrawable1 = mButtonDialog.itemNotPressedDrawableList.get(viewHolder.getAdapterPosition());
            notPressedDrawable1.setBounds(0, 0, notPressedDrawable1.getIntrinsicWidth(), notPressedDrawable1.getIntrinsicHeight());
            viewHolder.imageButton.setImageDrawable(notPressedDrawable1);

        }

        if (mButtonDialog.dataType == 5){
            Drawable notPressedDrawable2 = mButtonDialog.itemNotPressedDrawableList.get(viewHolder.getAdapterPosition());
            notPressedDrawable2.setBounds(0, 0, notPressedDrawable2.getIntrinsicWidth(), notPressedDrawable2.getIntrinsicHeight());
            viewHolder.imageButton.setImageDrawable(notPressedDrawable2);

        }
        if (mButtonDialog.itemCorner){
            viewHolder.imageButton.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemBgColor, mButtonDialog.itemCornerRadius));

        }else {
            viewHolder.imageButton.setBackground(ButtonDialogBg.itemRectangleBg(mButtonDialog.itemBgColor));

        }

    }

    @Override
    public void onBindViewHolder(@NonNull ButtonDialogAdapter.ViewHolder holder, int position) {
        /**
         * 处理Button或者imageButton的内容
         */
        switch (mButtonDialog.dataType){
            case 1:
                holder.button.setText(mButtonDialog.itemStringList.get(position));
                break;

            case 2:
                Drawable drawable1 = mButtonDialog.itemDrawableList.get(position);
                drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getIntrinsicHeight());
                holder.imageButton.setImageDrawable(drawable1);

                break;

            case 3:
                holder.button.setText(mButtonDialog.itemStringList.get(position));
                Drawable drawable2 = mButtonDialog.itemDrawableList.get(position);
                drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
                holder.button.setCompoundDrawablesRelative(null, drawable2, null, null);
                break;

            case 4:
                Drawable pressedDrawable = mButtonDialog.itemPressedDrawableList.get(position);
                pressedDrawable.setBounds(0, 0, pressedDrawable.getIntrinsicWidth(), pressedDrawable.getIntrinsicHeight());
                Drawable notPressedDrawable = mButtonDialog.itemNotPressedDrawableList.get(position);
                notPressedDrawable.setBounds(0, 0, notPressedDrawable.getIntrinsicWidth(), notPressedDrawable.getIntrinsicHeight());
                if (mButtonDialog.dialogModeEnum == DialogModeEnum.COMMON){ //普通
                    StateListDrawable stateListDrawable = new StateListDrawable();
                    stateListDrawable.addState(new int[]{mStatePressed}, pressedDrawable);
                    stateListDrawable.addState(new int[]{-mStatePressed}, notPressedDrawable);
                    stateListDrawable.setBounds(0, 0, pressedDrawable.getIntrinsicWidth(), pressedDrawable.getIntrinsicHeight());
                    holder.imageButton.setImageDrawable(stateListDrawable);
                    break;

                }
                if (mButtonDialog.dialogModeEnum == DialogModeEnum.SINGLE){ //单选
                    if (mCurrentSinglePosition == null || mCurrentSinglePosition != position){ //选中状态
                        holder.imageButton.setImageDrawable(notPressedDrawable);

                    }else if (mCurrentSinglePosition == position){ //未选中状态
                        holder.imageButton.setImageDrawable(pressedDrawable);

                    }
                    break;
                }
                if (mButtonDialog.dialogModeEnum == DialogModeEnum.MANY){ //多选
                    for (int selectPosition : mSelecetPositionSet){
                        if (selectPosition == position){ //判断当前的按钮是被选中
                            holder.imageButton.setImageDrawable(pressedDrawable);
                            break;

                        }
                    }
                    holder.imageButton.setImageDrawable(notPressedDrawable);//未选中

                }
                break;

            case 5:
                holder.button.setText(mButtonDialog.itemStringList.get(position));
                Drawable pressedDrawable1 = mButtonDialog.itemPressedDrawableList.get(position);
                pressedDrawable1.setBounds(0, 0, pressedDrawable1.getIntrinsicWidth(), pressedDrawable1.getIntrinsicHeight());
                Drawable notPressedDrawable1 = mButtonDialog.itemNotPressedDrawableList.get(position);
                notPressedDrawable1.setBounds(0, 0, pressedDrawable1.getIntrinsicWidth(), notPressedDrawable1.getIntrinsicHeight());

                if (mButtonDialog.dialogModeEnum == DialogModeEnum.COMMON){ //普通
                    StateListDrawable stateListDrawable = new StateListDrawable();
                    stateListDrawable.addState(new int[]{mStatePressed}, pressedDrawable1);
                    stateListDrawable.addState(new int[]{-mStatePressed}, notPressedDrawable1);
                    stateListDrawable.setBounds(0, 0, pressedDrawable1.getIntrinsicWidth(), notPressedDrawable1.getIntrinsicHeight());
                    holder.button.setCompoundDrawablesRelative(null, stateListDrawable, null, null);
                    break;

                }

                if (mButtonDialog.dialogModeEnum == DialogModeEnum.SINGLE){ //单选
                    if (mCurrentSinglePosition == null || mCurrentSinglePosition != position){
                        holder.button.setCompoundDrawablesRelative(null, notPressedDrawable1, null, null);

                    }else if (mCurrentSinglePosition == position){
                        holder.button.setCompoundDrawablesRelative(null, pressedDrawable1, null, null);
                    }
                    break;

                }

                if (mButtonDialog.dialogModeEnum == DialogModeEnum.MANY){ //复选
                    for (int selectPosition : mSelecetPositionSet){
                        if (selectPosition == position){ //判断当前的按钮是被选中
                            holder.button.setCompoundDrawablesRelative(null, pressedDrawable1, null, null);
                            break;

                        }
                    }
                    holder.button.setCompoundDrawablesRelative(null, notPressedDrawable1, null, null);
                }
                break;

            default:
                break;
        }

        /*
         * 下面是开始处理Button或者ImageButton的背景
         */
        addItemViewBg(holder, position);

    }

    /**
     * 添加Button或者ImageButton的背景
     * @param holder
     * @param position
     */
    private void addItemViewBg(ViewHolder holder, int position){
        if (mButtonDialog.dialogModeEnum == DialogModeEnum.COMMON){ //普通模式
            if (mButtonDialog.itemCorner){ //判断是圆角背景
                if (getItemViewType(position) == IMAGE_BUTTON_TYPE){ //判断是ImageButton按键
                    //注意! 普通模式的是点击效果,所以这里设置StateListDrawable的背景
                    holder.imageButton.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemPressedBgColor, mButtonDialog.itemBgColor, mButtonDialog.itemCornerRadius));
                }else { //Button按键
                    holder.button.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemPressedBgColor, mButtonDialog.itemBgColor, mButtonDialog.itemCornerRadius));

                }
            }else {
                if (getItemViewType(position) == IMAGE_BUTTON_TYPE){
                    holder.imageButton.setBackground(ButtonDialogBg.itemRectangleBg( mButtonDialog.itemPressedBgColor, mButtonDialog.itemBgColor));

                }else {
                    holder.button.setBackground(ButtonDialogBg.itemRectangleBg( mButtonDialog.itemPressedBgColor, mButtonDialog.itemBgColor));

                }

            }
        }else if (mButtonDialog.dialogModeEnum == DialogModeEnum.SINGLE){ //单选模式
            if (mCurrentSinglePosition == null || mCurrentSinglePosition != position){
                setSingleOrManyButtonBg(false, holder, position);
            }else if (mCurrentSinglePosition == position){
                setSingleOrManyButtonBg(true, holder, position);
            }
        } else if (mButtonDialog.dialogModeEnum == DialogModeEnum.MANY){ //复选模式
            for (int selectPosition : mSelecetPositionSet){ //查找是否选中过
                if (selectPosition == position){ //判断当前的按钮是被选中
                    setSingleOrManyButtonBg(true, holder, position);
                    return;
                }
            }
            setSingleOrManyButtonBg(false, holder, position);
        }

    }

    /**
     * 设置单选模式与复选模式的按键背景
     * @param isSelecetItem
     * @param viewHolder
     * @param position
     */
    private void setSingleOrManyButtonBg(boolean isSelecetItem, ViewHolder viewHolder, int position){
        if (isSelecetItem){
            if (mButtonDialog.itemCorner){ //判断是圆角背景
                if (getItemViewType(position) == IMAGE_BUTTON_TYPE){//判断是ImageButton按键
                    viewHolder.imageButton.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemPressedBgColor, mButtonDialog.itemCornerRadius));
                }else { //Button按键
                    viewHolder.button.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemPressedBgColor, mButtonDialog.itemCornerRadius));
                }
            }else { //不是圆角
                if (getItemViewType(position) == IMAGE_BUTTON_TYPE){
                    viewHolder.imageButton.setBackground(ButtonDialogBg.itemRectangleBg(mButtonDialog.itemPressedBgColor));
                }else {
                    viewHolder.button.setBackground(ButtonDialogBg.itemRectangleBg(mButtonDialog.itemPressedBgColor));
                }
            }
        }else {
            if (mButtonDialog.itemCorner){
                if (getItemViewType(position) == IMAGE_BUTTON_TYPE){ //判断按键是ImageButton
                    viewHolder.imageButton.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemBgColor, mButtonDialog.itemCornerRadius));
                }else {
                    viewHolder.button.setBackground(ButtonDialogBg.itemCornerRectangleBg(mButtonDialog.itemBgColor, mButtonDialog.itemCornerRadius));
                }

            }else {
                if (getItemViewType(position) == IMAGE_BUTTON_TYPE){
                    viewHolder.imageButton.setBackground(ButtonDialogBg.itemRectangleBg(mButtonDialog.itemBgColor));
                }else {
                    viewHolder.button.setBackground(ButtonDialogBg.itemRectangleBg(mButtonDialog.itemBgColor));
                }
            }

        }
    }


    @Override
    public int getItemCount() {
        switch (mButtonDialog.dataType){
            case 1:
                return mButtonDialog.itemStringList.size();

            case 2:
                return mButtonDialog.itemDrawableList.size();

            case 3:
                return mButtonDialog.itemStringList.size();

            case 4:
                return mButtonDialog.itemPressedDrawableList.size();

            case 5:
                return mButtonDialog.itemStringList.size();

            default:
                break;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (mButtonDialog.dataType == 2 || mButtonDialog.dataType == 4){
            return IMAGE_BUTTON_TYPE;
        }
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        ImageButton imageButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            if (IMAGE_BUTTON_TYPE == (int) itemView.getTag()){
                imageButton = (ImageButton) itemView;
            }else {
                button = (Button) itemView;
            }
        }
    }
}
