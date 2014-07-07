package net.mf99.d3viewer.ui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.Const.ServerPath;
import net.mf99.d3viewer.R;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.unit.EquipList;
import net.mf99.d3viewer.data.unit.EquipShort;
import net.mf99.d3viewer.data.unit.Hero;
import net.mf99.d3viewer.data.unit.Skill;

/**
 * A fragment representing a single Hero detail screen.
 * This fragment is either contained in a {@link HeroListActivity}
 * in two-pane mode (on tablets) or a {@link HeroDetailActivity}
 * on handsets.
 */
public class HeroDetailFragment extends Fragment 
								implements HeroDetailItemSubView.OnEquipClickListener, 
										   HeroDetailSkillSubView.OnSkillClickListener, 
										   OnRefreshListener, 
										   OnCancelListener{
	
	View mRootView;
	
    HeroDetailItemSubView mHead, mTorso, mWaist, mLeg, mFeets;
    HeroDetailItemSubView mShoulder, mHand, mRightRing, mMainhand;
    HeroDetailItemSubView mNeck, mBracers, mLeftRing, mOffhand;
    
    HeroDetailSkillSubView[] mActiveSkills, mPassiveSkills;

    private long hID;    
    private Hero mHero;    
    private PullToRefreshLayout mPullToRefreshLayout;    
    private LayoutInflater mInflater;
    private boolean isDialogDisplaying = false;
    private ImageView mInfo;
    private ProgressDialog mLoadingDialog;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hID = getArguments().getLong(Const.KEY_HERO_ID);
        
        mActiveSkills = new HeroDetailSkillSubView[6];
        mPassiveSkills = new HeroDetailSkillSubView[4];
        
        mLoadingDialog = new ProgressDialog(getActivity(), ProgressDialog.THEME_HOLO_DARK);
        mLoadingDialog.setTitle(R.string.loading);
		mLoadingDialog.setMessage(getString(R.string.loading_hero));
    }
    

    @Override
	public void onStart() {
		super.onStart();
		downloadData();
	}	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	mInflater = inflater;
    	mRootView = inflater.inflate(R.layout.fragment_hero_detail, container, false);    
    	
    	// Now find the PullToRefreshLayout to setup
        mPullToRefreshLayout = (PullToRefreshLayout) mRootView.findViewById(R.id.ptr_layout);

        // Now setup the PullToRefreshLayout
        ActionBarPullToRefresh.from(this.getActivity())
                // Mark All Children as pullable
                .allChildrenArePullable()
                // Set a OnRefreshListener
                .listener(this)
                // Finally commit the setup to our PullToRefreshLayout
                .setup(mPullToRefreshLayout);
    	
        mHead = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_head), mRootView.findViewById(R.id.bg_head), this);
        mTorso = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_torso), mRootView.findViewById(R.id.bg_torso), this);
        mWaist = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_waist), mRootView.findViewById(R.id.bg_waist), this);
        mLeg = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_legs), mRootView.findViewById(R.id.bg_legs), this);
        mFeets = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_feet), mRootView.findViewById(R.id.bg_feet), this);
        
        mShoulder = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_shoulder), mRootView.findViewById(R.id.bg_shoulder), this);
        mHand = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_hand), mRootView.findViewById(R.id.bg_hand), this);
        mRightRing = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_right_ring), mRootView.findViewById(R.id.bg_right_ring), this);
        mMainhand = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_mainhand), mRootView.findViewById(R.id.bg_mainhand), this);
        
        mNeck = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_neck), mRootView.findViewById(R.id.bg_neck), this);
        mBracers = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_bracers), mRootView.findViewById(R.id.bg_bracers), this);
        mLeftRing = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_left_ring), mRootView.findViewById(R.id.bg_left_ring), this);
        mOffhand = new HeroDetailItemSubView((ImageView)mRootView.findViewById(R.id.view_offhand), mRootView.findViewById(R.id.bg_offhand), this);

        mActiveSkills[0] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_active_skill_1), this);
        mActiveSkills[1] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_active_skill_2), this);
        mActiveSkills[2] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_active_skill_3), this);
        mActiveSkills[3] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_active_skill_4), this);
        mActiveSkills[4] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_active_skill_5), this);
        mActiveSkills[5] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_active_skill_6), this);
        
        mPassiveSkills[0] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_passive_skill_1), this);
        mPassiveSkills[1] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_passive_skill_2), this);
        mPassiveSkills[2] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_passive_skill_3), this);
        mPassiveSkills[3] = new HeroDetailSkillSubView((ImageView)mRootView.findViewById(R.id.view_passive_skill_4), this);
        
        mInfo = (ImageView)mRootView.findViewById(R.id.info_view);
        
        return mRootView;
    }
    
    public void setHeroData(Hero hero){
    	mHero = hero;
    	
    	getActivity().setTitle(mHero.mName + 
    						   ", Lv " + mHero.mLevel + " " + getString(Utils.getHeroClassNameSource(mHero.mClass)) + " " +
    						   getString(R.string.pull_down_to_update));
    	
    	EquipList mList = mHero.mEquips;
    	
    	// Set Item data
    	mHead.setData(mList.mHead);
    	mTorso.setData(mList.mTorso);
    	mWaist.setData(mList.mWaist);
    	mLeg.setData(mList.mLegs);
    	mFeets.setData(mList.mFeet);
        mShoulder.setData(mList.mShoulders);
        mHand.setData(mList.mHands);
        mRightRing.setData(mList.mRightFinger);
        mMainhand.setData(mList.mMainHand);
        mNeck.setData(mList.mNeck);
        mBracers.setData(mList.mBracers);
        mLeftRing.setData(mList.mLeftFinger);
        mOffhand.setData(mList.mOffHand);
        
        // Set Skills data
        for(int i=0; i<mHero.mActiveSkills.size(); i++)
        	mActiveSkills[i].setData(mHero.mActiveSkills.get(i));
        
        for(int i=0; i<mHero.mPassiveSkills.size(); i++)
        	mPassiveSkills[i].setData(mHero.mPassiveSkills.get(i));    
        
        mInfo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				HeroStatsDetailDialog dialog = new HeroStatsDetailDialog(getActivity(), 
															mInflater, mHero.mStats, 
															HeroDetailFragment.this);
    			dialog.show();
    			isDialogDisplaying = true;
			}
        	
        });
    }
    
    @Override
	public void onEquipClick(EquipShort equip) {
    	if(equip != null){
    		if(!isDialogDisplaying){    	
    			EquipDetailDialog dialog = new EquipDetailDialog(getActivity(), mInflater, equip.mToolkitParam, this);
    			dialog.show();
    			isDialogDisplaying = true;
    		}
    	}
	}
    
    @Override
	public void onSkillClick(Skill skill) {
    	if(skill != null){
    		if(!isDialogDisplaying){
    			SkillDetailDialog dialog = new SkillDetailDialog(getActivity(), mInflater, skill, this);
    			dialog.show();
    			isDialogDisplaying = true;
    		}
    	}
	}
    
    class HeroDataDownloadTask extends AsyncTask<Long, Void, Hero>{

		@Override
		protected Hero doInBackground(Long... heroID) {
			try {
				return Utils.translateJsonToHero(Utils.downloadJSONData(ServerPath.getHeroPath(heroID[0])));
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
		protected void onPostExecute(Hero result) {	
			try{
				super.onPostExecute(result);
				
				if(mLoadingDialog != null)
					mLoadingDialog.dismiss();
				
				if(result == null){
					Toast.makeText(getActivity(), "Download Hero data fail, pull down to refresh !", Toast.LENGTH_SHORT).show();
					getActivity().setTitle(R.string.loading_fail);
					mInfo.setOnClickListener(null);
				}
				else
					setHeroData(result);
				
				if(mPullToRefreshLayout != null)
					mPullToRefreshLayout.setRefreshComplete();
			}catch(Exception ex){}
		}
    }

	@Override
	public void onRefreshStarted(View view) {
		getActivity().setTitle(R.string.loading);
		downloadData();
	}
	
	private void downloadData(){
		mLoadingDialog.show();
		HeroDataDownloadTask downloadTask = new HeroDataDownloadTask();
		downloadTask.execute(hID);
	}

	@Override
	public void onCancel(DialogInterface arg0) {
		isDialogDisplaying = false;				
	}	
}
