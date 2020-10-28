/** **********************************************************************
 *
 * Copyright 2018 Jochen Staerk
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
/**
 * Mustangproject's ZUGFeRD implementation
 * ZUGFeRD exporter helper class
 * Licensed under the APLv2
 * @date 2014-05-10
 * @version 1.2.0s
 * @author jstaerk
 * */

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.XMPSchema;

public class XMPSchemaZugferd extends XMPSchema {

	/**
	 * This is what needs to be added to the RDF metadata - basically the name of the embedded Zugferd file
	 */
	public XMPSchemaZugferd(XMPMetadata metadata, ZUGFeRDConformanceLevel conformanceLevel,
			                String URN, String prefix, String filename, String versionValue) {
		super(metadata, URN, prefix, "ZUGFeRD Schema");

		setAboutAsSimple("");

		String conformanceLevelValue = conformanceLevel.name();
		if (conformanceLevelValue.equals("BASICWL")) {
			conformanceLevelValue = "BASIC WL";
		} else if (conformanceLevelValue.equals("EN16931")) {
			conformanceLevelValue = "EN 16931";
		}
		setTextPropertyValue("ConformanceLevel", conformanceLevelValue);
		setTextPropertyValue("DocumentType", "INVOICE");
		setTextPropertyValue("DocumentFileName", filename);
		setTextPropertyValue("Version", versionValue);
	}
}
