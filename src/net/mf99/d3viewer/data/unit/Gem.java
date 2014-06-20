package net.mf99.d3viewer.data.unit;

import net.mf99.d3viewer.R;
import net.mf99.d3viewer.Const.GEM_CLASS;

public class Gem {
	GEM_CLASS mClass;
	String mName;
	int mLevel;
	String mAttribute;
	
	public int getIconResource(){
		int base;
		switch(mClass){
			case AMETHYSY:
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
		
		return (base+(mLevel-1));
	}
	
}
