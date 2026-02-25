package org.mustangproject.ZUGFeRD;

import org.mustangproject.XMLTools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
<<<<<<< HEAD
import java.util.logging.Level;
import java.util.logging.Logger;
=======
import java.text.ParseException;

import org.mustangproject.XMLTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
>>>>>>> refs/remotes/origin/master

public class XRechnungImporter extends ZUGFeRDImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger (XRechnungImporter.class);

	public XRechnungImporter(byte[] rawXml) {
		super();

		try {
			setRawXML(rawXml);
			containsMeta = true;
<<<<<<< HEAD
		} catch (final IOException e) {
			Logger.getLogger(ZUGFeRDImporter.class.getName()).log(Level.SEVERE, null, e);
=======
		} catch (final IOException | ParseException e) {
			LOGGER.error ("Failed to set raw XML", e);
>>>>>>> refs/remotes/origin/master
			throw new ZUGFeRDExportException(e);
		}
	}

	public XRechnungImporter(String filename) {
		super();

		try {
			setRawXML(Files.readAllBytes(Paths.get(filename)));
			containsMeta = true;
<<<<<<< HEAD
		} catch (final IOException e) {
			Logger.getLogger(ZUGFeRDImporter.class.getName()).log(Level.SEVERE, null, e);
			throw new ZUGFeRDExportException(e);
		}

	}
	public XRechnungImporter(InputStream fileinput) {
		super();

		try {
			setRawXML(XMLTools.getBytesFromStream(fileinput));
			containsMeta = true;
		} catch (final IOException e) {
			Logger.getLogger(ZUGFeRDImporter.class.getName()).log(Level.SEVERE, null, e);
			throw new ZUGFeRDExportException(e);
		}


=======
		} catch (final IOException | ParseException e) {
      		LOGGER.error ("Failed to set raw XML", e);
			throw new ZUGFeRDExportException(e);
		}

	}
	public XRechnungImporter(InputStream fileinput) {
		super();
		try {
			setRawXML(XMLTools.getBytesFromStream(fileinput));
			containsMeta = true;
		} catch (final IOException | ParseException e) {
      		LOGGER.error ("Failed to set raw XML", e);
			throw new ZUGFeRDExportException(e);
		}
>>>>>>> refs/remotes/origin/master
	}


}
