package net.mf99.d3viewer.data.unit;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import net.mf99.d3viewer.Const.HERO_CLASS;

public class Hero {
	public String mName;
	public long mId;
	public int mLevel;
	public HERO_CLASS mClass;
	public boolean isMale;
	public EquipList mEquips;
	public ArrayList<Skill> mActiveSkills;
	public ArrayList<Skill> mPassiveSkills;	
	public Stats mStats;
	
	public Hero(String mName, long mId, int mLevel, HERO_CLASS mClass,
			boolean isMale, EquipList mEquips,
			ArrayList<Skill> mActiveSkills, ArrayList<Skill> mPassiveSkills,
			Hero.Stats mStats) {
		
		this.mName = mName;
		this.mId = mId;
		this.mLevel = mLevel;
		this.mClass = mClass;
		this.isMale = isMale;
		this.mEquips = mEquips;
		this.mActiveSkills = mActiveSkills;
		this.mPassiveSkills = mPassiveSkills;
		this.mStats = mStats;
	}
	
	public static class Stats{
		public int mLevel;
		public int mProgression;
		public long mLife, mArmor;
		public double mDamage, mAttackSpeed;
		
		public int mStr, mDex, mVit, mInt;
		
		public int mResistPhysical, mResistFire, mResistCold, mResistLightning, mResistPoison, mResistArcane;
		
		public double mCritDamage, mCritChance;
		
		public double mBlockChance, mBlockMin, mBlockMax, mThorns;// ¯ð´Æ¶Ë®`
		
		public double mDamageIncrease, mDamageReduction;
		
		public double mLifeSteal, mLifePerKill, mLifeOnHit;
		
		public double mGoldFind, mMagicFind;
		
		public int mSourcePrimary, mSourceSecandary;
		
		public Stats(JSONObject data, int level, int progression){
			mLevel = level;
			mProgression = progression;
			
			try {
				this.mLife = data.getLong("life");
				this.mArmor = data.getLong("armor");
				
				this.mDamage = data.getDouble("damage");
				this.mAttackSpeed = Math.floor(data.getDouble("attackSpeed") * 100) / 100;				
				
				this.mStr = data.getInt("strength");
				this.mDex = data.getInt("dexterity");
				this.mVit = data.getInt("vitality");
				this.mInt  = data.getInt("intelligence");

				this.mResistPhysical  = data.getInt("physicalResist");
				this.mResistFire  = data.getInt("fireResist");
				this.mResistCold  = data.getInt("coldResist");
				this.mResistLightning  = data.getInt("lightningResist");
				this.mResistPoison  = data.getInt("poisonResist");
				this.mResistArcane  = data.getInt("arcaneResist");
				
				
				this.mCritDamage = (Math.floor(data.getDouble("critDamage") * 100) / 100) - 1;
				this.mCritChance = data.getDouble("critChance");
				
				this.mBlockChance = data.getDouble("blockChance");
				this.mBlockMin = data.getDouble("blockAmountMin");
				this.mBlockMax = data.getDouble("blockAmountMax");
				
				this.mDamageIncrease = data.getDouble("damageIncrease");
				this.mDamageReduction = data.getDouble("damageReduction");
				
				this.mThorns = data.getDouble("thorns");
				this.mLifeSteal = data.getDouble("lifeSteal");
				this.mLifePerKill = data.getDouble("lifePerKill");
				this.mLifeOnHit = data.getDouble("lifeOnHit");
				
				this.mGoldFind = data.getDouble("goldFind");
				this.mMagicFind = data.getDouble("magicFind");
				
				this.mSourcePrimary  = data.getInt("primaryResource");
				this.mSourceSecandary  = data.getInt("secondaryResource");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
