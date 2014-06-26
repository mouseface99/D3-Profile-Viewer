package net.mf99.d3viewer.data.unit;

import java.util.ArrayList;

import net.mf99.d3viewer.Const.HERO_CLASS;

public class Hero {
	public String mName;
	public long mId;
	public int mLevel;
	public HERO_CLASS mClass;
	public boolean isMale;
	public int mProgression;
	public EquipList mEquips;
	public ArrayList<Skill> mActiveSkills;
	public ArrayList<Skill> mPassiveSkills;
	
	public boolean isTemp;
	
	public Hero(String mName, long mId, int mLevel, HERO_CLASS mClass,
			boolean isMale, int mProgression, EquipList mEquips,
			ArrayList<Skill> mActiveSkills, ArrayList<Skill> mPassiveSkills) {
		
		this.mName = mName;
		this.mId = mId;
		this.mLevel = mLevel;
		this.mClass = mClass;
		this.isMale = isMale;
		this.mProgression = mProgression;
		this.mEquips = mEquips;
		this.mActiveSkills = mActiveSkills;
		this.mPassiveSkills = mPassiveSkills;
		
		isTemp = false;
	}
	
	public Hero(String mName, long mId, int mLevel, HERO_CLASS mClass, boolean isMale) {
		
		this.mName = mName;
		this.mId = mId;
		this.mLevel = mLevel;
		this.mClass = mClass;
		this.isMale = isMale;
		
		isTemp = true;
	}
}
