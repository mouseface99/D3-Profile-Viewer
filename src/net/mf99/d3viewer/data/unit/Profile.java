package net.mf99.d3viewer.data.unit;

import java.util.ArrayList;

import net.mf99.d3viewer.Const.SERVER_REGION;

public class Profile {
	public String mBattleTag;
	public SERVER_REGION mServerRegion;
	public ArrayList<HeroShort> mHeros;
	public int mParagonLevel;
	public long mKilled, mEliteKilled;
	
	public Profile(String mBattleTag, SERVER_REGION mServerRegion,
			ArrayList<HeroShort> mHeros, int mParagonLevel, long mKilled,
			long mEliteKilled) {
		super();
		this.mBattleTag = mBattleTag;
		this.mServerRegion = mServerRegion;
		this.mHeros = mHeros;
		this.mParagonLevel = mParagonLevel;
		this.mKilled = mKilled;
		this.mEliteKilled = mEliteKilled;
	}
	
	
	
}
