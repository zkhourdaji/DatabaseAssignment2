/*
 * Zafer Khourdaji
 * EID: E01251928
 * URL: https://people.emich.edu/zkhourda/COSC471/Homework2.zip
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Database {
	
	private int recordsInserted;
	private File file;
	
	/*
	 * constructor takes a file name and checks if the file already exist,
	 * if it doesnt, it will create a new file and a meta.txt file
	 * the meta.txt file is simply used to store the number of records in the database
	 */
	public Database(String fileName) {
		file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
				recordsInserted = 0;
				File meta = new File("meta.txt");
				meta.createNewFile();
				this.updateNumberOfRecords();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			int numOfRecords =  getNumberOfRecords();
			if (recordsInserted == -1) {
				System.out.println("CANT READ NUMBER OF RECORDS FROM META.TXT EXITING...");
				System.exit(1);
			}
			else {
				this.recordsInserted = numOfRecords;
			}
		}
	}
	
	/*
	 * this method reads the number of records from the meta.txt file and returns it
	 */
	public int getNumberOfRecords() {
		try {
			Scanner s = new Scanner(new File("meta.txt"));
			int numOfRecords =  s.nextInt();
			s.close();
			return numOfRecords;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/*
	 * this private method is used to update the number of records inside the meta.txt file
	 */
	private void updateNumberOfRecords() {
		try {
			PrintWriter writer = new PrintWriter(new File("meta.txt"));
			writer.print(this.recordsInserted);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * this method will return a record from the database with the index i
	 */
	public Record getRecord(int index) {
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			raf.seek(index * 16);
			int intField = raf.readInt();
			double doubleField = raf.readDouble();
			char charField = raf.readChar();
			return new Record(intField, doubleField, charField);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * this method will simply set the number of records to 0
	 * so next insertions will override old data, in effect deleting all old records.
	 */
	public void deleteAllRecords() {
		recordsInserted = 0;
		updateNumberOfRecords();
		
	}
	
	/*
	 * this method takes a record object and inserts it to the 
	 * database after previously inserted records
	 */
	public void insert(Record record) {
		
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			// off set is 16 * number of records because each record is 16 bytes long
			raf.seek(recordsInserted * 16);
			raf.writeInt(record.getIntField());
			raf.writeDouble(record.getDoubleField());
			raf.writeChar(record.getCharField());
			raf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.recordsInserted ++;
		this.updateNumberOfRecords();
	}
	
	/*
	 * this method finds a record by the int field which is used as the primary key
	 * it will return null if it didnt find a record with that id
	 */
	public Record findRecordByIntField(int intField) {

			for (int i = 0; i < this.recordsInserted; i++) {
				Record r = this.getRecord(i);
				if (r.getIntField() == intField) {
					return r;
				}
			}
		return null;
		
	}
	
}
