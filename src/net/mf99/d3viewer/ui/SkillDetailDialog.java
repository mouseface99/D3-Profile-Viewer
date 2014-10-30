package net.mf99.d3viewer.ui;

import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.R;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.jsonformat.Skill;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SkillDetailDialog {
	
	Context mContext;
	AlertDialog.Builder mBuilder;	
	View mView;
	
	View mItemColor;
	ImageView mSkillIcon, mRuneIcon;
	TextView mSkillDesc, mSkillLv, mRuneName, mRuneDesc, mRuneLv;
	View mRuneView;
	
	Skill mSkill;

	LinearLayout mGemList, mAttrList;

	public SkillDetailDialog(Context context, LayoutInflater inflater, Skill skill, OnCancelListener listener) {
		
		mContext = context;
		
		mBuilder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_DARK);
		mBuilder.setOnCancelListener(listener);
		
		mSkill = skill;
		mView = inflater.inflate(R.layout.skill_detail_view, null);
		
		mSkillIcon = (ImageView)mView.findViewById(R.id.skill_icon);
		mRuneIcon = (ImageView)mView.findViewById(R.id.rune_icon);
		
		mSkillDesc = (TextView)mView.findViewById(R.id.skill_description);
		mSkillLv = (TextView)mView.findViewById(R.id.skill_level);
		mRuneName = (TextView)mView.findViewById(R.id.rune_name);
		mRuneDesc = (TextView)mView.findViewById(R.id.rune_description);
		mRuneLv = (TextView)mView.findViewById(R.id.rune_level);
		
		mRuneView = mView.findViewById(R.id.rune_layout);
		
		
	}
	
	public void show(){
		Skill.SkillDetail skill = mSkill.getSkill();
		Skill.RuneDetail rune = mSkill.getRune();		
		mSkillIcon.setImageBitmap(Utils.getBitmapFromFile(Const.getCacheFile(mContext, skill.getIcon())));	
		mSkillDesc.setText(skill.getDescription());
		mSkillLv.setText(mContext.getString(R.string.unlock_at_level) + " " + skill.getLevel());
		
		if(mSkill.hasRune()){
			mRuneIcon.setImageResource(Utils.getRuneResource(rune.getRuneType()));
			mRuneName.setText(rune.getName());
			mRuneDesc.setText(rune.getDescription());
			mRuneLv.setText(mContext.getString(R.string.unlock_at_level) + " " + rune.getLevel());
		}
		else
			mRuneView.setVisibility(View.GONE);	
		
		mBuilder.setTitle(skill.getName());
		mBuilder.setView(mView);		
		mBuilder.show();
	}
}
