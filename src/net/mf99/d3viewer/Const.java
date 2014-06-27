package net.mf99.d3viewer;

import android.util.Log;

public class Const {
	
	public static String DATA_BATTLE_ACCOUNT = null;
	public static String DATA_BATTLE_CODE = null;
	public static String DATA_BATTLE_REGION = null;
	public static final String KEY_HERO_ID = "key_of_hero_id";
	
	public static enum SERVER_REGION{
		TW,
		US,
		EU,
		SEA,
		KR
	}
	
	public static enum HERO_CLASS{
		HUNTER,
		MONK,
		DOCTOR,
		BARBARIAN,
		WIZARD,
		CRUSADER
	}
	
	public static enum GEM_CLASS{
		AMETHYSY,
		DIAMOND,
		EMERALD,
		RUBY,
		TOPAZ
	}
	
	public static enum ITEM_COLOR{
		GRAY,
		WHITE,
		BLUE,
		YELLOW,
		ORANGE,
		GREEN
	}
	
	public static class ServerPath{
		public static final String PREFIX = "http://";
		public static final String HOST = ".battle.net/api/d3/";
		
		public static String getProfilePath(){
			return PREFIX + DATA_BATTLE_REGION + HOST + "profile/" + DATA_BATTLE_ACCOUNT + "-" + DATA_BATTLE_CODE + "/";
		}
		
		public static String getHeroPath(long ID){			
			return getProfilePath() + "hero/" + String.valueOf(ID);
		}
	}
	
	public static class ProfileKeys{
		public static final String KEY_TAG = "battleTag";
		public static final String KEY_HEROS = "heroes";
		public static final String KEY_ID = "id";
		public static final String KEY_NAME = "name";
		public static final String KEY_LV = "level";
		public static final String KEY_PLV = "paragonLevel";
		public static final String KEY_GENDER = "gender";
		public static final String KEY_CLASS = "class";
		public static final String KEY_KILLS = "kills";
		public static final String KEY_KILLS_MONSTER = "monsters";
		public static final String KEY_KILLS_ELITE = "elites";
	}
	
	public static class HeroKeys{
		public static final String KEY_ID = "id";
		public static final String KEY_NAME = "name";
		public static final String KEY_LV = "level";
		public static final String KEY_GENDER = "gender";
		public static final String KEY_CLASS = "class";
		public static final String KEY_PROG = "progression";		
		
		public static final String KEY_SKILL = "skills";
		public static final String KEY_SKILL_ACTIVE = "active";
		public static final String KEY_SKILL_PASSIVE = "passive";
		
		public static final String KEY_ITEM = "items";
		
	}
}
