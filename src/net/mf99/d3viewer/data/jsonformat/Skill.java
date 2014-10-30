package net.mf99.d3viewer.data.jsonformat;

import net.mf99.d3viewer.Utils;

import org.json.JSONObject;

public class Skill extends JsonObjectBase {
	private SkillDetail skill;
	private RuneDetail rune;	
	
	public Skill(JSONObject jsonData) {
		super(jsonData);
	}
	
	public SkillDetail getSkill() {
		return skill;
	}

	public void setSkill(SkillDetail skill) {
		this.skill = skill;
	}

	public RuneDetail getRune() {
		return rune;
	}

	public void setRune(RuneDetail rune) {
		this.rune = rune;
	}
	
	public boolean hasSkill() {
		return (skill != null);
	}

	public boolean hasRune() {
		return (rune != null);
	}

	public static class SkillDetail extends JsonObjectBase{
		private String name;
		private String icon;
		private String description;
		private int level;
		
		public SkillDetail(JSONObject jsonData) {
			super(jsonData);
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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}
		
		
	}
	
	public static class RuneDetail extends JsonObjectBase{
		private String name;
		private String description;
		private String type;
		private int level;
		private int mRuneType = -1;
		
		public RuneDetail(JSONObject jsonData) {
			super(jsonData);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getRuneType() {
			if(mRuneType == -1)
				mRuneType = Utils.getRuneType(type);
			return mRuneType;
		}
		
	}
}
