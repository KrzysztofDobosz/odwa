package org.pwr.odwa.client.guiUtils;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.tree.TreeNode;
import com.google.gwt.xml.client.XMLParser;

/**
 * Parser for XML tree structure given by metadata module, creates tree possible
 * to load into tree panel and display.
 *
 *
 * @author Lukasz Pintal
 *
 */
public class MetaXmlParser
{

	/**
	 * Parses XML string and generates gwt-ext tree.
	 *
	 *
	 * @param xmlString string containing XML structure
	 * @return root node of data view tree
	 */
	public TreeNode parse(String xmlString)
	{

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
					TreeNode measures = new TreeNode("MEASURES");
					measures.setExpanded(true);
					measures.setAttribute("allowDrag", "false");
					measures.setAttribute("metaType", "measure");
					returnNode.appendChild(measures);
					NodeList measuresChildren = rootChild.getChildNodes();
					for (int j = 0; j < measuresChildren.getLength(); j++)
					{
						Node measuresChild = measuresChildren.item(j);
						if (measuresChild.getNodeName().equals("measure"))
						{
							String name = getAttribute("name", measuresChild);
							String uid = getAttribute("uid", measuresChild);
							NodeList attributes = measuresChild.getChildNodes();

							TreeNode measure = new TreeNode(name);
							measure.setAttribute("uid", uid);
							measure.setAttribute("allowDrag", "true");
							measure.setAttribute("metaType", "measure");
							measures.appendChild(measure);
						}
					}

				} else if (rootChild.getNodeName().equals("dimensions"))
				{
					TreeNode dimensions = new TreeNode("DIMENSIONS");
					dimensions.setExpanded(true);
					dimensions.setAttribute("allowDrag", "false");
					returnNode.appendChild(dimensions);
					NodeList dimensionsChildren = rootChild.getChildNodes();
					for (int j = 0; j < dimensionsChildren.getLength(); j++)
					{
						Node dimensionsChild = dimensionsChildren.item(j);
						if (dimensionsChild.getNodeName().equals("dimension"))
						{
							String name = getAttribute("name", dimensionsChild);
							String uid = getAttribute("uid", dimensionsChild);

							TreeNode dimension = new TreeNode(name);
							dimension.setAttribute("uid", uid);
							dimension.setAttribute("allowDrag", "false");
							dimension.setAttribute("metaType", "dimension");
							NodeList attributes = dimensionsChild.getChildNodes();

							for (int k = 0; k < attributes.getLength(); k++)
							{
								if (attributes.item(k).getNodeName().equals("hierarchies"))
								{
									TreeNode hierarchies = new TreeNode("hierarchies");
									hierarchies.setAttribute("allowDrag", "false");
									NodeList hierarchiesChildren = attributes.item(k).getChildNodes();
									for (int l = 0; l < hierarchiesChildren.getLength(); l++)
									{
										Node hierChild = hierarchiesChildren.item(l);
										if (hierChild.getNodeName().equals("hierarchy"))
										{
											String hierName = getAttribute("name", hierChild);
											String hierUid = getAttribute("uid", hierChild);

											TreeNode hierarchy = new TreeNode(name);

											hierarchy.setAttribute("uid", uid);
											hierarchy.setAttribute("allowDrag", "false");
											hierarchy.setAttribute("metaType", "dimension");

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
														TreeNode level = new TreeNode(levName);
														level.setAttribute("uid", levUid);
														level.setAttribute("allowDrag", "true");
														level.setAttribute("metaType", "dimension");
														level.setAttribute("element", "false");
														hierarchy.appendChild(level);
														hierarchy.setExpanded(true);

														for (int o = 0; o < hlevel.getChildNodes().getLength(); o++)
														{
															Node lmembers = hlevel.getChildNodes().item(o);
															if (lmembers.getNodeName().equals("members"))
															{
																for (int p = 0; p < lmembers.getChildNodes()
																		.getLength(); p++)
																{
																	Node member = lmembers.getChildNodes().item(p);

																	TreeNode mbr = memberWithChildren(member);// new
																												// TreeNode(mbrName);

																	level.appendChild(mbr);
																	level.setExpanded(true);
																	mbr.setExpanded(false);

																}
															}
														}
													}
												}
											}

											dimension.appendChild(hierarchy);
											dimension.setExpanded(true);
										}
									}
									// dimension.appendChild(hierarchies);
								}

							}
							dimensions.setExpanded(true);
							dimensions.appendChild(dimension);
						}
					}

				}
			}
		} else
			MessageBox.alert("Could not load view!");
		return returnNode;
	}

	/**
	 * Creates gwt-ext tree node with children (recursively)
	 *
	 * @param node
	 * @return node with nested children
	 */
	private TreeNode memberWithChildren(Node node)
	{

		NodeList firstLevel = node.getChildNodes();
		String name = getAttribute("name", node);
		String uid = getAttribute("uid", node);

		TreeNode finalNode = new TreeNode(name);
		finalNode.setAttribute("uid", uid);
		finalNode.setAttribute("allowDrag", "true");
		finalNode.setAttribute("metaType", "dimension");
		finalNode.setAttribute("element", "true");
		finalNode.setExpanded(false);

		for (int i = 0; i < firstLevel.getLength(); i++)
		{
			if (firstLevel.item(i).getNodeName().equals("children"))
			{
				NodeList childrenList = firstLevel.item(i).getChildNodes();
				for (int j = 0; j < childrenList.getLength(); j++)
				{
					if (childrenList.item(j).getNodeName().equals("child"))
					{

						TreeNode child = memberWithChildren(childrenList.item(j));
						finalNode.appendChild(child);
					}
				}
			}
		}

		return finalNode;
	}


	/**
	 * Finds named attribute for given node and returns its value
	 *
	 * @param name attribute name
	 * @param node
	 * @return String containing value of parameter
	 */
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
