package net.mf99.d3viewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import net.mf99.d3viewer.Const.HERO_CLASS;
import net.mf99.d3viewer.Const.HeroKeys;
import net.mf99.d3viewer.Const.ITEM_COLOR;
import net.mf99.d3viewer.Const.ProfileKeys;
import net.mf99.d3viewer.Const.SERVER_REGION;
import net.mf99.d3viewer.Const.ServerPath;
import net.mf99.d3viewer.data.unit.EquipList;
import net.mf99.d3viewer.data.unit.EquipShort;
import net.mf99.d3viewer.data.unit.Gem;
import net.mf99.d3viewer.data.unit.Hero;
import net.mf99.d3viewer.data.unit.HeroShort;
import net.mf99.d3viewer.data.unit.Profile;
import net.mf99.d3viewer.data.unit.Skill;

public class Utils {
	
	public static int getBackgroundColorResource(ITEM_COLOR color){
		switch(color){
			case GRAY:
				return R.drawable.bg_equip_gray;
			case WHITE:
				return R.drawable.bg_equip_white;
			case BLUE:
				return R.drawable.bg_equip_blue;
			case YELLOW:
				return R.drawable.bg_equip_yellow;
			case ORANGE:
				return R.drawable.bg_equip_orange;
			case GREEN:
				return R.drawable.bg_equip_green;
		}
		return R.drawable.bg_equip_white;
	}
	
	public static ITEM_COLOR getItemColor(String color){
		if("gray".equals(color)) return ITEM_COLOR.GRAY;
		else if("white".equals(color)) return ITEM_COLOR.WHITE;
		else if("blue".equals(color)) return ITEM_COLOR.BLUE;
		else if("yellow".equals(color)) return ITEM_COLOR.YELLOW;
		else if("orange".equals(color)) return ITEM_COLOR.ORANGE;
		else if("green".equals(color)) return ITEM_COLOR.GREEN;
		
		return ITEM_COLOR.WHITE;
	}
	
	public static HERO_CLASS getHeroClass(int numClass){
		switch(numClass){
			case 0: return HERO_CLASS.HUNTER;
			case 1: return HERO_CLASS.MONK;
			case 2: return HERO_CLASS.DOCTOR;
			case 3: return HERO_CLASS.BARBARIAN;
			case 4: return HERO_CLASS.WIZARD;
			case 5: return HERO_CLASS.CRUSADER;
		}
		
		return HERO_CLASS.HUNTER;
	}
	
	public static HERO_CLASS getHeroClass(String strClass){
		if("demon-hunter".equals(strClass))
			return HERO_CLASS.HUNTER;
		else if("monk".equals(strClass))
			return HERO_CLASS.MONK;
		else if("crusader".equals(strClass))
			return HERO_CLASS.CRUSADER;
		else if("witch-doctor".equals(strClass))
			return HERO_CLASS.DOCTOR;
		else if("barbarian".equals(strClass))
			return HERO_CLASS.BARBARIAN;
		else if("wizard".equals(strClass))
			return HERO_CLASS.WIZARD;
		
		return HERO_CLASS.HUNTER;
	}
	
	public static int getHeroClassCode(HERO_CLASS enumClass){
		switch(enumClass){
			case HUNTER: 	return 0;
			case MONK: 		return 1;
			case DOCTOR: 	return 2;
			case BARBARIAN: return 3;
			case WIZARD: 	return 4;
			case CRUSADER: 	return 5;
		}
		
		return 0;
	}
	
	public static int getRuneLevel(String lv){
		if("a".equals(lv)) return 1;
		else if("b".equals(lv)) return 2;
		else if("c".equals(lv)) return 3;
		else if("d".equals(lv)) return 4;
		else if("e".equals(lv)) return 5;
		
		return 0;
	}
	
