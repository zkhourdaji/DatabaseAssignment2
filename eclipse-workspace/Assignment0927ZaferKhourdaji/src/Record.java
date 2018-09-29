/*
 * Zafer Khourdaji
 * EID: E01251928
 * URL: https://people.emich.edu/zkhourda/COSC471/Homework2.zip
 */
public class Record {
	
	private int intField;
	private double doubleField;
	private char charField;
	
	public Record(int intField, double doubleField, char charField) {
		this.intField = intField;
		this.doubleField = doubleField;
		this.charField = charField;
	}

	public char getCharField() {
		return charField;
	}
	
	public double getDoubleField() {
		return doubleField;
	}
	
	public int getIntField() {
		return intField;
	}
	
	@Override
	public String toString() {
		return 	getIntField() + " " + getDoubleField() + " " + getCharField();

	}
}
