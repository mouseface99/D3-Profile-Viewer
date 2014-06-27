package net.mf99.d3viewer.data.unit;

import net.mf99.d3viewer.Const.HERO_CLASS;

public class HeroShort {
	public String mName;
	public long mId;
	public int mLevel;
	public HERO_CLASS mClass;
	public boolean isMale;
	
	public HeroShort(String mName, long mId, int mLevel, HERO_CLASS mClass, boolean isMale) {
		this.mName = mName;
		this.mId = mId;
		this.mLevel = mLevel;
		this.mClass = mClass;
		this.isMale = isMale;
	}
}
