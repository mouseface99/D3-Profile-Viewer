package net.mf99.d3viewer.data.jsonformat;

import org.json.JSONObject;

public class Skill extends JsonObjectBase {	
	private String mName;
	private String mIcon;
	private String mDescription;
	private int mLevel;
	private boolean mHasRune;
	private String mRuneName;
	private String mRuneDescription;
	private int mRuneType;
	private int mRuneLevel;
	
	public Skill(JSONObject jsonData) {
		super(jsonData);
	}
}
