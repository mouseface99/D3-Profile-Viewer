package net.mf99.d3viewer.ui;

import net.mf99.d3viewer.Const.ITEM_COLOR;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.unit.EquipShort;
import net.mf99.d3viewer.data.unit.Hero;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

public class HeroDetailSubView {	
	View mColor;
	ImageView mIcon;
	public HeroDetailSubView(ImageView iconView, View colorView) {
		super();
		this.mColor = colorView;
		this.mIcon = iconView;
	}
	
	public void setData(EquipShort equip){
		mColor.setBackgroundResource(Utils.getBackgroundColorResource(equip.mColor));
		setIcon(equip.mIcon);
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

}
