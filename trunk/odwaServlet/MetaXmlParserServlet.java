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
/**
 * Class responsible for parsing XML files containing metastructure of database;
 * used to display form and view of datastructure
 * @author Necklon
 *
 */
public class MetaXmlParserServlet
{

	 ArrayList<String> measures = new ArrayList<String>();
	 String databaseId = new String();
	 
	public String parse(String fileName)
	{

		String result = "";
		Node root = parseXmlFile(fileName, false).getDocumentElement();

		
		if (root.getNodeName().equals("metadata"))
		{
			

			NodeList rootChildren = root.getChildNodes();
			for (int i = 0; i < rootChildren.getLength(); i++)
			{
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
				if (rootChild.getNodeName().equals("measures"))
				{
					NodeList measuresChildren = rootChild.getChildNodes();
					for (int j = 0; j < measuresChildren.getLength(); j++)
					{
						Node measuresChild = measuresChildren.item(j);
						if (measuresChild.getNodeName().equals("measure"))
						{
							String name = getAttribute("name", measuresChild);
							String uid = getAttribute("uid", measuresChild);

							measures.add(name + ":" + uid);
						}
					}

				} else if (rootChild.getNodeName().equals("dimensions"))
				{
					result += "DIMENSIONS<br>";
					NodeList dimensionsChildren = rootChild.getChildNodes();
					for (int j = 0; j < dimensionsChildren.getLength(); j++)
					{
						Node dimensionsChild = dimensionsChildren.item(j);
						if (dimensionsChild.getNodeName().equals("dimension"))
						{
							String name = getAttribute("name", dimensionsChild);
							String uid = getAttribute("uid", dimensionsChild);

							result += "____" + name + " " + uid + "<br>";

							NodeList attributes = dimensionsChild.getChildNodes();

							for (int k = 0; k < attributes.getLength(); k++)
							{
								if (attributes.item(k).getNodeName().equals("hierarchies"))
								{
									NodeList hierarchiesChildren = attributes.item(k).getChildNodes();
									for (int l = 0; l < hierarchiesChildren.getLength(); l++)
									{
										Node hierChild = hierarchiesChildren.item(l);
										if (hierChild.getNodeName().equals("hierarchy"))
										{
											String hierName = getAttribute("name", hierChild);
											String hierUid = getAttribute("uid", hierChild);

											result += "_______" + hierName + " " + hierUid + "<br>";
											for (int m = 0; m < hierChild.getChildNodes().getLength(); m++)
											{
												Node hlevels = hierChild.getChildNodes().item(m);
												if (hlevels.getNodeName().equals("levels"))
												{
													for (int n = 0; n < hlevels.getChildNodes().getLength(); n++)
													{
														Node hlevel = hlevels.getChildNodes().item(n);
														String levName = getAttribute("name", hlevel);
														String levUid = getAttribute("uid", hlevel);

														result += "_______" + levName + " " + levUid + "<br>";

														for (int o = 0; o < hlevel.getChildNodes().getLength(); o++)
														{
															Node lmembers = hlevel.getChildNodes().item(o);
															if (lmembers.getNodeName().equals("members"))
															{
																for (int p = 0; p < lmembers.getChildNodes()
																		.getLength(); p++)
																{
																	Node member = lmembers.getChildNodes().item(p);

																	String mbr = memberWithChildren(member,12);//new TreeNode(mbrName);
																	result += mbr;

																}
															}
														}
													}
												}
											}

										}
									}
								}

							}
						}
					}

				}
			}
		} else
			System.out.println("Could not load view!");
		return result;
	}

	private String memberWithChildren(Node node, int spaces)
	{
		String result ="";

		NodeList firstLevel = node.getChildNodes();
		String name = getAttribute("name", node);
		String uid = getAttribute("uid", node);
		for(int i=0; i<spaces;i++)
			result += "_";

		result += name + " " + uid + "<br>";

		for (int i = 0; i < firstLevel.getLength(); i++)
		{
			if (firstLevel.item(i).getNodeName().equals("children"))
			{
				NodeList childrenList = firstLevel.item(i).getChildNodes();
				for (int j = 0; j < childrenList.getLength(); j++)
				{
					if (childrenList.item(j).getNodeName().equals("child"))
					{


						String child = memberWithChildren(childrenList.item(j), spaces);
						result +=child;
					}
				}
			}
		}

		return result;
	}

	private String getAttribute(String name, Node node)
	{
		NodeList attributes = node.getChildNodes();
		String retStr = "";
		for (int m = 0; m < attributes.getLength(); m++)
		{
			if (attributes.item(m).getNodeName().equals(name))
				retStr = attributes.item(m).getFirstChild().getNodeValue();

		}
		return retStr;
	}
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
