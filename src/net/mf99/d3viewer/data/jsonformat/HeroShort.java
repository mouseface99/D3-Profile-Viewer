package net.mf99.d3viewer.data.jsonformat;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.HERO_CLASS;

public class HeroShort extends JsonObjectBase {
	private String name;
	private long id;
	private int level;
	private HERO_CLASS _class;
	private int gender;
	
	public HeroShort(JSONObject jsonData) {
		super(jsonData);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}	

	public HERO_CLASS get_class() {
		return _class;
	}

	public void set_class(HERO_CLASS _class) {
		this._class = _class;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public boolean isMale() {
		return (gender == 0);
	}
	
}