	public static int getHeroHeaderSource(HERO_CLASS mClass, boolean isMale){
		switch(mClass){
			case HUNTER:
				return isMale ? R.drawable.hero_hunter_male : R.drawable.hero_hunter_female;
			case MONK:
				return isMale ? R.drawable.hero_monk_male : R.drawable.hero_monk_female;
			case DOCTOR:
				return isMale ? R.drawable.hero_doctor_male : R.drawable.hero_doctor_female;
			case BARBARIAN:
				return isMale ? R.drawable.hero_barbarian_male : R.drawable.hero_barbarian_female;
			case WIZARD:
				return isMale ? R.drawable.hero_wizard_male : R.drawable.hero_wizard_female;
			case CRUSADER:
				return isMale ? R.drawable.hero_crusader_male : R.drawable.hero_crusader_female;
		}
		
		return R.drawable.hero_hunter_female;
	}
	
	public static int getHeroClassNameSource(HERO_CLASS mClass){
		switch(mClass){
			case HUNTER:
				return R.string.hero_type_hunter;
			case MONK:
				return R.string.hero_type_monk;
			case DOCTOR:
				return R.string.hero_type_doctor;
			case BARBARIAN:
				return R.string.hero_type_barbarian;
			case WIZARD:
				return R.string.hero_type_wizard;
			case CRUSADER:
				return R.string.hero_type_crusader;
		}
		return 0;
	}
	
	public static String getRegionStr(SERVER_REGION region){
		switch(region){
			case TW: return "tw";
			case US: return "us";
			case EU: return "en";
			case SEA: return "sea";
			case KR: return "kr";
		}
		
		return "us";
	}
	
	public static SERVER_REGION getRegion(String region){
		region = region.toLowerCase();
		
		if("tw".equals(region))
			return SERVER_REGION.TW;
		else if("us".equals(region))
			return SERVER_REGION.US;
		else if("eu".equals(region))
			return SERVER_REGION.EU;
		else if("kr".equals(region))
			return SERVER_REGION.KR;
		else if("sea".equals(region))
			return SERVER_REGION.SEA;
		
		
		return SERVER_REGION.TW;
	}
	
	public static Profile translateJsonToProfile(String jsonData, SERVER_REGION region) throws JSONException{
		JSONObject data = new JSONObject(jsonData);
		JSONArray jsonHeros = data.getJSONArray(ProfileKeys.KEY_HEROS);
		JSONObject kills = data.getJSONObject(ProfileKeys.KEY_KILLS);
		
		String battleTag;
		ArrayList<HeroShort> heros = new ArrayList<HeroShort>(jsonHeros.length());
		int paragonLv;
		long killed, elite_killed;
		
		battleTag = data.getString(ProfileKeys.KEY_TAG);
		paragonLv = data.getInt(ProfileKeys.KEY_PLV);
		killed = kills.getLong(ProfileKeys.KEY_KILLS_MONSTER);
		elite_killed = kills.getLong(ProfileKeys.KEY_KILLS_ELITE);
		
		String name;
		long id;
		int level;
		HERO_CLASS hClass;
		boolean isMale;
		
		for(int i=0; i<jsonHeros.length(); i++){
			JSONObject hero = jsonHeros.getJSONObject(i);
			
			name = hero.getString(ProfileKeys.KEY_NAME);
			id = hero.getLong(ProfileKeys.KEY_ID);
			level = hero.getInt(ProfileKeys.KEY_LV);
			hClass = Utils.getHeroClass(hero.getString(ProfileKeys.KEY_CLASS));
			isMale = (hero.getInt(ProfileKeys.KEY_GENDER) == 0 );
			
			
			heros.add(new HeroShort(name, id, level, hClass, isMale));
		}
		
		return new Profile(battleTag, region, heros, paragonLv, killed, elite_killed);
	}
	
