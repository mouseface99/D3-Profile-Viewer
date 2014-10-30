package net.mf99.d3viewer.data.jsonformat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.ITEM_COLOR;
import net.mf99.d3viewer.Utils;

public class Equip extends JsonObjectBase{	
	private String name;
	private String icon;
	private String displayColor;	
	private int itemLevel;
	
	// Weapon
	private MaxMinItem dps, attacksPerSecond, maxDamage, minDamage;
	// Sheld
	private MaxMinItem blockChance;
	// Armor
	private MaxMinItem armor;	
	
	private AttributeList attributes;
	private AttributesRaw attributesRaw;	
	private ArrayList<Gem> gems;
	private double mBlockMin, mBlockMax;
	
	private ITEM_COLOR mColor;
	private final boolean isWepond, isShield;
	
	public Equip(JSONObject jsonData) {
		super(jsonData);
		isWepond = (dps != null);
		isShield = (blockChance != null);
		
		if(isShield){
			mBlockMax = (attributesRaw.getBlock_Amount_Item_Min().getMax() + attributesRaw.getBlock_Amount_Item_Delta().getMax());
			mBlockMin = attributesRaw.getBlock_Amount_Item_Min().getMax();
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDisplayColor() {
		return displayColor;
	}

	public void setDisplayColor(String displayColor) {
		this.displayColor = displayColor;
	}

	public int getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(int itemLevel) {
		this.itemLevel = itemLevel;
	}

	public MaxMinItem getDps() {
		return dps;
	}

	public void setDps(MaxMinItem dps) {
		this.dps = dps;
	}

	public MaxMinItem getAttacksPerSecond() {
		return attacksPerSecond;
	}

	public void setAttacksPerSecond(MaxMinItem attacksPerSecond) {
		this.attacksPerSecond = attacksPerSecond;
	}

	public MaxMinItem getMaxDamage() {
		return maxDamage;
	}

	public void setMaxDamage(MaxMinItem maxDamage) {
		this.maxDamage = maxDamage;
	}

	public MaxMinItem getMinDamage() {
		return minDamage;
	}

	public void setMinDamage(MaxMinItem minDamage) {
		this.minDamage = minDamage;
	}

	public MaxMinItem getBlockChance() {
		return blockChance;
	}

	public void setBlockChance(MaxMinItem blockChance) {
		this.blockChance = blockChance;
	}

	public MaxMinItem getArmor() {
		return armor;
	}

	public void setArmor(MaxMinItem armor) {
		this.armor = armor;
	}

	public AttributeList getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributeList attributes) {
		this.attributes = attributes;
	}

	public AttributesRaw getAttributesRaw() {
		return attributesRaw;
	}

	public void setAttributesRaw(AttributesRaw attributesRaw) {
		this.attributesRaw = attributesRaw;
	}

	public ArrayList<Gem> getGems() {
		if(gems == null)
			gems = new ArrayList<Gem>();
		return gems;
	}

	public void setGems(ArrayList<Gem> gems) {
		this.gems = gems;
	}

	public ITEM_COLOR getItemColor() {
		if(mColor == null)
			mColor = Utils.getItemColor(displayColor);
		return mColor;
	}

	public boolean isWepond() {
		return isWepond;
	}

	public boolean isShield() {
		return isShield;
	}
	
	public int getSocketNum(){
		return (int) attributesRaw.getSockets().getMax();
	}
	
	public String getBlockMax(){
		DecimalFormat formatter = new DecimalFormat("#.00");
		return (mBlockMax == 0) ? "0" : formatter.format(mBlockMax); 
	}
	
	public String getBlockMin(){
		DecimalFormat formatter = new DecimalFormat("#.00");
		return (mBlockMin == 0) ? "0" : formatter.format(mBlockMin);
	}

	public static class AttributeList extends JsonObjectBase{
		private List<Attribute> primary, secondary, passive;
		public AttributeList(JSONObject jsonData) {
			super(jsonData);
		}
		
		public List<Attribute> getPrimary() {
			if(primary == null)
				primary = new ArrayList<Attribute>();
			return primary;
		}

		public List<Attribute> getSecondary() {
			if(secondary == null)
				secondary = new ArrayList<Attribute>();
			return secondary;
		}

		public List<Attribute> getPassive() {
			if(passive == null)
				passive = new ArrayList<Attribute>();
			return passive;
		}



		public static class Attribute extends JsonObjectBase{
			private String text;
			public Attribute(JSONObject jsonData) {
				super(jsonData);
			}
			public String getVaule() {
				return text;
			}
			public void setText(String text) {
				this.text = text;
			}
			
		}
	}
	
	public static class AttributesRaw extends JsonObjectBase{
		private MaxMinItem Sockets, Block_Amount_Item_Min, Block_Amount_Item_Delta;
		public AttributesRaw(JSONObject jsonData) {
			super(jsonData);
		}
		public MaxMinItem getSockets() {
			return Sockets;
		}
		public void setSockets(MaxMinItem sockets) {
			Sockets = sockets;
		}
		public MaxMinItem getBlock_Amount_Item_Min() {
			return Block_Amount_Item_Min;
		}
		public void setBlock_Amount_Item_Min(MaxMinItem block_Amount_Item_Min) {
			Block_Amount_Item_Min = block_Amount_Item_Min;
		}
		public MaxMinItem getBlock_Amount_Item_Delta() {
			return Block_Amount_Item_Delta;
		}
		public void setBlock_Amount_Item_Delta(MaxMinItem block_Amount_Item_Delta) {
			Block_Amount_Item_Delta = block_Amount_Item_Delta;
		}
	}
	
	public static class MaxMinItem extends JsonObjectBase{
		private double max;
		private DecimalFormat formatter = new DecimalFormat("#.00");
		public MaxMinItem(JSONObject jsonData) {
			super(jsonData);
		}
		public String getValue() {
			if(max == 0) return "0";
			else return formatter.format(max);
		}
		public void setMax(double max) {
			this.max = max;
		}
		public double getMax(){
			return max;
		}
	}
}
