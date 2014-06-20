package net.mf99.d3viewer.data;

import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.R;
import android.view.View;
import net.mf99.d3viewer.Const.HERO_CLASS;

public class HeroListItem {
	public HERO_CLASS mClass;
	public String mName;
	public int mLv;
	public boolean isMale;
	
	public View mView;
	
	public int getHeaderSource(){
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
	
	public int getHeroTypeSource(){
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
