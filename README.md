# buttonDialog
一个横向的按键对话框,有点类似与选中文本的弹出框


使用方法说明


	/**
     * 字体工具对话框
     */
     
     
     
    private void showFontToolsDialog(){
        if (mFontToolsDialog == null){
            int dialogWidth = mDisplayMetrics.widthPixels/2;
            int dialogHeight = (int)(mDisplayMetrics.heightPixels*0.06);
            int itemWidth = dialogWidth/3;
            int itemHeight = dialogHeight;

            List<Drawable> itemPressedDrawableList = new ArrayList<>();
            itemPressedDrawableList.add(getResources().getDrawable(R.drawable.ic_font_bold_white,null));
            itemPressedDrawableList.add(getResources().getDrawable(R.drawable.ic_font_italic_white,null));
            itemPressedDrawableList.add(getResources().getDrawable(R.drawable.ic_font_underline_white,null));
            List<Drawable> itemNotPressedDrawableList = new ArrayList<>();
            itemNotPressedDrawableList.add(getResources().getDrawable(R.drawable.ic_font_bold_black,null));
            itemNotPressedDrawableList.add(getResources().getDrawable(R.drawable.ic_font_italic_black,null));
            itemNotPressedDrawableList.add(getResources().getDrawable(R.drawable.ic_font_underline_black,null));

            mFontToolsDialog = new ButtonDialog(this, DialogModeEnum.MANY)
                    .setDialogSize(dialogWidth, dialogHeight)
                    .setItemButtonSize(itemWidth , itemHeight)
                    .setItemPressedDrawableContent(itemPressedDrawableList, itemNotPressedDrawableList)
                    .setClickItemDismiss(false)
                    .setMarginY(-10)
                    .setMarginX(mDisplayMetrics.widthPixels/8)
                    .setItemBgColor(getResources().getColor(R.color.colorWhite), getResources().getColor(R.color.colorOrange1))
                    .setOutsideTouchable(true)
                    .setButtonDialogManyListener(new ButtonDialogManyListener() {
                        @Override
                        public void onShow() {
                            mBtnFontTools.setImageDrawable(getResources().getDrawable(R.drawable.ic_font_tools_white, null));
                            mBtnFontTools.setBackground(getResources().getDrawable(R.drawable.bg_orange_corners_24dp, null));

                        }

                        @Override
                        public void onCurrentItemClick(int position) {
                            switch (position){
                                case 0:
                                    mRichEditor.setBold();//粗体
                                    break;

                                case 1:
                                    mRichEditor.setItalic();//斜体
                                    break;

                                case 2:
                                    mRichEditor.setUnderline();//下划线
                                    break;

                                default:
                                    break;
                            }

                        }

                        @Override
                        public void onSelectItem(Set<Integer> selecetPositionSet) {


                        }

                        @Override
                        public void onDismiss() {
                            mBtnFontTools.setImageDrawable(getResources().getDrawable(R.drawable.ic_font_tools_black, null));
                            mBtnFontTools.setBackgroundColor(getResources().getColor(R.color.colorWhite));

                        }

                        @Override
                        public void onError(String e) {
                            L.e("异常="+e);

                        }
                    })
                    .build();
        }

        Set<Integer> selecetItemset = new HashSet<>();
        selecetItemset.clear();
        for (RichEditor.Type type : mRichEditorTypeList){
            switch (type){
                case BOLD:
                    selecetItemset.add(0);
                    break;

                case ITALIC:
                    selecetItemset.add(1);
                    break;

                case UNDERLINE:
                    selecetItemset.add(2);
                    break;

                default:
                    break;
            }

        }
        mFontToolsDialog.manySelectiontShow(mRichTextToolkit, DialogDirectionEnum.TOP, selecetItemset);

    }
