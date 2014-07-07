package net.mf99.d3viewer.ui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.R;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.unit.Equip;
import net.mf99.d3viewer.data.unit.Gem;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class EquipDetailDialog {
	
	Context mContext;
	AlertDialog mDialog;
	AlertDialog.Builder mBuilder;
	String mToolTip;
	View mView;
	
	View mItemColor;
	ImageView mItemIcon;
	TextView mMainValue, mMainValueType, mMaxMinDamage, mAttackSpeed, mItemLevel;

	LinearLayout mGemList, mAttrList;
	OnCancelListener mListener;

	public EquipDetailDialog(Context context, LayoutInflater inflater, String toolTip, OnCancelListener listener){
		mContext = context;
		
		mListener = listener;
		mBuilder = new AlertDialog.Builder(mContext, AlertDialog.THEME_HOLO_DARK);
		mBuilder.setOnCancelListener(listener);
		mView = inflater.inflate(R.layout.equip_detail_view, null);
		mToolTip = toolTip;
		
		mItemColor = mView.findViewById(R.id.view_item_color);
		mItemIcon = (ImageView)mView.findViewById(R.id.view_item_icon);
		
		mMainValue = (TextView)mView.findViewById(R.id.equip_main_value);
		mMainValueType = (TextView)mView.findViewById(R.id.equip_main_value_type);
		mMaxMinDamage = (TextView)mView.findViewById(R.id.equip_max_min_text);
		mAttackSpeed = (TextView)mView.findViewById(R.id.equip_attack_per_sec);
		mItemLevel = (TextView)mView.findViewById(R.id.view_item_level);
		
		mGemList = (LinearLayout)mView.findViewById(R.id.view_item_gems);
		mAttrList = (LinearLayout)mView.findViewById(R.id.view_item_attrs);
	}
	
	public void show(){
		DownloadItemDetailTask task = new DownloadItemDetailTask();
		task.execute(mToolTip);
		mBuilder.setTitle(R.string.loading);
		mBuilder.setView(new ProgressBar(mContext));
		mDialog = mBuilder.show();
	}
	
	private void setData(Equip data){
		mItemColor.setBackgroundResource(Utils.getBackgroundColorResource(data.mColor));
		mItemIcon.setImageBitmap(Utils.getBitmapFromFile(Const.getCacheFile(mContext, data.mIcon)));
		
		if(data.isWepond){
			mMainValue.setText(String.valueOf(data.mDps));
			mMainValueType.setText(R.string.main_value_type_dps);
			mMaxMinDamage.setText(data.mMinDamage + "-" + data.mMaxDamage + " " + mContext.getString(R.string.damage));
			mAttackSpeed.setText(String.valueOf(data.mAttackPerSec) + " " + mContext.getString(R.string.hit_per_sec));
		}
		else{
			if(data.mArmor != -1){
				mMainValue.setText(String.valueOf(data.mArmor));
				mMainValueType.setText(R.string.main_value_type_armor);
				
				if(data.isShield){
					mMaxMinDamage.setText(data.mBlockMin + "-" + data.mBlockMax + " " + mContext.getString(R.string.block_value));
					mAttackSpeed.setText(String.valueOf(data.mBlockChance*100) + " " + mContext.getString(R.string.block_chance));
				}
			}
			else{
				mMainValue.setVisibility(View.GONE);
				mMainValueType.setVisibility(View.GONE);
			}
		}
		
		mItemLevel.setText(mContext.getString(R.string.item_level) + " " + data.mItemLevel);
		
		TextView tv;
		
		for(String attr : data.mPrimaryAttr){
			tv = new TextView(mContext);
			tv.setText(attr);
			tv.setTextColor(Color.rgb(0x68, 0x67,0xFA));
			tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.attr_dot, 0, 0, 0);
			mAttrList.addView(tv);
		}
		
		for(String attr : data.mSecandaryAttr){
			tv = new TextView(mContext);
			tv.setText(attr);
			tv.setTextColor(Color.rgb(0xBF, 0xA6, 0xD6));
			tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.attr_dot, 0, 0, 0);
			mAttrList.addView(tv);
		}
		
		for(String attr : data.mPassiveAttr){
			tv = new TextView(mContext);
			tv.setText(attr);
			tv.setTextColor(Color.rgb(0xC6, 0x56, 0x23));
			tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.attr_dot, 0, 0, 0);
			mAttrList.addView(tv);
		}
		
		for(int i=0; i<data.mSocketNum; i++){
			if(i<data.mGems.size()){
				Gem gem = data.mGems.get(i);
				tv = new TextView(mContext);
				tv.setText(gem.mAttribute);
				tv.setCompoundDrawablesWithIntrinsicBounds(Utils.getGemIconResource(gem), 0, 0, 0);				
			}
			else{
				tv = new TextView(mContext);
				tv.setText(R.string.empty_socket);
				
				tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.gem_socket, 0, 0, 0);				
			}
			tv.setGravity(Gravity.CENTER);
			tv.setTextColor(Color.WHITE);
			mGemList.addView(tv);
		}		
		
		mBuilder.setTitle(data.mName);
		mBuilder.setView(mView);
		mDialog.dismiss();
		mDialog = mBuilder.show();
	}
	
	private class DownloadItemDetailTask extends AsyncTask<String, Void, Equip>{

		@Override
		protected Equip doInBackground(String... params) {			
			try {
				return Utils.translateJsonToEquip(Utils.downloadJSONData(Const.ServerPath.getItemDetailPath(params[0])));
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Equip result) {
			super.onPostExecute(result);
			
			if(result != null)
				setData(result);
			else{
				Toast.makeText(mContext, "Download item data fail", Toast.LENGTH_SHORT).show();
				mListener.onCancel(null);
			}
		}
		
	}

}
