package net.mf99.d3viewer.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.mf99.d3viewer.Const.ITEM_COLOR;
import net.mf99.d3viewer.Const.ServerPath;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.unit.EquipShort;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HeroDetailItemSubView {	
	EquipShort mEquip;
	View mColor;
	ImageView mIcon;
	
	private OnEquipClickListener mListener;
	private File mCacheDir; 
	private File mFile;
	public HeroDetailItemSubView(ImageView iconView, View colorView, OnEquipClickListener listener) {		
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
		
		mCacheDir = mColor.getContext().getCacheDir();
		
	}
	
	public void setData(EquipShort equip){
		if(equip != null){
			mEquip = equip;
			mColor.setBackgroundResource(Utils.getBackgroundColorResource(equip.mColor));
			mFile = new File(mCacheDir, equip.mIcon + ".png");
			setIcon(equip.mIcon);
		}
	}
	
	public void setBlankData(){
		mColor.setBackgroundResource(Utils.getBackgroundColorResource(ITEM_COLOR.GRAY));
	}
	
	public void setIcon(String fileName){		
		
		if(!mFile.exists()){			
			EquipIconDownloadTask mTask = new EquipIconDownloadTask();
			mTask.execute(fileName);
		}
		else{
			mIcon.setImageBitmap(Utils.getBitmapFromFile(mFile.getPath()));
		}
		
			
		
	}
	
	class EquipIconDownloadTask extends AsyncTask<String, Void, Bitmap>{

		@Override
		protected void onPostExecute(Bitmap result) {			
			super.onPostExecute(result);
			
			if(result != null){
				mIcon.setImageBitmap(result);
				try {
					result.compress(CompressFormat.PNG, 100, new FileOutputStream(mFile));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				return Utils.downloadBitmapFromURL(ServerPath.getItemMediaPath(params[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
    	
    }
	
	public static interface OnEquipClickListener{
		public void onEquipClick(EquipShort equip);
	}

}
