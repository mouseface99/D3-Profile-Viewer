package net.mf99.d3viewer.data.jsonformat;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import net.mf99.d3viewer.Const.HERO_CLASS;
import net.mf99.d3viewer.Utils;

public class Hero extends JsonObjectBase {
	private String name;
	private long id;
	private int level;
	private int gender;
	private String _class;
	private HERO_CLASS mClass;
	private Skills skills;
	private Stats stats;
	private Progression progression;
	private EquipList items;
	
	public Hero(JSONObject jsonData) {
		super(jsonData);		
		stats.setLevel(level);
	}
	
	public static class Skills extends JsonObjectBase{
		private List<Skill> active;		
		private List<Skill> passive;
		public Skills(JSONObject jsonData) {
			super(jsonData);
		}
		public List<Skill> getActive() {
			if(active == null)
				active = new ArrayList<Skill>();
			return active;
		}
		public void setActive(List<Skill> active) {
			this.active = active;
		}
		public List<Skill> getPassive() {
			if(passive == null)
				passive = new ArrayList<Skill>();
			return passive;
		}
		public void setPassive(List<Skill> passive) {
			this.passive = passive;
		}
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public HERO_CLASS getHeroClass() {
		if(mClass == null)
			mClass = Utils.getHeroClass(_class);
		return mClass;
	}

	public Skills getSkills() {
		return skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
	}

