package odwaServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class responsible for parsing XML files containing metastructure of database;
 * used to display form and view of datastructure
 * 
 * @author Necklon
 * 
 */
public class MetaXmlParserServlet {

	ArrayList<String> measures = new ArrayList<String>();
	String databaseId = new String();

	public String parse(String xmlString) {

		String result = "";

		String result1 = "<ul id=\"tree0\" class=\"tree\">\n";
		Node root;
		try {
			root = loadXMLFrom(xmlString).getDocumentElement();

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
						NodeList measuresChildren = rootChild.getChildNodes();
						for (int j = 0; j < measuresChildren.getLength(); j++) {
							Node measuresChild = measuresChildren.item(j);
							if (measuresChild.getNodeName().equals("measure")) {
								String name = getAttribute("name",
										measuresChild);
								String uid = getAttribute("uid", measuresChild);

								measures.add(name + ":" + uid);
							}
						}

					} else if (rootChild.getNodeName().equals("dimensions")) {
						result += "DIMENSIONS<br>";
						result1 += "<li> \n<a> Dimensions:</a>\n<ul>";
						NodeList dimensionsChildren = rootChild.getChildNodes();
						for (int j = 0; j < dimensionsChildren.getLength(); j++) {
							Node dimensionsChild = dimensionsChildren.item(j);
							if (dimensionsChild.getNodeName().equals(
									"dimension")) {
								String name = getAttribute("name",
										dimensionsChild);
								String uid = getAttribute("uid",
										dimensionsChild);

								result += "____" + name + " " + uid + "<br>";
								result1 += "\n<li><a>" + name + "</a>(" + uid
										+ ")\n<ul>";

								NodeList attributes = dimensionsChild
										.getChildNodes();

								for (int k = 0; k < attributes.getLength(); k++) {
									if (attributes.item(k).getNodeName()
											.equals("hierarchies")) {
										NodeList hierarchiesChildren = attributes
												.item(k).getChildNodes();
										for (int l = 0; l < hierarchiesChildren
												.getLength(); l++) {
											Node hierChild = hierarchiesChildren
													.item(l);
											if (hierChild.getNodeName().equals(
													"hierarchy")) {
												String hierName = getAttribute(
														"name", hierChild);
												String hierUid = getAttribute(
														"uid", hierChild);

												result += "_______" + hierName
														+ " " + hierUid
														+ "<br>";
												result1 += "\n<li><a>"
														+ hierName + "(</a>"
														+ hierUid + ")\n<ul>";
												for (int m = 0; m < hierChild
														.getChildNodes()
														.getLength(); m++) {
													Node hlevels = hierChild
															.getChildNodes()
															.item(m);
													if (hlevels.getNodeName()
															.equals("levels")) {
														for (int n = 0; n < hlevels
																.getChildNodes()
																.getLength(); n++) {
															Node hlevel = hlevels
																	.getChildNodes()
																	.item(n);
															String levName = getAttribute(
																	"name",
																	hlevel);
															String levUid = getAttribute(
																	"uid",
																	hlevel);

															result += "_______"
																	+ levName
																	+ " "
																	+ levUid
																	+ "<br>";
															result1 += "\n<li><a>"
																	+ levName
																	+ "</a>("
																	+ levUid
																	+ ")";

															for (int o = 0; o < hlevel
																	.getChildNodes()
																	.getLength(); o++) {
																Node lmembers = hlevel
																		.getChildNodes()
																		.item(o);
																if (lmembers
																		.getNodeName()
																		.equals(
																				"members")) {
																	for (int p = 0; p < lmembers
																			.getChildNodes()
																			.getLength(); p++) {
																		Node member = lmembers
																				.getChildNodes()
																				.item(
																						p);

																		String mbr = memberWithChildren(
																				member,
																				12);// new
																		// TreeNode(mbrName);
																		result += mbr;
																		result1 += mbr;

																	}
																}
															}
															result1 += "\n</li>";

														}
													}
												}
												result1 += "\n</ul>\n</li>";

											}
										}
									}

								}
								result1 += "</ul></li>\n";
							}
						}
						result1 += "</ul>\n</li>\n</ul><script type=\"text/javascript\">// <![CDATA[\nnew Tree(\"tree0\");\n// ]]></script>";
					}
				}
			} else
				System.out.println("Could not load view!");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result1;
	}

	private String memberWithChildren(Node node, int spaces) {
		String result = "";
		String result1 = "";

		NodeList firstLevel = node.getChildNodes();
		String name = getAttribute("name", node);
		String uid = getAttribute("uid", node);
		for (int i = 0; i < spaces; i++)
			result += "_";

		result += name + " " + uid + "<br>";
		if (true) //jesli wêze³ ma dzieci dodajemy to:
			result1 += "\n<ul>\n<li><a>" + name + "</a>(" + uid + ")";
		else //a jesli jest ju¿ najni¿szym membersem, to:
			result1 += "\n<li><a>" + name + "</a>(" + uid + ")</li>";
		for (int i = 0; i < firstLevel.getLength(); i++) {
			if (firstLevel.item(i).getNodeName().equals("children")) {
				NodeList childrenList = firstLevel.item(i).getChildNodes();
				for (int j = 0; j < childrenList.getLength(); j++) {
					if (childrenList.item(j).getNodeName().equals("child")) {

						String child = memberWithChildren(childrenList.item(j),
								spaces);
						result += child;
						result1 += child;
					}
				}
			}
		}
		if (true) //dodajemy tylko, jesli weze³ ma dzieci...
			result1 += "\n</ul></li>";

		return result1;
	}

	private String getAttribute(String name, Node node) {
		NodeList attributes = node.getChildNodes();
		String retStr = "";
		for (int m = 0; m < attributes.getLength(); m++) {
			if (attributes.item(m).getNodeName().equals(name))
				retStr = attributes.item(m).getFirstChild().getNodeValue();

		}
		return retStr;
	}

	public static org.w3c.dom.Document loadXMLFrom(String xml)
			throws org.xml.sax.SAXException, java.io.IOException {
		return loadXMLFrom(new java.io.ByteArrayInputStream(xml.getBytes()));
	}

	public static org.w3c.dom.Document loadXMLFrom(java.io.InputStream is)
			throws org.xml.sax.SAXException, java.io.IOException {
		javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory
				.newInstance();
		factory.setNamespaceAware(true);
		javax.xml.parsers.DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (javax.xml.parsers.ParserConfigurationException ex) {
		}
		org.w3c.dom.Document doc = builder.parse(is);
		is.close();
		return doc;
	}

}