	public static Hero translateJsonToHero(String jsonData) throws JSONException{
		JSONObject data = new JSONObject(jsonData);
		
		String mName = data.getString(HeroKeys.KEY_NAME);
		long mId = data.getLong(HeroKeys.KEY_ID);
		int mLevel = data.getInt(HeroKeys.KEY_LV);
		HERO_CLASS mClass = Utils.getHeroClass(data.getString(HeroKeys.KEY_CLASS));
		boolean isMale = (data.getInt(HeroKeys.KEY_GENDER) == 0 );
		int mProgression = getProgression(data.getJSONObject(HeroKeys.KEY_PROG));
		
		ArrayList<Skill> mActiveSkills = new ArrayList<Skill>(6);
		ArrayList<Skill> mPassiveSkills = new ArrayList<Skill>(4);
		
		JSONArray activeSkills = data.getJSONObject("skills").getJSONArray("active");
		for(int i=0; i<activeSkills.length(); i++){
			try{
				mActiveSkills.add(toSkill(activeSkills.getJSONObject(i), true));
			} catch(JSONException ex){}
		}
		
		JSONArray passiveSkills = data.getJSONObject("skills").getJSONArray("passive");
		for(int i=0; i<passiveSkills.length(); i++){
			try{
				mPassiveSkills.add(toSkill(passiveSkills.getJSONObject(i), false));
			} catch(JSONException ex){}
		}
			
		
		EquipList mEquips = new EquipList();
		JSONObject items = data.getJSONObject("items");
		
		mEquips.mHead = toEquip(items, "head");
		mEquips.mTorso = toEquip(items, "torso");
		mEquips.mWaist = toEquip(items, "waist");
		mEquips.mLegs = toEquip(items, "legs");
		mEquips.mFeet = toEquip(items, "feet");
		mEquips.mShoulders = toEquip(items, "shoulders");
		mEquips.mHands = toEquip(items, "hands");
		mEquips.mNeck = toEquip(items, "neck");
		mEquips.mBracers = toEquip(items, "bracers");
		mEquips.mMainHand = toEquip(items, "mainHand");
		mEquips.mOffHand = toEquip(items, "offHand");
		mEquips.mRightFinger = toEquip(items, "rightFinger");
		mEquips.mLeftFinger = toEquip(items, "leftFinger");
		
		return new Hero(mName, mId, mLevel, mClass, isMale, mProgression, mEquips, mActiveSkills, mPassiveSkills);
	}
	
	private static int getProgression(JSONObject progData) throws JSONException{
		int prog = 5;
		for(; prog>0; prog--)
			if(progData.getJSONObject("act" + prog).getBoolean("completed"))
				break;
		
		return prog;
	}
	
	private static Skill toSkill(JSONObject skillData, boolean isActive) throws JSONException{
		if(skillData == null) return null;
		
		JSONObject skill = skillData.getJSONObject("skill");
		
		String mName = skill.getString("name");;
		String mIcon = skill.getString("icon");
		String mDescription = skill.getString("description");
		int mLevel = skill.getInt("level");
		
		boolean mHasRune = false;
		
		String mRuneName = null;
		String mRuneDescription = null;
		int mRuneType=0, mRuneLevel = 0;
		
		if(isActive){
			JSONObject rune = skillData.getJSONObject("rune");
			
			mHasRune = (rune != null);
			
			if(mHasRune){
				mRuneName = rune.getString("name");
				mRuneDescription = rune.getString("description");
				mRuneType = getRuneLevel(rune.getString("type"));
				mRuneLevel = rune.getInt("level");
			}
		}
		return new Skill(mName, mIcon, mDescription, mLevel, mHasRune,
						 mRuneName, mRuneDescription, mRuneType, mRuneLevel);		
	}
	
	private static EquipShort toEquip(JSONObject itemsData, String pos){		
		if(itemsData == null) return null;
		try{
			JSONObject equipData = itemsData.getJSONObject(pos);
			String mName = equipData.getString("name");
			String mIcon = equipData.getString("icon");
			ITEM_COLOR mColor = getItemColor(equipData.getString("displayColor"));
			String mToolkitParam = equipData.getString("tooltipParams");
			
			return new EquipShort(mName, mIcon, mColor, mToolkitParam);
		} catch(JSONException ex){}
		
		return null;
		
	}
	
	public static String downloadJSONData(String url) throws ClientProtocolException, IOException{		
		StringBuffer string = new StringBuffer();
		
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);

        String linha = "";
        HttpResponse response = client.execute(get);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();

        if (statusCode == 200) { // OK
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            while ((linha = rd.readLine()) != null) {
            	string.append(linha);
            }
            return string.toString();
        }
		
		return null;
	}

}
