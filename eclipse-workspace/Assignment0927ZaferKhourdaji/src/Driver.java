/*
 * Zafer Khourdaji
 * EID: E01251928
 * URL: https://people.emich.edu/zkhourda/COSC471/Homework2.zip
 */
public class Driver {

	public static void main(String[] args) {

		// create records to populate the database with
		Record r1 = new Record(1, 1.0, 'a');
		Record r2 = new Record(2, 2.0, 'b');
		Record r3 = new Record(3, 3.0, 'c');
		Record r4 = new Record(4, 4.0, 'd');
		Record r5 = new Record(5, 5.0, 'e');
		Record r6 = new Record(6, 6.0, 'f');

		// create a database with the file database.txt
		Database db = new Database("database.txt");
		// start with a fresh database by deleting all previous records
		db.deleteAllRecords();
		// insert the records
		db.insert(r1);
		db.insert(r2);
		db.insert(r3);
		db.insert(r4);
		db.insert(r5);
		db.insert(r6);

		System.out.println("Displaying all records: ");
		// print out all the records in the database
		for (int i = 0; i < db.getNumberOfRecords(); i++) {
			System.out.println(db.getRecord(i));
		}
		System.out.println("--------------------------------------------");

		System.out.println("Displaying the double field values for the records: ");
		// print out double values of all records in the database
		for (int i = 0; i < db.getNumberOfRecords(); i++) {
			System.out.println(db.getRecord(i).getDoubleField() + "");
		}

		System.out.println("--------------------------------------------");

		System.out.println("Locating the record with int value 4: ");
		// finds and prints the record with ID 4
		Record r = db.findRecordByIntField(4);
		System.out.println(r);
		System.out.println("--------------------------------------------");

		// has to be between 2 and 5 inclusive
		int intValue = 5;

		System.out.println(
				"Locating records with int value less than or equal to " + intValue + " and printing their char values");
		// output charField of all recorders with intField <= intValue
		for (int i = 0; i < db.getNumberOfRecords(); i++) {
			Record re = db.getRecord(i);
			if (re.getIntField() <= intValue) {
				System.out.println(re.getCharField() + "");
			}

		}

	}

}
