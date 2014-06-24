package net.mf99.d3viewer.ui;

import net.mf99.d3viewer.Const.ITEM_COLOR;
import net.mf99.d3viewer.R;
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
	
	public void setColor(ITEM_COLOR color){
		switch(color){
			case GRAY:
				mColor.setBackgroundResource(R.drawable.bg_equip_gray);
				break;
			case WHITE:
				mColor.setBackgroundResource(R.drawable.bg_equip_white);
				break;
			case BLUE:
				mColor.setBackgroundResource(R.drawable.bg_equip_blue);
				break;
			case YELLOW:
				mColor.setBackgroundResource(R.drawable.bg_equip_yellow);
				break;
			case ORANGE:
				mColor.setBackgroundResource(R.drawable.bg_equip_orange);
				break;
			case GREEN:
				mColor.setBackgroundResource(R.drawable.bg_equip_green);
				break;
		}
	}
	
	public void setIcon(String fileName){
		
	}

}
