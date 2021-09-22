package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Movefile {

	public void baseMoveFile() {

		File a = new File(System.getProperty("user.dir") + "\\screenshots");

		{
			try {
				if (a.isDirectory()) {
					File[] content = a.listFiles();
					System.out.println("Total files avaliable  " + content.length);
					if (content.length != 0) {
						System.out.println("File is moving from screenshots to screenshot_backup folder...... ");

						for (int i = 0; i < content.length; i++) {
							Path path = content[i].toPath();
							String pathString = path.toString();
							String[] pathSplitted = pathString.split("screenshots\\\\");
							String pathFormated = pathSplitted[1];
							// System.out.println("entry1");
							moveFile(System.getProperty("user.dir") + "\\screenshots\\" + pathFormated,
									System.getProperty("user.dir") + "\\screenshot_backup\\" + pathFormated);
						}
						System.out.println("File is moved successfully ");
						// System.out.println("entry2");
					}

					else {
						System.out.println("No file found in screenshots folder");

					}

				}
			} catch (Exception e) {

				System.out.println("File is Already exist");

			}
		}

	}

	private static void moveFile(String src, String dest) throws IOException {
		Path result = null;

		result = Files.move(Paths.get(src), Paths.get(dest));

		if (result != null) {
			// System.out.println("File moved successfully.");
		} else {
			System.out.println("File movement failed.");
		}
	}

}
