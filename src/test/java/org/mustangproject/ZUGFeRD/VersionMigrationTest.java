/** **********************************************************************
 *
 * Use is subject to license terms.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *********************************************************************** */
package org.mustangproject.ZUGFeRD;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Every ZUGFeRD 1.0 XML file from <code>src/test/resources/migration/input</code>
 * will be migrated to <code>src/test/resources/migration/output</code>.
 * If there is a similar named file as test reference <code>src/test/resources/migration/reference</code> a test
 * will be triggered for every single file.
 * <p/>
 * Note: Any 'ZUGFeRD1' will be exchanged to 'ZUGFeRD2' during migration and adequate reference will be searched for.
 */
@RunWith(Parameterized.class)
public class VersionMigrationTest {

	private static final Logger LOG = Logger.getLogger(VersionMigrationTest.class.getName());

	private static final String INPUT_DIR = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "migration" + File.separator + "input" + File.separator;
	private static final String REFERENCE_DIR = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "migration" + File.separator + "reference" + File.separator;
	private static final String OUTPUT_DIR = "target" + File.separator + "test-classes" + File.separator + "migration" + File.separator + "output" + File.separator;
	private File mTestFile = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Creating the output directory for the tests
		new File(OUTPUT_DIR).mkdirs();
	}

	public VersionMigrationTest(File testFile) {
		mTestFile = testFile;
	}

	@Parameterized.Parameters(name = "Test# {index}: {0}")
	public static Collection<Object[]> data() {
		Collection<Object[]> testSuiteData = new ArrayList<Object[]>();
		addFilesFromFolder(new File(INPUT_DIR), testSuiteData);
		return testSuiteData;
	}

	private static void addFilesFromFolder(final File folder, Collection<Object[]> testSuiteData) {
		String filePath = null;
		for (final File fileEntry : folder.listFiles()) {
			filePath = fileEntry.getAbsolutePath();
			if (fileEntry.isDirectory()) {
				LOG.log(Level.INFO, "*** testDirectory:{0}", filePath);
				addFilesFromFolder(fileEntry, testSuiteData);
			} else {
				LOG.log(Level.INFO, "*** testFile: {0}", filePath);
				Object[] testData = new Object[]{fileEntry};
				testSuiteData.add(testData);
			}
		}
	}

	@Test
	/**
	 * ZUGFeRD 1.0 to 2.0 migration test.
	 * For more information see class description. */
	public void testFile() {
		testMigration(mTestFile.getName());
	}

	private void testMigration(String fileName) {
		try {
			String tmp = new ZUGFeRDMigrator().migrateFromV1ToV2(INPUT_DIR + fileName);
			String newName = fileName.replace("ZUGFeRD1", "ZUGFeRD2");
			ResourceUtilities.saveFile(StandardCharsets.UTF_8, OUTPUT_DIR + newName, tmp);
			if (new File(REFERENCE_DIR + newName).exists()) {
				// we need to save the string and reload it otherwise two bytes are missing (likely EOF related)
				String outXML = ResourceUtilities.readFile(StandardCharsets.UTF_8, OUTPUT_DIR + newName);
				String refXML = ResourceUtilities.readFile(StandardCharsets.UTF_8, REFERENCE_DIR + newName);
				int t = outXML.length();
				int r = refXML.length();
				if (t != r || !(outXML.equals(refXML))) {
					LOG.info("There are differences between:"
							+ "\nZUGFeRD 2.0 Test Output @ " + OUTPUT_DIR + newName
							+ "\nZUGFeRD 2.0 Test Reference @ " + REFERENCE_DIR + newName);
					LOG.info("\n\nFile sizes:\n "
							+ "\nZUGFeRD 2.0 Test Output character count: " + t
							+ "\nZUGFeRD 2.0 Test Refer. character count: " + r);
					findLineDiffs(outXML, refXML);
					Assert.fail("Version update failed, as test result and reference are different!");
				} else {
					LOG.log(Level.INFO, "\n*** Tested successfull migration from ZUGFeRD 1.0 to 2.0: '" + newName + "'' ***\n", OUTPUT_DIR);
				}
			} else {
				LOG.log(Level.INFO, "\n*** Migrated from ZUGFeRD 1.0 to 2.0 invoice: '" + newName + "'' ***\n", OUTPUT_DIR);
			}
		} catch (IOException | TransformerException t) {
			LOG.log(Level.SEVERE, t.getMessage(), t);
			Assert.fail("Failed with " + t.getClass().getName() + ": '" + t.getMessage() + "'");
		}
	}


	private static void findLineDiffs(String outXML, String refXML) {
		Scanner outScanner = new Scanner(outXML);
		Scanner refScanner = new Scanner(refXML);
		String outLine = null;
		String refLine = null;
		while (outScanner.hasNextLine() && refScanner.hasNextLine()) {
			outLine = outScanner.nextLine();
			refLine = refScanner.nextLine();
			if (!outLine.equals(refLine)) {
				LOG.info("First line difference between reference and output file:" +
						"\nRefLine: " + refLine +
						"\nOutLine: " + outLine + "\n");
			}
		}
		refScanner.close();
		outScanner.close();
	}
}
