package net.mf99.d3viewer.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.R;
import net.mf99.d3viewer.data.unit.Hero;

/**
 * A fragment representing a single Hero detail screen.
 * This fragment is either contained in a {@link HeroListActivity}
 * in two-pane mode (on tablets) or a {@link HeroDetailActivity}
 * on handsets.
 */
public class HeroDetailFragment extends Fragment {
	TextView mHeroInfo;
	
    HeroDetailSubView mHead, mTorso, mWaist, mLeg, mFeets;
    HeroDetailSubView mShoulder, mHand, mRightRing, mMainhand;
    HeroDetailSubView mNeck, mBracers, mLeftRing, mOffhand;
    
    ImageView[] mActiveSkills, mPassiveSkills;

    private String HeroID;
    private Hero mHero;    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        HeroID = getArguments().getString(Const.KEY_HERO_ID);
        mActiveSkills = new ImageView[6];
        mPassiveSkills = new ImageView[4];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hero_detail, container, false);
        
        mHeroInfo = (TextView)rootView.findViewById(R.id.hero_info_title);
        mHeroInfo.setText("Hero ID : " + HeroID);
        
        mHead = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_head), rootView.findViewById(R.id.bg_head));
        mTorso = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_torso), rootView.findViewById(R.id.bg_torso));
        mWaist = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_waist), rootView.findViewById(R.id.bg_waist));
        mLeg = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_legs), rootView.findViewById(R.id.bg_legs));
        mFeets = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_feet), rootView.findViewById(R.id.bg_feet));
        
        mShoulder = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_shoulder), rootView.findViewById(R.id.bg_shoulder));
        mHand = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_hand), rootView.findViewById(R.id.bg_hand));
        mRightRing = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_right_ring), rootView.findViewById(R.id.bg_right_ring));
        mMainhand = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_mainhand), rootView.findViewById(R.id.bg_mainhand));
        
        mNeck = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_neck), rootView.findViewById(R.id.bg_neck));
        mBracers = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_bracers), rootView.findViewById(R.id.bg_bracers));
        mLeftRing = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_left_ring), rootView.findViewById(R.id.bg_left_ring));
        mOffhand = new HeroDetailSubView((ImageView)rootView.findViewById(R.id.view_offhand), rootView.findViewById(R.id.bg_offhand));

        mActiveSkills[0] = (ImageView)rootView.findViewById(R.id.view_active_skill_1);
        mActiveSkills[1] = (ImageView)rootView.findViewById(R.id.view_active_skill_2);
        mActiveSkills[2] = (ImageView)rootView.findViewById(R.id.view_active_skill_3);
        mActiveSkills[3] = (ImageView)rootView.findViewById(R.id.view_active_skill_4);
        mActiveSkills[4] = (ImageView)rootView.findViewById(R.id.view_active_skill_5);
        mActiveSkills[5] = (ImageView)rootView.findViewById(R.id.view_active_skill_6);
        
        mPassiveSkills[0] = (ImageView)rootView.findViewById(R.id.view_passive_skill_1);
        mPassiveSkills[1] = (ImageView)rootView.findViewById(R.id.view_passive_skill_2);
        mPassiveSkills[2] = (ImageView)rootView.findViewById(R.id.view_passive_skill_3);
        mPassiveSkills[3] = (ImageView)rootView.findViewById(R.id.view_passive_skill_4);
        
        return rootView;
    }
}
