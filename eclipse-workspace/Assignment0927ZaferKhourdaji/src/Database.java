
/*
 * Zafer Khourdaji
 * EID: E01251928
 * URL: https://people.emich.edu/zkhourda/COSC471/Homework2.zip
 */

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Database {

	private File file;

	/*
	 * constructor takes a file name and checks if the file already exist, if it
	 * doesnt
	 */
	public Database(String fileName) {
		file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * this method will return a record from the database with the index i if such
	 * record doesnt exist, this method will return null
	 */
	public Record getRecord(int index) {
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			raf.seek(index * 14);
			int intField = raf.readInt();
			double doubleField = raf.readDouble();
			char charField = raf.readChar();
			return new Record(intField, doubleField, charField);
		} catch (EOFException e) {
			return null;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public void deleteAllRecords() {
		file.delete();

	}

	// this method will return the number of records in the database
	public int getNumberOfRecords() {
		int numberOfRecords = 0;

		try {
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			do {
				if (getRecord(numberOfRecords) != null)
					numberOfRecords++;
				else
					break;

			} while (true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numberOfRecords;

	}

	/*
	 * this method takes a record object and inserts it to the database after
	 * previously inserted records
	 */
	public void insert(Record record) {

		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(raf.length());
			raf.writeInt(record.getIntField());
			raf.writeDouble(record.getDoubleField());
			raf.writeChar(record.getCharField());
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * this method finds a record by the int field which is used as the primary key
	 * it will return null if it didnt find a record with that id
	 */
	public Record findRecordByIntField(int intField) {
		int i = 0;

		do {
			Record r = this.getRecord(i);
			if (r == null)
				break;

			else if (r.getIntField() == intField) {
				return r;
			} else {
				i++;
			}
		} while (true);
		return null;

	}

}
