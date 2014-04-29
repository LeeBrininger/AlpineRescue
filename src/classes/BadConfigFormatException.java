package classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SuppressWarnings("serial")
public class BadConfigFormatException extends RuntimeException {
	public BadConfigFormatException() {
		String fileName = "logfile.txt";

		File file = new File(fileName);
		try {
			// if file doesn't exist, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw;

			fw = new FileWriter(file.getAbsoluteFile());

			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public String toString() {
		return "There was an error in the configuration!";

	}

}