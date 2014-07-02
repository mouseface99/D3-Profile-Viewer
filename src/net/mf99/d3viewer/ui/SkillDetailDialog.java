package net.mf99.d3viewer.ui;

import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.R;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.unit.Skill;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SkillDetailDialog extends AlertDialog {
	
	Context mContext;
	Builder mBuilder;	
	View mView;
	
	View mItemColor;
	ImageView mSkillIcon, mRuneIcon;
	TextView mSkillDesc, mSkillLv, mRuneName, mRuneDesc, mRuneLv;
	View mRuneView;
	
	Skill mSkill;

	LinearLayout mGemList, mAttrList;

	public SkillDetailDialog(Context context, LayoutInflater inflater, Skill skill, OnDismissListener listener) {
		super(context);
		
		mContext = context;
		
		mBuilder = new Builder(mContext, AlertDialog.THEME_HOLO_DARK);
		mBuilder.setOnDismissListener(listener);
		
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
		mSkillIcon.setImageBitmap(Utils.getBitmapFromFile(Const.getCacheFile(mContext, mSkill.mIcon)));	
		mSkillDesc.setText(mSkill.mDescription);
		mSkillLv.setText(mContext.getString(R.string.unlock_at_level) + " " + mSkill.mLevel);
		
		if(mSkill.mHasRune){			
			mRuneIcon.setImageResource(Utils.getRuneResource(mSkill.mRuneType));
			mRuneName.setText(mSkill.mRuneName);
			mRuneDesc.setText(mSkill.mRuneDescription);
			mRuneLv.setText(mContext.getString(R.string.unlock_at_level) + " " + mSkill.mRuneLevel);
		}
		else
			mRuneView.setVisibility(View.GONE);
		
		mBuilder.setTitle(mSkill.mName);
		mBuilder.setView(mView);		
		mBuilder.show();
	}
}
