package net.mf99.d3viewer.data.jsonformat;

import org.json.JSONObject;

public class EquipList extends JsonObjectBase{	

	private EquipShort head,
					  torso, // Body
					  feet,
					  hands,
					  shoulders,
					  legs,
					  bracers, //Arms
					  mainHand,
					  offHand,
					  waist,
					  rightFinger,
					  leftFinger,
					  neck;
	
	public EquipList(JSONObject jsonData) {
		super(jsonData);
	}

	public EquipShort getHead() {
		return head;
	}

	public void setHead(EquipShort head) {
		this.head = head;
	}

	public EquipShort getTorso() {
		return torso;
	}

	public void setTorso(EquipShort torso) {
		this.torso = torso;
	}

	public EquipShort getFeet() {
		return feet;
	}

	public void setFeet(EquipShort feet) {
		this.feet = feet;
	}

	public EquipShort getHands() {
		return hands;
	}

	public void setHands(EquipShort hands) {
		this.hands = hands;
	}

	public EquipShort getShoulders() {
		return shoulders;
	}

	public void setShoulders(EquipShort shoulders) {
		this.shoulders = shoulders;
	}

	public EquipShort getLegs() {
		return legs;
	}

	public void setLegs(EquipShort legs) {
		this.legs = legs;
	}

	public EquipShort getBracers() {
		return bracers;
	}

	public void setBracers(EquipShort bracers) {
		this.bracers = bracers;
	}

	public EquipShort getMainHand() {
		return mainHand;
	}

	public void setMainHand(EquipShort mainHand) {
		this.mainHand = mainHand;
	}

	public EquipShort getOffHand() {
		return offHand;
	}

	public void setOffHand(EquipShort offHand) {
		this.offHand = offHand;
	}

	public EquipShort getWaist() {
		return waist;
	}

	public void setWaist(EquipShort waist) {
		this.waist = waist;
	}

	public EquipShort getRightFinger() {
		return rightFinger;
	}

	public void setRightFinger(EquipShort rightFinger) {
		this.rightFinger = rightFinger;
	}

	public EquipShort getLeftFinger() {
		return leftFinger;
	}

	public void setLeftFinger(EquipShort leftFinger) {
		this.leftFinger = leftFinger;
	}

	public EquipShort getNeck() {
		return neck;
	}

	public void setNeck(EquipShort neck) {
		this.neck = neck;
	}
	
	
}
