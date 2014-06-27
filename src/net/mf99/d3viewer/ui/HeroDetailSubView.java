package net.mf99.d3viewer.ui;

import net.mf99.d3viewer.Const.ITEM_COLOR;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.unit.EquipShort;
import net.mf99.d3viewer.data.unit.Hero;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HeroDetailSubView {	
	EquipShort mEquip;
	View mColor;
	ImageView mIcon;
	private OnEquipClickListener mListener;
	public HeroDetailSubView(ImageView iconView, View colorView, OnEquipClickListener listener) {
		super();
		this.mColor = colorView;
		this.mIcon = iconView;
		this.mListener = listener;
		
		mIcon.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(mListener != null){
					mListener.onEquipClick(mEquip);
				}
			}
			
		});
	}
	
	public void setData(EquipShort equip){
		if(equip != null){
			mEquip = equip;
			mColor.setBackgroundResource(Utils.getBackgroundColorResource(equip.mColor));
			setIcon(equip.mIcon);
			
			Log.d("MIKE", equip.mName + " : " + equip.mIcon);
		}
		else
			Log.d("MIKE", "Equip is null");
	}
	
	public void setBlankData(){
		mColor.setBackgroundResource(Utils.getBackgroundColorResource(ITEM_COLOR.GRAY));
	}
	
	public void setIcon(String fileName){
		
	}
	
	class EquipIconDownloadTask extends AsyncTask<String, Void, Hero>{

		@Override
		protected void onPostExecute(Hero result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		@Override
		protected Hero doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
    	
    }
	
	public static interface OnEquipClickListener{
		public void onEquipClick(EquipShort equip);
	}

}
