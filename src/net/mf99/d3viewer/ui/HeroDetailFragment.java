package net.mf99.d3viewer.ui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
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

/**
 * A fragment representing a single Hero detail screen.
 * This fragment is either contained in a {@link HeroListActivity}
 * in two-pane mode (on tablets) or a {@link HeroDetailActivity}
 * on handsets.
 */
public class HeroDetailFragment extends Fragment implements HeroDetailSubView.OnEquipClickListener{
	
	View mRootView;
	
    HeroDetailSubView mHead, mTorso, mWaist, mLeg, mFeets;
    HeroDetailSubView mShoulder, mHand, mRightRing, mMainhand;
    HeroDetailSubView mNeck, mBracers, mLeftRing, mOffhand;
    
    ImageView[] mActiveSkills, mPassiveSkills;

    private long hID;
    
    private Hero mHero;    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle args = this.getArguments();
        
        hID = args.getLong(Const.KEY_HERO_ID);
        
        mActiveSkills = new ImageView[6];
        mPassiveSkills = new ImageView[4];
    }
    

    @Override
	public void onStart() {
		super.onStart();
		HeroDataDownloadTask downloadTask = new HeroDataDownloadTask();
		downloadTask.execute(hID);
	}	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	mRootView = inflater.inflate(R.layout.fragment_hero_detail, container, false);    
    	
        mHead = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_head), mRootView.findViewById(R.id.bg_head), this);
        mTorso = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_torso), mRootView.findViewById(R.id.bg_torso), this);
        mWaist = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_waist), mRootView.findViewById(R.id.bg_waist), this);
        mLeg = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_legs), mRootView.findViewById(R.id.bg_legs), this);
        mFeets = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_feet), mRootView.findViewById(R.id.bg_feet), this);
        
        mShoulder = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_shoulder), mRootView.findViewById(R.id.bg_shoulder), this);
        mHand = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_hand), mRootView.findViewById(R.id.bg_hand), this);
        mRightRing = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_right_ring), mRootView.findViewById(R.id.bg_right_ring), this);
        mMainhand = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_mainhand), mRootView.findViewById(R.id.bg_mainhand), this);
        
        mNeck = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_neck), mRootView.findViewById(R.id.bg_neck), this);
        mBracers = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_bracers), mRootView.findViewById(R.id.bg_bracers), this);
        mLeftRing = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_left_ring), mRootView.findViewById(R.id.bg_left_ring), this);
        mOffhand = new HeroDetailSubView((ImageView)mRootView.findViewById(R.id.view_offhand), mRootView.findViewById(R.id.bg_offhand), this);

        mActiveSkills[0] = (ImageView)mRootView.findViewById(R.id.view_active_skill_1);
        mActiveSkills[1] = (ImageView)mRootView.findViewById(R.id.view_active_skill_2);
        mActiveSkills[2] = (ImageView)mRootView.findViewById(R.id.view_active_skill_3);
        mActiveSkills[3] = (ImageView)mRootView.findViewById(R.id.view_active_skill_4);
        mActiveSkills[4] = (ImageView)mRootView.findViewById(R.id.view_active_skill_5);
        mActiveSkills[5] = (ImageView)mRootView.findViewById(R.id.view_active_skill_6);
        
        mPassiveSkills[0] = (ImageView)mRootView.findViewById(R.id.view_passive_skill_1);
        mPassiveSkills[1] = (ImageView)mRootView.findViewById(R.id.view_passive_skill_2);
        mPassiveSkills[2] = (ImageView)mRootView.findViewById(R.id.view_passive_skill_3);
        mPassiveSkills[3] = (ImageView)mRootView.findViewById(R.id.view_passive_skill_4);
        
        return mRootView;
    }
    
    public void setHeroData(Hero hero){
    	mHero = hero;
    	
    	EquipList mList = mHero.mEquips;
    	
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
        
        setProgression(mHero.mProgression);
    	
    }
    
    private void setProgression(int progression){
    	ImageView act = null;
    	switch(progression){
    		case 5:
    			act = (ImageView)mRootView.findViewById(R.id.view_progression_act5);
    			act.setImageResource(R.drawable.progression_act5_pass);
    		case 4:
    			act = (ImageView)mRootView.findViewById(R.id.view_progression_act4);
    			act.setImageResource(R.drawable.progression_act4_pass);
    		case 3:
    			act = (ImageView)mRootView.findViewById(R.id.view_progression_act3);
    			act.setImageResource(R.drawable.progression_act3_pass);
    		case 2:
    			act = (ImageView)mRootView.findViewById(R.id.view_progression_act2);
    			act.setImageResource(R.drawable.progression_act2_pass);
    		case 1:
    			act = (ImageView)mRootView.findViewById(R.id.view_progression_act1);
    			act.setImageResource(R.drawable.progression_act1_pass);
    			
    	}
    }
    
    @Override
	public void onEquipClick(EquipShort equip) {
		
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
			super.onPostExecute(result);
			
			if(result == null)
				Toast.makeText(getActivity(), "Download Hero data fail", Toast.LENGTH_SHORT).show();
			else
				setHeroData(result);
		}
    	
    }

	
}
