package net.mf99.d3viewer;

import java.io.File;

import android.content.Context;

public class Const {
	
	public static final int NETWORK_TIMEOUT = 5000;
	
	public static String DATA_BATTLE_ACCOUNT = null;
	public static String DATA_BATTLE_CODE = null;
	public static String DATA_BATTLE_REGION = null;
	public static int PARAGON_LEVEL;
	
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
		AMETHYST,
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
	
	public static enum ERROR_TYPE{
		NONE,
		NETWORK_ERROR,
		INVALID_BATTLETAG,
	}
	
	public static class ServerPath{
		public static final String PREFIX = "http://";
		public static final String HOST = ".battle.net/api/d3/";
		public static final String MEDIA_ITEM = "media.blizzard.com/d3/icons/items/large/";
		public static final String MEDIA_SKILL = "media.blizzard.com/d3/icons/skills/64/";
		
		public static String PROFILE_PATH;
		
		public static String getHeroPath(long ID){			
			return PROFILE_PATH + "hero/" + String.valueOf(ID);
		}
		
		public static String getItemMediaPath(String fileName){
			return PREFIX + MEDIA_ITEM + fileName + ".png";
		}
		
		public static String getSkillMediaPath(String fileName){
			return PREFIX + MEDIA_SKILL + fileName + ".png";
		}
		
		public static String getItemDetailPath(String tooltipParams){
			return PREFIX + DATA_BATTLE_REGION + HOST + "data/" + tooltipParams;
		}
	}
	
	public static File getCacheFile(Context context, String fileName){
		return new File(context.getCacheDir(), fileName + ".png");
	}
}
