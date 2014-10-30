package net.mf99.d3viewer.data.jsonformat;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.GEM_CLASS;
import net.mf99.d3viewer.Utils;
import net.mf99.d3viewer.data.jsonformat.Equip.AttributeList;

public class Gem extends JsonObjectBase {
	private Item item;
	private AttributeList attributes;
	private GEM_CLASS mClass;	
	private int mLevel;
	private String mAttribute;	
	
	public Gem(JSONObject jsonData) {
		super(jsonData);
		
		mClass = Utils.getGemClass(item.getId());
		mLevel = Utils.getGemLevel(item.getId());
		mAttribute = attributes.getPrimary().get(0).getVaule();
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setAttributes(AttributeList attributes) {
		this.attributes = attributes;
	}
	
	public GEM_CLASS getGemClass() {
		return mClass;
	}

	public int getGemLevel() {
		return mLevel;
	}
	
	public String getAttribute() {		
		return mAttribute;
	}

	public static class Item extends JsonObjectBase {
		private String id, name;
		public Item(JSONObject jsonData) {
			super(jsonData);
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}
