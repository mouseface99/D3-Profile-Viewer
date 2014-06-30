package net.mf99.d3viewer.data.unit;

public class Skill {
	public String mName;
	public String mIcon;
	public String mDescription;
	public int mLevel;
	public boolean mHasRune;
	public String mRuneName;
	public String mRuneDescription;
	public int mRuneType;
	public int mRuneLevel;
	
	public Skill(String mName, String mIcon, String mDescription, int mLevel,
			boolean mHasRune, String mRuneName, String mRuneDescription,
			int mRuneType, int mRuneLevel) {
		
		this.mName = mName;
		this.mIcon = mIcon;
		this.mDescription = mDescription;
		this.mLevel = mLevel;
		this.mHasRune = mHasRune;
		this.mRuneName = mRuneName;
		this.mRuneDescription = mRuneDescription;
		this.mRuneType = mRuneType;
		this.mRuneLevel = mRuneLevel;
	}	
}
