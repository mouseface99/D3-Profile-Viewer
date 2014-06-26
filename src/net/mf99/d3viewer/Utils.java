package net.mf99.d3viewer;

import net.mf99.d3viewer.Const.HERO_CLASS;
import net.mf99.d3viewer.Const.ITEM_COLOR;

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

}
