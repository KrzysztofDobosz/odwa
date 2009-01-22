package org.pwr.odwa.odwaServlet;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.tree.TreeNode;
import com.google.gwt.xml.client.XMLParser;

public class MetaXmlParserServlet
{

	public String parse(String xmlString)
	{

		String result = "";
		Document doc = XMLParser.parse(xmlString);
		// MessageBox.alert("Parsing XML");
		Element root = doc.getDocumentElement();

		TreeNode returnNode = new TreeNode("METADATA");
		returnNode.setExpanded(true);

		if (root.getNodeName().equals("metadata"))
		{
			returnNode.setAttribute("allowDrag", "false");

			NodeList rootChildren = root.getChildNodes();
			for (int i = 0; i < rootChildren.getLength(); i++)
			{
				Node rootChild = rootChildren.item(i);
				if (rootChild.getNodeName().equals("measures"))
				{
					result += "___MEASURES<br>";
					NodeList measuresChildren = rootChild.getChildNodes();
					for (int j = 0; j < measuresChildren.getLength(); j++)
					{
						Node measuresChild = measuresChildren.item(j);
						if (measuresChild.getNodeName().equals("measure"))
						{
							String name = getAttribute("name", measuresChild);
							String uid = getAttribute("uid", measuresChild);

							result += "______" + name + " " + uid + "<p>";
						}
					}

				} else if (rootChild.getNodeName().equals("dimensions"))
				{
					result += "___DIMENSIONS<br>";
					NodeList dimensionsChildren = rootChild.getChildNodes();
					for (int j = 0; j < dimensionsChildren.getLength(); j++)
					{
						Node dimensionsChild = dimensionsChildren.item(j);
						if (dimensionsChild.getNodeName().equals("dimension"))
						{
							String name = getAttribute("name", dimensionsChild);
							String uid = getAttribute("uid", dimensionsChild);

							result += "_______" + name + " " + uid + "<br>";

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

											result += "__________" + hierName + " " + hierUid + "<br>";
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

														result += "__________" + levName + " " + levUid + "<br>";

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
			MessageBox.alert("Could not load view!");
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


						String child = memberWithChildren(childrenList.item(j));
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

}
