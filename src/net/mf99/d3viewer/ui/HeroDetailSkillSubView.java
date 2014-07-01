package net.mf99.d3viewer.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.mf99.d3viewer.Const.ServerPath;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.unit.Skill;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HeroDetailSkillSubView {	
	Skill mSkill;	
	ImageView mIcon;
	
	private OnSkillClickListener mListener;
	private File mCacheDir; 
	private File mFile;
	public HeroDetailSkillSubView(ImageView iconView, OnSkillClickListener listener) {		
		this.mIcon = iconView;
		this.mListener = listener;
		
		mIcon.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(mListener != null){
					mListener.onSkillClick(mSkill);
				}
			}
			
		});
		
		mCacheDir = iconView.getContext().getCacheDir();
		
	}
	
	public void setData(Skill skill){
		if(skill != null){
			mSkill = skill;
			mFile = new File(mCacheDir, mSkill.mIcon + ".png");
			setIcon(mSkill.mIcon);
		}
	}
	
	public void setIcon(String fileName){		
		
		if(!mFile.exists()){			
			SkillIconDownloadTask mTask = new SkillIconDownloadTask();
			mTask.execute(fileName);
		}
		else{
			mIcon.setImageBitmap(Utils.getBitmapFromFile(mFile.getPath()));
		}
		
			
		
	}
	
	class SkillIconDownloadTask extends AsyncTask<String, Void, Bitmap>{

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
				return Utils.downloadBitmapFromURL(ServerPath.getSkillMediaPath(params[0]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
    	
    }
	
	public static interface OnSkillClickListener{
		public void onSkillClick(Skill equip);
	}

}
