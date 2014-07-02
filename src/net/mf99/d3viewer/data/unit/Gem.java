package net.mf99.d3viewer.data.unit;

import net.mf99.d3viewer.Const.GEM_CLASS;

public class Gem {
	public GEM_CLASS mClass;
	public String mName;
	public int mLevel;
	public String mAttribute;
	
	public Gem(String mName, GEM_CLASS mClass, int mLevel, String mAttribute) {		
		this.mClass = mClass;
		this.mName = mName;
		this.mLevel = mLevel;
		this.mAttribute = mAttribute;
	}
	
}
