package data;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

import business.Person;

/**
 * 
 * @name KUORUI, CHIANG; N01538270
 *
 */
public class RandomIO {
	public static final int RECORD_NUMBER_SIZE = 4; // integer type with max length 3, plus 1 byte for sign
	public static final int FIRST_NAME_SIZE = 20; // max size 20 characters
	public static final int LAST_NAME_SIZE = 25; // max size 25 characters
	public static final int PHONE_SIZE = 10; // max size 10 characters
	public static final int AGE_SIZE = 4; // integer type with max length 3, plus 1 byte for sign
	public static final int RECORD_SIZE = RECORD_NUMBER_SIZE + FIRST_NAME_SIZE + LAST_NAME_SIZE + PHONE_SIZE + AGE_SIZE;

	public static void addPersonToFile(Person person, String filename) throws IOException {
		
		try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
			// seek to end of file appendData
			file.seek(file.length());

			long startPoint = file.getFilePointer();
			System.out.println("pointer of start = " + startPoint);
			// write fields to file
			writeString(file, person.getRecordNumber(), RECORD_NUMBER_SIZE);
			writeString(file, person.getFirstName(), FIRST_NAME_SIZE);
			writeString(file, person.getLastName(), LAST_NAME_SIZE);
			writeString(file, person.getPhone(), PHONE_SIZE);
			// AGE
			file.writeInt(person.getAge());
			long endPoint = file.getFilePointer();
			System.out.println("pointer of end = " + endPoint);
			System.out.println("this row length = " + (endPoint - startPoint));

		}
	}

	public static Person findPersonByRecordNumber(String recordNumber, String filename) throws IOException {
		try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
			// calculate the number of records in the file
			System.out.println("FILELENGTH==" + file.length());
			long count = file.length() / RECORD_SIZE;
			System.out.println("count of row ==" + count);

			// iterate over each record in the file
			for (int i = 1; i <= count; i++) {
				// calculate the position of the record in the file
				long position = (i - 1) * RECORD_SIZE;
				if (position >= 0) {
					// move the file pointer to the position of the record
					file.seek(position);
					System.out.println("position==" + position);
					position++;
					String recordNum = readString(file, RECORD_NUMBER_SIZE);
					System.out.println("recordNum==" + recordNum);
					if (recordNumber.equals(recordNum)) {
						// check if the current record matches the record number we're looking for
						String firstName = readString(file, FIRST_NAME_SIZE);
						String lastName = readString(file, LAST_NAME_SIZE);
						String phone = readString(file, PHONE_SIZE);
						int age = file.readInt();
						return new Person(recordNum, firstName, lastName, phone, age);
					}

				}

			}
		}
		// the record was not found in the file
		return null;
	}

	private static void writeString(RandomAccessFile file, String str, int size) throws IOException {
		// write the string to the file as UTF-8 encoded bytes
		byte[] strByte = str.getBytes();
		file.write(strByte);
//		 pad the remaining bytes with zeros
		int remainingBytes = size - strByte.length;
		for (int i = 0; i < remainingBytes; i++) {
			file.writeByte(0);
		}

		System.out.println("size======" + size);
		System.out.println("remainingBytes======" + remainingBytes);
		System.out.println("strByte.length======" + strByte.length);
	}

	private static String readString(RandomAccessFile file, int size) throws IOException {
		byte[] bytes = new byte[size];
		file.readFully(bytes);
		return new String(bytes, StandardCharsets.UTF_8).trim();
	}
}