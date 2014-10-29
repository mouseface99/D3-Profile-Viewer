package net.mf99.d3viewer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import net.mf99.d3viewer.Const.GEM_CLASS;
import net.mf99.d3viewer.Const.HERO_CLASS;
import net.mf99.d3viewer.Const.ITEM_COLOR;
import net.mf99.d3viewer.Const.SERVER_REGION;
import net.mf99.d3viewer.data.unit.Equip;
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
	
	public static int getRuneResource(int level){
		switch(level){
			case 0: return R.drawable.skill_rune_none;
			case 1: return R.drawable.skill_rune1;
			case 2: return R.drawable.skill_rune2;
			case 3: return R.drawable.skill_rune3;
			case 4: return R.drawable.skill_rune4;
			case 5: return R.drawable.skill_rune5;
		}
		
		return R.drawable.skill_rune_none;
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
	
	public static int getGemIconResource(Gem gem){
		int base;
		switch(gem.mClass){
			case AMETHYST:
				base = R.drawable.gem_amethyst_01;
				break;
			case DIAMOND:
				base = R.drawable.gem_diamond_01;
				break;
			case EMERALD:
				base = R.drawable.gem_emerald_01;
				break;
			case RUBY:
				base = R.drawable.gem_ruby_01;
				break;
			case TOPAZ:
				base = R.drawable.gem_topaz_01;
				break;
			default :
				base = R.drawable.gem_diamond_01;
		}
		
		return (base+(gem.mLevel-1));
	}
	
	private static int getGemLevel(String id){
		if(id != null && id.contains("_")){
			return Integer.parseInt(id.substring(id.indexOf("_")+1, id.length()));
		}
		
		return -1;		
	}
	
	private static GEM_CLASS getGemClass(String id){
		String type = id.substring(0, id.indexOf("_"));
		
		if("Amethyst".equals(type))
			return GEM_CLASS.AMETHYST;
		else if("Diamond".equals(type))
			return GEM_CLASS.DIAMOND;
		else if("Emerald".equals(type))
			return GEM_CLASS.EMERALD;
		else if("Ruby".equals(type))
			return GEM_CLASS.RUBY;
		else if("Topaz".equals(type))
			return GEM_CLASS.TOPAZ;
		
		return null;
	}
	
	public static Profile translateJsonToProfile(String jsonData, SERVER_REGION region) throws JSONException{
		JSONObject data = new JSONObject(jsonData);
		JSONArray jsonHeros = data.getJSONArray("heroes");
		JSONObject kills = data.getJSONObject("kills");
		
		String battleTag;
		ArrayList<HeroShort> heros = new ArrayList<HeroShort>(jsonHeros.length());
		int paragonLv;
		long killed, elite_killed;
		
		battleTag = data.getString("battleTag");
		paragonLv = data.getInt("paragonLevel");
		Const.PARAGON_LEVEL = paragonLv;
		killed = kills.getLong("monsters");
		elite_killed = kills.getLong("elites");
		
		String name;
		long id;
		int level;
		HERO_CLASS hClass;
		boolean isMale;
		
		for(int i=0; i<jsonHeros.length(); i++){
			JSONObject hero = jsonHeros.getJSONObject(i);
			
			name = hero.getString("name");
			id = hero.getLong("id");
			level = hero.getInt("level");
			hClass = Utils.getHeroClass(hero.getString("class"));
			isMale = (hero.getInt("gender") == 0 );
			
			
			heros.add(new HeroShort(name, id, level, hClass, isMale));
		}
		
		return new Profile(battleTag, region, heros, paragonLv, killed, elite_killed);
	}
	
	public static Hero translateJsonToHero(String jsonData) throws JSONException{
		JSONObject data = new JSONObject(jsonData);
		
		String mName = data.getString("name");
		long mId = data.getLong("id");
		int mLevel = data.getInt("level");
		HERO_CLASS mClass = Utils.getHeroClass(data.getString("class"));
		boolean isMale = (data.getInt("gender") == 0 );
		
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
		
		mEquips.mHead = toEquipShort(items, "head");
		mEquips.mTorso = toEquipShort(items, "torso");
		mEquips.mWaist = toEquipShort(items, "waist");
		mEquips.mLegs = toEquipShort(items, "legs");
		mEquips.mFeet = toEquipShort(items, "feet");
		mEquips.mShoulders = toEquipShort(items, "shoulders");
		mEquips.mHands = toEquipShort(items, "hands");
		mEquips.mNeck = toEquipShort(items, "neck");
		mEquips.mBracers = toEquipShort(items, "bracers");
		mEquips.mMainHand = toEquipShort(items, "mainHand");
		mEquips.mOffHand = toEquipShort(items, "offHand");
		mEquips.mRightFinger = toEquipShort(items, "rightFinger");
		mEquips.mLeftFinger = toEquipShort(items, "leftFinger");
		
		return new Hero(mName, mId, mLevel, mClass, isMale, mEquips, mActiveSkills, mPassiveSkills,
						new Hero.Stats(data.getJSONObject("stats"), mLevel, getProgression(data.getJSONObject("progression"))));
	}
	
	public static Equip translateJsonToEquip(String jsonData) throws JSONException{
		JSONObject data = new JSONObject(jsonData);
		
		String mName = data.getString("name");
		String mIcon = data.getString("icon");
		ITEM_COLOR mColor = Utils.getItemColor(data.getString("displayColor"));
		int mItemLevel = data.getInt("itemLevel");
		int mSocketNum = 0;
		
		try{
			mSocketNum = data.getJSONObject("attributesRaw").getJSONObject("Sockets").getInt("max");
		}catch(JSONException ex){}
		
		boolean isWepond = false;
		try{
			data.getJSONObject("dps");
			isWepond = true;
		}catch(JSONException ex){}
		
		boolean isShield = false;
		try{
			data.getJSONObject("blockChance");
			isShield = true;
		}catch(JSONException ex){}
		
		double mDps = 0, mAttackPerSec = 0, mMaxDamage = 0, mMinDamage = 0;
		double mArmor = 0;
		double mBlockChance = 0, mBlockMin = 0, mBlockMax = 0;
		if(isWepond){
			mDps = data.getJSONObject("dps").getDouble("max");
			mAttackPerSec = data.getJSONObject("attacksPerSecond").getDouble("max");
			mMaxDamage = data.getJSONObject("maxDamage").getDouble("max");
			mMinDamage = data.getJSONObject("minDamage").getDouble("max");
		}
		else{
			try{
				mArmor = data.getJSONObject("armor").getDouble("max");
			}catch(JSONException ex){ mArmor = -1; }
			if(isShield){
				JSONObject rawData = data.getJSONObject("attributesRaw");
				
				mBlockChance = data.getJSONObject("blockChance").getDouble("max");
				mBlockMin = rawData.getJSONObject("Block_Amount_Item_Min").getDouble("max");
				mBlockMax = mBlockMin + rawData.getJSONObject("Block_Amount_Item_Delta").getDouble("max");
			}
		}
		
		ArrayList<String> mPrimaryAttr = new ArrayList<String>();
		ArrayList<String> mSecandaryAttr = new ArrayList<String>();
		ArrayList<String> mPassiveAttr = new ArrayList<String>();
		ArrayList<Gem> mGems = new ArrayList<Gem>();
		
		try{
			JSONObject attrs = data.getJSONObject("attributes");
			
			JSONArray primary = attrs.getJSONArray("primary");
			
			for(int i=0; i<primary.length(); i++)
				mPrimaryAttr.add(primary.getJSONObject(i).getString("text"));
			
			JSONArray secondary = attrs.getJSONArray("secondary");
			
			for(int i=0; i<secondary.length(); i++)
				mSecandaryAttr.add(secondary.getJSONObject(i).getString("text"));
			
			JSONArray passive = attrs.getJSONArray("passive");
			
			for(int i=0; i<passive.length(); i++)
				mPassiveAttr.add(passive.getJSONObject(i).getString("text"));
			
			JSONArray gems = data.getJSONArray("gems");			
			
			for(int i=0; i<gems.length(); i++){
				JSONObject gem = gems.getJSONObject(i);
				String name = gem.getJSONObject("item").getString("name");
				String id = gem.getJSONObject("item").getString("id");
				GEM_CLASS mClass = getGemClass(id);
				int level = getGemLevel(id);
				String attr = gem.getJSONObject("attributes").getJSONArray("primary").getJSONObject(0).getString("text");				
				mGems.add(new Gem(name, mClass, level, attr));
			}			
		}catch(JSONException ex){}
		
		// Adjust Max-Min damage with attribute
		if(isWepond){
			if(mPrimaryAttr.size() > 0 &&
			   mPrimaryAttr.get(0).contains("+") && 
			   mPrimaryAttr.get(0).contains("¡V")){				
				String dd = mPrimaryAttr.get(0).substring(1, mPrimaryAttr.get(0).indexOf(" "));
				
				mMinDamage += Integer.parseInt(dd.substring(0, dd.indexOf("¡V")));
				mMaxDamage += Integer.parseInt(dd.substring(dd.indexOf("¡V")+1, dd.length()));				
			}
		}
		
		
		if(isWepond)
			return new Equip(mName, mIcon, mColor, 
							 mItemLevel, mSocketNum, 
							 mDps, mAttackPerSec, mMaxDamage, mMinDamage, 
							 mPrimaryAttr, mSecandaryAttr, mPassiveAttr, mGems);
		else if(isShield)
			return new Equip(mName, mIcon, mColor, 
					 		 mItemLevel, mSocketNum, 
					 		 mArmor, 
					 		 mPrimaryAttr, mSecandaryAttr, mPassiveAttr, mGems,
					 		 mBlockChance, mBlockMin, mBlockMax);
		else
			return new Equip(mName, mIcon, mColor, 
			 		 mItemLevel, mSocketNum, 
			 		 mArmor, 
			 		 mPrimaryAttr, mSecandaryAttr, mPassiveAttr, mGems);
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
	
	private static EquipShort toEquipShort(JSONObject itemsData, String pos){		
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
		URL u = new URL(url);
        HttpURLConnection c = (HttpURLConnection) u.openConnection();
        c.setRequestMethod("GET");
        c.setUseCaches(false);
        c.setAllowUserInteraction(false);
        c.setConnectTimeout(Const.NETWORK_TIMEOUT);
        c.setReadTimeout(Const.NETWORK_TIMEOUT);
        c.connect();
        int status = c.getResponseCode();

        if(status == 200){// OK
        	BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            br.close();
            return sb.toString();
        }
        return null;
	}
	
	public static Bitmap downloadBitmapFromURL(String link) throws IOException {
		URL url = new URL(link);
        HttpURLConnection connection = (HttpURLConnection) url
                .openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap myBitmap = BitmapFactory.decodeStream(input);

        return myBitmap;
	}
	
	public static Bitmap getBitmapFromFile(String path){
		Bitmap myBitmap = BitmapFactory.decodeFile(path);
		
		return myBitmap;
	}
	
	public static Bitmap getBitmapFromFile(File file){
		Bitmap myBitmap = BitmapFactory.decodeFile(file.getPath());
		
		return myBitmap;
	}

}
