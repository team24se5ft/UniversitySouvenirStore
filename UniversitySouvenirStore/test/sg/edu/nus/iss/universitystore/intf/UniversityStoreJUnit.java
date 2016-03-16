package sg.edu.nus.iss.universitystore.intf;

import java.io.File;
import java.nio.file.Files;

import org.junit.After;
import org.junit.Before;

import sg.edu.nus.iss.universitystore.constants.JUnitConstants;
import sg.edu.nus.iss.universitystore.test.InitializeTest;

public abstract class UniversityStoreJUnit {
	
	@Before
	public void setUp() throws Exception {
		InitializeTest.getInstance();
	}
	
	@After
	public void tearDown() throws Exception {
		InitializeTest.destroyInstance();
		
		// Remove all files from test/data directory
		File testFileDir = new File(JUnitConstants.Data.TEST_FILE_PATH);
		for(File testFile : testFileDir.listFiles()){
			if(testFile.isDirectory())
				continue;
			Files.deleteIfExists(testFile.toPath());
		}
	}
}
