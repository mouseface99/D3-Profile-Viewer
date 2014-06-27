package net.mf99.d3viewer.data.unit;

import net.mf99.d3viewer.Const.ITEM_COLOR;

public class EquipShort {
	public String mName;
	public String mIcon;
	public ITEM_COLOR mColor;
	public String mToolkitParam;	
	
	public EquipShort(String mName, String mIcon, ITEM_COLOR mColor, String mToolkitParam) {
		this.mName = mName;
		this.mIcon = mIcon;
		this.mColor = mColor;
		this.mToolkitParam = mToolkitParam;
	}
}
