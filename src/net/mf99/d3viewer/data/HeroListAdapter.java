package net.mf99.d3viewer.data;

import java.util.ArrayList;

import net.mf99.d3viewer.R;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.unit.Hero;
import net.mf99.d3viewer.data.unit.HeroShort;
import net.mf99.d3viewer.data.unit.Profile;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class HeroListAdapter extends BaseAdapter {
	Context mContext;
	ArrayList<HeroShort> mList;
	View mHeaderView;
	Hero mEmptyHero;
	LayoutInflater mInflater;
	int mPatagonLv;
	long mKill, mEliteKill;
	
	public boolean isTemp;
	
	public HeroListAdapter(Context context, int patagonLv, long kill, long eliteKill) {
		mContext = context;
		mPatagonLv = patagonLv;
		mKill = kill;
		mEliteKill = eliteKill;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		mList = new ArrayList<HeroShort>();
		
		isTemp = true;
	}
	
	public HeroListAdapter(Context context, Profile profile) {
		this(context, profile.mParagonLevel, profile.mKilled, profile.mEliteKilled);
		
		mList = profile.mHeros;
		
		isTemp = false;
	}

	@Override
	public int getCount() {
		return mList.size()+1;
	}

	@Override
	public HeroShort getItem(int index) {
		if(index == 0)
			return null;
		else
			return mList.get(index-1);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index-1;
	}

	@Override
	public View getView(int index, View view, ViewGroup group) {
		// Customized Header
		if(index == 0){
			if(mHeaderView == null) {
				mHeaderView = mInflater.inflate(R.layout.hero_list_header, null);			
			
				((TextView)mHeaderView.findViewById(R.id.paragon_level)).setText(String.valueOf(mPatagonLv));
				((TextView)mHeaderView.findViewById(R.id.number_kill)).setText(String.valueOf(mKill));
				((TextView)mHeaderView.findViewById(R.id.number_kill_elite)).setText(String.valueOf(mEliteKill));
			}
			
			return mHeaderView;			
		}
		else{// Hero List
			if(view == null)
				view = mInflater.inflate(R.layout.hero_list_item, null);
			
			HeroShort hero = mList.get(index-1);
			
			
			((ImageView)view.findViewById(R.id.hero_head)).setImageResource(Utils.getHeroHeaderSource(hero.mClass, hero.isMale));
			
			((TextView)view.findViewById(R.id.hero_name)).setText(hero.mName);
			String heroInfo = "Lv " + hero.mLevel + " " + mContext.getString(Utils.getHeroClassNameSource(hero.mClass));
			((TextView)view.findViewById(R.id.hero_info)).setText(heroInfo);
			
			return view;
			
		}
	}
}
