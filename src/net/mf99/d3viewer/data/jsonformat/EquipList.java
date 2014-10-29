package net.mf99.d3viewer.data.jsonformat;

import org.json.JSONObject;

public class EquipList extends JsonObjectBase{	

	private EquipShort mHead,
					  mTorso, // Body
					  mFeet,
					  mHands,
					  mShoulders,
					  mLegs,
					  mBracers, //Arms
					  mMainHand,
					  mOffHand,
					  mWaist,
					  mRightFinger,
					  mLeftFinger,
					  mNeck;
	
	public EquipList(JSONObject jsonData) {
		super(jsonData);
	}
}
