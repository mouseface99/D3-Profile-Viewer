package net.mf99.d3viewer.data.unit;

import java.util.ArrayList;

import net.mf99.d3viewer.Const.ITEM_COLOR;

public class Equip {	
	public String mName;
	public String mIcon;
	public ITEM_COLOR mColor;
	public int mItemLevel, mSocketNum;
	public double mDps, mAttackPerSec; 
	public int mMaxDamage, mMinDamage;
	public double mArmor;
	
	public ArrayList<String> mPrimaryAttr, mSecandaryAttr, mPassiveAttr;
	public ArrayList<Gem> mGems;
	
	public final boolean isWepond;
	
	public Equip(String mName, String mIcon, ITEM_COLOR mColor, 
				 int mItemLevel, int mSocketNum, 
				 double mDps, double mAttackPerSec,
				 double mMaxDamage, double mMinDamage,
				 ArrayList<String> mPrimaryAttr, ArrayList<String> mSecandaryAttr, ArrayList<String> mPassiveAttr,
				 ArrayList<Gem> mGems) {
		
		this.mName = mName;
		this.mIcon = mIcon;
		this.mColor = mColor;
		this.mItemLevel = mItemLevel;
		this.mSocketNum = mSocketNum;
		this.mDps = Math.floor(mDps * 100) / 100;
		this.mAttackPerSec = Math.floor(mAttackPerSec * 100) / 100;
		this.mMaxDamage = (int) Math.round(mMaxDamage);
		this.mMinDamage = (int) Math.round(mMinDamage);
		this.mPrimaryAttr = mPrimaryAttr;
		this.mSecandaryAttr = mSecandaryAttr;
		this.mPassiveAttr = mPassiveAttr;
		this.mGems = mGems;
		
		this.isWepond = true;
	}

	public Equip(String mName, String mIcon, ITEM_COLOR mColor, 
				int mItemLevel, int mSocketNum, 
				double mArmor, 
				ArrayList<String> mPrimaryAttr, ArrayList<String> mSecandaryAttr, ArrayList<String> mPassiveAttr,
				ArrayList<Gem> mGems) {
		
		this.mName = mName;
		this.mIcon = mIcon;
		this.mColor = mColor;
		this.mItemLevel = mItemLevel;
		this.mSocketNum = mSocketNum;
		this.mArmor = Math.floor(mArmor * 100) / 100;
		this.mPrimaryAttr = mPrimaryAttr;
		this.mSecandaryAttr = mSecandaryAttr;
		this.mPassiveAttr = mPassiveAttr;
		this.mGems = mGems;
		
		this.isWepond = false;
	}	
}
