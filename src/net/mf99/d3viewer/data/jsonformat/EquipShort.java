package net.mf99.d3viewer.data.jsonformat;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.ITEM_COLOR;

public class EquipShort extends JsonObjectBase{
	private String mName;
	private String mIcon;
	private ITEM_COLOR mColor;
	private String mToolkitParam;	
	
	public EquipShort(JSONObject jsonData) {
		super(jsonData);
	}
}
