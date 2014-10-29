package net.mf99.d3viewer.data.jsonformat;

import java.util.ArrayList;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.HERO_CLASS;

public class Hero extends JsonObjectBase {
	private String mName;
	private long mId;
	private int mLevel;
	private HERO_CLASS mClass;
	private boolean isMale;
	private EquipList mEquips;
	private ArrayList<Skill> mActiveSkills;
	private ArrayList<Skill> mPassiveSkills;	
	private Stats mStats;
	
	public Hero(JSONObject jsonData) {
		super(jsonData);
	}
	
    public static class Stats extends JsonObjectBase{
        private int mLevel;
        private int mProgression;
        private long mLife, mArmor;
        private double mDamage, mAttackSpeed;
        
        private int mStr, mDex, mVit, mInt;
        
        private int mResistPhysical, mResistFire, mResistCold, mResistLightning, mResistPoison, mResistArcane;
        
        private double mCritDamage, mCritChance;
        
        private double mBlockChance, mBlockMin, mBlockMax, mThorns;
        
        private double mDamageIncrease, mDamageReduction;
        
        private double mLifeSteal, mLifePerKill, mLifeOnHit;
        
        private double mGoldFind, mMagicFind;
        
        private int mSourcePrimary, mSourceSecandary;
        
        public Stats(JSONObject jsonData) {
    		super(jsonData);
    	}
    }

}
