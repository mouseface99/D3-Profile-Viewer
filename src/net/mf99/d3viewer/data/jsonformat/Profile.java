package net.mf99.d3viewer.data.jsonformat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import net.mf99.d3viewer.Const;
import net.mf99.d3viewer.Const.SERVER_REGION;

public class Profile extends JsonObjectBase {
	private String battleTag;
	private SERVER_REGION mServerRegion;
	private List<HeroShort> heroes;
	private int paragonLevel;	
	private Kills kills;
	
	public Profile(JSONObject jsonData, SERVER_REGION region) {
		super(jsonData);
		mServerRegion = region;
		Const.PARAGON_LEVEL = getParagonLevel();
	}
	
	public String getBattleTag() {
		return battleTag;
	}

	public void setBattleTag(String battleTag) {
		this.battleTag = battleTag;
	}

	public SERVER_REGION getmServerRegion() {
		return mServerRegion;
	}

	public void setmServerRegion(SERVER_REGION mServerRegion) {
		this.mServerRegion = mServerRegion;
	}

	public List<HeroShort> getHeroes() {
		if(heroes == null)
			heroes = new ArrayList<HeroShort>();
		return heroes;
	}

	public void setHeroes(List<HeroShort> heroes) {
		this.heroes = heroes;
	}

	public int getParagonLevel() {
		return paragonLevel;
	}

	public void setParagonLevel(int paragonLevel) {
		this.paragonLevel = paragonLevel;
	}

	public Kills getKills() {
		return kills;
	}

	public void setKills(Kills kills) {
		this.kills = kills;
	}
	
	public static class Kills extends JsonObjectBase{
		private long monsters, elites;
		public Kills(JSONObject jsonData) {
			super(jsonData);
		}
		public long getMonsters() {
			return monsters;
		}
		public void setMonsters(long monsters) {
			this.monsters = monsters;
		}
		public long getElites() {
			return elites;
		}
		public void setElites(long elites) {
			this.elites = elites;
		}
	}
}