	public Stats getStats() {
		return stats;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public Progression getProgression() {
		return progression;
	}

	public void setProgression(Progression progression) {
		this.progression = progression;
	}

	public EquipList getItems() {
		return items;
	}

	public void setItems(EquipList items) {
		this.items = items;
	}

	public static class Progression extends JsonObjectBase{
		private ACT act1, act2, act3, act4, act5;
		private int current = -1;
		public Progression(JSONObject jsonData) {
			super(jsonData);
		}
		public ACT getAct1() {
			return act1;
		}
		public void setAct1(ACT act1) {
			this.act1 = act1;
		}
		public ACT getAct2() {
			return act2;
		}
		public void setAct2(ACT act2) {
			this.act2 = act2;
		}
		public ACT getAct3() {
			return act3;
		}
		public void setAct3(ACT act3) {
			this.act3 = act3;
		}
		public ACT getAct4() {
			return act4;
		}
		public void setAct4(ACT act4) {
			this.act4 = act4;
		}
		public ACT getAct5() {
			return act5;
		}
		public void setAct5(ACT act5) {
			this.act5 = act5;
		}
		
		public int getCurrent(){
			if(current == -1){
				if	   (act5.isCompleted())	current = 5;
				else if(act4.isCompleted()) current = 4;
				else if(act3.isCompleted()) current = 3;
				else if(act2.isCompleted()) current = 2;
				else if(act1.isCompleted()) current = 1;
				else 						current = 0;
			}
			
			return current;
		}

		public static class ACT extends JsonObjectBase{
			private boolean completed;
			public ACT(JSONObject jsonData) {
				super(jsonData);
			}
			public boolean isCompleted() {
				return completed;
			}
			public void setCompleted(boolean completed) {
				this.completed = completed;
			}
		}
		
	}
	
    public static class Stats extends JsonObjectBase{
        private int level;        
        private long life, armor;
        private double damage, attackSpeed;        
        private int strength, dexterity, vitality, intelligence;        
        private int physicalResist, fireResist, coldResist, lightningResist, poisonResist, arcaneResist;        
        private double critDamage, critChance;        
        private double blockChance, blockAmountMin, blockAmountMax, thorns;        
        private double damageIncrease, damageReduction;        
        private double lifeSteal, lifePerKill, lifeOnHit;        
        private double goldFind, magicFind;        
        private int primaryResource, secondaryResource;        
        
        public Stats(JSONObject jsonData) {
    		super(jsonData);
    	}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public long getLife() {
			return life;
		}

		public void setLife(long life) {
			this.life = life;
		}

		public long getArmor() {
			return armor;
		}

		public void setArmor(long armor) {
			this.armor = armor;
		}

		public double getDamage() {
			return damage;
		}

		public void setDamage(double damage) {
			this.damage = damage;
		}

		public double getAttackSpeed() {
			return attackSpeed;
		}

		public void setAttackSpeed(double attackSpeed) {
			this.attackSpeed = attackSpeed;
		}

		public int getStrength() {
			return strength;
		}

		public void setStrength(int strength) {
			this.strength = strength;
		}

		public int getDexterity() {
			return dexterity;
		}

		public void setDexterity(int dexterity) {
			this.dexterity = dexterity;
		}

		public int getVitality() {
			return vitality;
		}

		public void setVitality(int vitality) {
			this.vitality = vitality;
		}

		public int getIntelligence() {
			return intelligence;
		}

		public void setIntelligence(int intelligence) {
			this.intelligence = intelligence;
		}

		public int getPhysicalResist() {
			return physicalResist;
		}

		public void setPhysicalResist(int physicalResist) {
			this.physicalResist = physicalResist;
		}

		public int getFireResist() {
			return fireResist;
		}

		public void setFireResist(int fireResist) {
			this.fireResist = fireResist;
		}

		public int getColdResist() {
			return coldResist;
		}

		public void setColdResist(int coldResist) {
			this.coldResist = coldResist;
		}

		public int getLightningResist() {
			return lightningResist;
		}

		public void setLightningResist(int lightningResist) {
			this.lightningResist = lightningResist;
		}

		public int getPoisonResist() {
			return poisonResist;
		}

		public void setPoisonResist(int poisonResist) {
			this.poisonResist = poisonResist;
		}

		public int getArcaneResist() {
			return arcaneResist;
		}

		public void setArcaneResist(int arcaneResist) {
			this.arcaneResist = arcaneResist;
		}

		public double getCritDamage() {
			return critDamage * 100;
		}

		public void setCritDamage(double critDamage) {
			this.critDamage = critDamage;
		}

		public double getCritChance() {
			return critChance * 100;
		}

		public void setCritChance(double critChance) {
			this.critChance = critChance;
		}

		public double getBlockChance() {
			return blockChance;
		}

		public void setBlockChance(double blockChance) {
			this.blockChance = blockChance;
		}

		public double getBlockAmountMin() {
			return blockAmountMin;
		}

		public void setBlockAmountMin(double blockAmountMin) {
			this.blockAmountMin = blockAmountMin;
		}

		public double getBlockAmountMax() {
			return blockAmountMax;
		}

		public void setBlockAmountMax(double blockAmountMax) {
			this.blockAmountMax = blockAmountMax;
		}

		public double getThorns() {
			return thorns;
		}

		public void setThorns(double thorns) {
			this.thorns = thorns;
		}

		public double getDamageIncrease() {
			return damageIncrease;
		}

		public void setDamageIncrease(double damageIncrease) {
			this.damageIncrease = damageIncrease;
		}

		public double getDamageReduction() {
			return damageReduction;
		}

		public void setDamageReduction(double damageReduction) {
			this.damageReduction = damageReduction;
		}

		public double getLifeSteal() {
			return lifeSteal;
		}

		public void setLifeSteal(double lifeSteal) {
			this.lifeSteal = lifeSteal;
		}

		public double getLifePerKill() {
			return lifePerKill;
		}

		public void setLifePerKill(double lifePerKill) {
			this.lifePerKill = lifePerKill;
		}

		public double getLifeOnHit() {
			return lifeOnHit;
		}

		public void setLifeOnHit(double lifeOnHit) {
			this.lifeOnHit = lifeOnHit;
		}

		public double getGoldFind() {
			return goldFind * 100;
		}

		public void setGoldFind(double goldFind) {
			this.goldFind = goldFind;
		}

		public double getMagicFind() {
			return magicFind * 100;
		}

		public void setMagicFind(double magicFind) {
			this.magicFind = magicFind;
		}

		public int getPrimaryResource() {
			return primaryResource;
		}

		public void setPrimaryResource(int primaryResource) {
			this.primaryResource = primaryResource;
		}

		public int getSecondaryResource() {
			return secondaryResource;
		}

		public void setSecondaryResource(int secondaryResource) {
			this.secondaryResource = secondaryResource;
		}        
    }

}
