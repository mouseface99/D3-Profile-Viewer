package net.mf99.d3viewer.data.jsonformat;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.GEM_CLASS;

public class Gem extends JsonObjectBase {
	private GEM_CLASS mClass;
	private String mName;
	private int mLevel;
	private String mAttribute;
	
	public Gem(JSONObject jsonData) {
		super(jsonData);
	}
}
