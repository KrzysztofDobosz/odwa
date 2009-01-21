package odwaServlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BasicDom {

	ArrayList<String> measures = new ArrayList<String>();
	ArrayList<String> dimensions = new ArrayList<String>();
	String databaseId = new String();

	public void start(String fileName) {
		Node root = parseXmlFile(fileName, false).getDocumentElement();

		// System.out.println(root.getNodeName() + " " +
		// root.getNodeValue());

		if (root.getNodeName().equals("metadata")) {

			NodeList rootChildren = root.getChildNodes();
			for (int i = 0; i < rootChildren.getLength(); i++) {
				Node rootChild = rootChildren.item(i);
				if (rootChild.getNodeName().equals("database")) {
					NodeList attributesDb = rootChild.getChildNodes();
					for (int k = 0; k < attributesDb.getLength(); k++) {
						if (attributesDb.item(k).getNodeName().equals(
								"name"))
							databaseId = attributesDb.item(k)
									.getFirstChild().getNodeValue();

					}
				}
				if (rootChild.getNodeName().equals("measures")) {
					// System.out.println("\tMeasures:");

					NodeList measuresChildren = rootChild.getChildNodes();
					for (int j = 0; j < measuresChildren.getLength(); j++) {
						Node measuresChild = measuresChildren.item(j);
						if (measuresChild.getNodeName().equals("measure")) {
							String name = "";
							String uid = "";
							NodeList attributes = measuresChild
									.getChildNodes();
							for (int k = 0; k < attributes.getLength(); k++) {
								if (attributes.item(k).getNodeName()
										.equals("name"))
									name = attributes.item(k)
											.getFirstChild().getNodeValue();

								else if (attributes.item(k).getNodeName()
										.equals("uid"))
									uid = attributes.item(k)
											.getFirstChild().getNodeValue();
							}

							// System.out.println(name + " " + uid);

							measures.add(name + ":" + uid);

						}
					}

				} else if (rootChild.getNodeName().equals("dimensions")) {

					NodeList dimensionsChildren = rootChild.getChildNodes();
					// System.out.println("\tDimensions:");

					for (int j = 0; j < dimensionsChildren.getLength(); j++) {
						Node dimensionsChild = dimensionsChildren.item(j);
						if (dimensionsChild.getNodeName().equals(
								"dimension")) {
							String name = "";
							String uid = "";
							NodeList attributes = dimensionsChild
									.getChildNodes();
							for (int k = 0; k < attributes.getLength(); k++) {
								if (attributes.item(k).getNodeName()
										.equals("name"))
									name = attributes.item(k)
											.getFirstChild().getNodeValue();

								else if (attributes.item(k).getNodeName()
										.equals("uid"))
									uid = attributes.item(k)
											.getFirstChild().getNodeValue();
							}
							// System.out.println(name + " " + uid);

							dimensions.add(name + ":" + uid);
						}
					}

				}
			}
		} else {
			System.out.println("Nie mo¿na za³adowac widoku");
		}

	}

	// Parses an XML file and returns a DOM document.
	// If validating is true, the contents is validated against the DTD
	// specified in the file.
	public Document parseXmlFile(String filename, boolean validating) {
		try {
			// Create a builder factory
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(validating);

			// Create the builder and parse the file
			Document doc = factory.newDocumentBuilder().parse(
					new File(filename));
			return doc;
		} catch (SAXException e) {
			System.out.println(e.getMessage());
			// A parsing error occurred; the xml input is not valid
		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}