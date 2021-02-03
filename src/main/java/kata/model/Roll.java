package kata.model;

public class Roll {

	private int dice1;
	private int dice2;
	private int dice3;
	private int dice4;
	private int dice5;

	public Roll(int dice1, int dice2, int dice3, int dice4, int dice5) {
		if (isValideValue(dice1) && isValideValue(dice2) && isValideValue(dice3) && isValideValue(dice4)
				&& isValideValue(dice5)) {
			this.dice1 = dice1;
			this.dice2 = dice2;
			this.dice3 = dice3;
			this.dice4 = dice4;
			this.dice5 = dice5;
		} else {
			throw new IllegalArgumentException("Roll should have value between 1 and 6");
		}
	}

	public int getDice1() {
		return dice1;
	}

	public void setDice1(int dice1) {
		if(isValideValue(dice1)) {
			this.dice1 = dice1;
		} else {
			throw new IllegalArgumentException("Roll should have value between 1 and 6");
		}
	}

	public int getDice2() {
		return dice2;
	}

	public void setDice2(int dice2) {
		if(isValideValue(dice2)) {
			this.dice2 = dice2;
		} else {
			throw new IllegalArgumentException("Roll should have value between 1 and 6");
		}
	}

	public int getDice3() {
		return dice3;
	}

	public void setDice3(int dice3) {
		if(isValideValue(dice3)) {
			this.dice3 = dice3;
		} else {
			throw new IllegalArgumentException("Roll should have value between 1 and 6");
		}
		this.dice3 = dice3;
	}

	public int getDice4() {
		return dice4;
	}

	public void setDice4(int dice4) {
		if(isValideValue(dice4)) {
			this.dice4 = dice4;
		} else {
			throw new IllegalArgumentException("Roll should have value between 1 and 6");
		}
	}

	public int getDice5() {
		return dice5;
	}

	public void setDice5(int dice5) {
		if(isValideValue(dice5)) {
			this.dice5 = dice5;
		} else {
			throw new IllegalArgumentException("Roll should have value between 1 and 6");
		}
	}

	private boolean isValideValue(int value) {
		return value >= 1 && value <= 6;
	}

}
