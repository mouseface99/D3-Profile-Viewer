package net.mf99.d3viewer.data.jsonformat;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.ITEM_COLOR;
import net.mf99.d3viewer.Utils;

public class EquipShort extends JsonObjectBase{
	private String name;
	private String icon;
	private String displayColor;
	private String tooltipParams;
	private ITEM_COLOR mColor;
	
	public EquipShort(JSONObject jsonData) {
		super(jsonData);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDisplayColor() {
		return displayColor;
	}

	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}

	public String getTooltipParams() {
		return tooltipParams;
	}

	public void setTooltipParams(String tooltipParams) {
		this.tooltipParams = tooltipParams;
	}

	public ITEM_COLOR getItemColor() {
		if(mColor == null)
			mColor = Utils.getItemColor(displayColor);
		return mColor;
	}
}
