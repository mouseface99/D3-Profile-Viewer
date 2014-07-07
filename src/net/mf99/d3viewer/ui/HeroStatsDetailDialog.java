package net.mf99.d3viewer.ui;

import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.R;
import net.mf99.d3viewer.data.unit.Hero;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HeroStatsDetailDialog {
	
	Context mContext;
	AlertDialog.Builder mBuilder;	
	View mView;
	
	TextView vLevel, vPLevel, vPSource, vSSource,
			 vStr, vDex, vInt, vVit,
			 vResistPhy, vResistFire, vResistCold, vResistLight, vResistPoison, vResistArcane,
			 vDamage, vAttkSpeed, vCritChance, vCritDamage, vDamageIncre,
			 vArmor, vBlockChance, vBlockAmount, vThorns, vDamageReduc,
			 vLife, vLifeSteal, vLifePerKill, vLifeOnHit, vMF, vGF;
	
	TextView vPSourceTitle, vSSourceTitle;
	
	Hero.Stats mStats;

	public HeroStatsDetailDialog(Context context, LayoutInflater inflater, Hero.Stats stats, OnCancelListener listener) {
		mContext = context;
		mStats = stats;
		
		mBuilder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_DARK);
		mBuilder.setOnCancelListener(listener);		
		
		mView = inflater.inflate(R.layout.hero_stats_detail_view, null);
		
		
		vLevel = (TextView)mView.findViewById(R.id.level_value);
		vPLevel = (TextView)mView.findViewById(R.id.plevel_value);
		vPSource = (TextView)mView.findViewById(R.id.psource_value);
		vSSource = (TextView)mView.findViewById(R.id.ssource_value);
		vPSourceTitle = (TextView)mView.findViewById(R.id.psource_title);
		vSSourceTitle = (TextView)mView.findViewById(R.id.ssource_title);
		
		vStr = (TextView)mView.findViewById(R.id.str_value);
		vDex = (TextView)mView.findViewById(R.id.dex_value);
		vInt = (TextView)mView.findViewById(R.id.int_value);
		vVit = (TextView)mView.findViewById(R.id.vit_value);
		
		vResistPhy = (TextView)mView.findViewById(R.id.phy_value);
		vResistFire = (TextView)mView.findViewById(R.id.fire_value);
		vResistCold = (TextView)mView.findViewById(R.id.cold_value);
		vResistLight = (TextView)mView.findViewById(R.id.light_value);
		vResistPoison = (TextView)mView.findViewById(R.id.poison_value);
		vResistArcane = (TextView)mView.findViewById(R.id.arcane_value);
		
		vDamage = (TextView)mView.findViewById(R.id.damage_value);
		vAttkSpeed = (TextView)mView.findViewById(R.id.attack_speed_value);
		vCritChance = (TextView)mView.findViewById(R.id.crit_chance_value);
		vCritDamage = (TextView)mView.findViewById(R.id.crit_damage_value);
		vDamageIncre = (TextView)mView.findViewById(R.id.damage_increase_value);
		
		vArmor = (TextView)mView.findViewById(R.id.armor_value);
		vBlockChance = (TextView)mView.findViewById(R.id.block_chance_value);
		vBlockAmount = (TextView)mView.findViewById(R.id.block_amount_value);
		vThorns = (TextView)mView.findViewById(R.id.thorns_value);
		vDamageReduc = (TextView)mView.findViewById(R.id.damage_reduction_value);
		
		vLife = (TextView)mView.findViewById(R.id.life_value);
		vLifeSteal = (TextView)mView.findViewById(R.id.life_steal_value);
		vLifePerKill = (TextView)mView.findViewById(R.id.life_per_kill_value);
		vLifeOnHit = (TextView)mView.findViewById(R.id.life_on_hit_value);
		vMF = (TextView)mView.findViewById(R.id.mf_value);
		vGF = (TextView)mView.findViewById(R.id.gf_value);
	}
	
	public void show(){
		// Info
		vLevel.setText(String.valueOf(mStats.mLevel));
		vPLevel.setText(String.valueOf(Const.PARAGON_LEVEL));
		vPSource.setText(String.valueOf(mStats.mSourcePrimary));
		if(mStats.mSourceSecandary > 0)
			vSSource.setText(String.valueOf(mStats.mSourceSecandary));
		else{
			vSSource.setVisibility(View.GONE);
			vSSourceTitle.setVisibility(View.GONE);			
			vPSourceTitle.setText(R.string.stats_source);
		}
		
		// Basic attr
		vStr.setText(String.valueOf(mStats.mStr));
		vDex .setText(String.valueOf(mStats.mDex));
		vInt.setText(String.valueOf(mStats.mInt));
		vVit.setText(String.valueOf(mStats.mVit));
		
		// Resist
		vResistPhy.setText(String.valueOf(mStats.mResistPhysical));
		vResistFire.setText(String.valueOf(mStats.mResistFire));
		vResistCold.setText(String.valueOf(mStats.mResistCold));
		vResistLight.setText(String.valueOf(mStats.mResistLightning));
		vResistPoison.setText(String.valueOf(mStats.mResistPoison));
		vResistArcane.setText(String.valueOf(mStats.mResistArcane));
		
		// Attack
		vDamage.setText(String.valueOf(mStats.mDamage));
		vAttkSpeed.setText(String.valueOf(mStats.mAttackSpeed));
		vCritChance.setText(String.valueOf(mStats.mCritChance) + "%");
		vCritDamage.setText("+" + String.valueOf(mStats.mCritDamage*100) + "%");
		vDamageIncre.setText(String.valueOf(mStats.mDamageIncrease));
		
		// Defense
		vArmor.setText(String.valueOf(mStats.mArmor));
		vBlockChance.setText(String.valueOf(mStats.mBlockChance*100) + "%");
		vBlockAmount.setText(mStats.mBlockMin + " - " + mStats.mBlockMax);
		vThorns.setText(String.valueOf(mStats.mThorns));
		vDamageReduc.setText(String.valueOf(mStats.mDamageReduction));
		
		// Others
		vLife.setText(String.valueOf(mStats.mLife));
		vLifeSteal.setText(String.valueOf(mStats.mLifeSteal*100) + "%");
		vLifePerKill.setText(String.valueOf(mStats.mLifePerKill));
		vLifeOnHit.setText(String.valueOf(mStats.mLifeOnHit));
		vMF.setText(String.valueOf(mStats.mMagicFind*100) + "%");
		vGF.setText(String.valueOf(mStats.mGoldFind*100) + "%");	
				
		// Progression
		setProgression(mStats.mProgression);
		
		mBuilder.setView(mView);		
		mBuilder.show();
	}
	
	private void setProgression(int progression){
    	ImageView act = null;
    	switch(progression){
    		case 5:
    			act = (ImageView)mView.findViewById(R.id.view_progression_act5);
    			act.setImageResource(R.drawable.progression_act5_pass);
    		case 4:
    			act = (ImageView)mView.findViewById(R.id.view_progression_act4);
    			act.setImageResource(R.drawable.progression_act4_pass);
    		case 3:
    			act = (ImageView)mView.findViewById(R.id.view_progression_act3);
    			act.setImageResource(R.drawable.progression_act3_pass);
    		case 2:
    			act = (ImageView)mView.findViewById(R.id.view_progression_act2);
    			act.setImageResource(R.drawable.progression_act2_pass);
    		case 1:
    			act = (ImageView)mView.findViewById(R.id.view_progression_act1);
    			act.setImageResource(R.drawable.progression_act1_pass);
    			
    	}
    }
}
