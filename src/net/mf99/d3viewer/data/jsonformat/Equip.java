package net.mf99.d3viewer.data.jsonformat;

import java.util.ArrayList;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.ITEM_COLOR;

public class Equip extends JsonObjectBase{	
	private String mName;
	private String mIcon;
	private ITEM_COLOR mColor;
	private int mItemLevel, mSocketNum;
	private double mDps, mAttackPerSec; 
	private int mMaxDamage, mMinDamage;
	private double mArmor;
	private double mBlockChance, mBlockMax, mBlockMin;
	
	private ArrayList<String> mPrimaryAttr, mSecandaryAttr, mPassiveAttr;
	private ArrayList<Gem> mGems;
	
	private final boolean isWepond, isShield;
	
	public Equip(JSONObject jsonData) {
		super(jsonData);
		isWepond = false;
		isShield = false;
	}
}
