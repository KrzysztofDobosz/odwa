package org.pwr.odwa.client.guiUtils;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NamedNodeMap;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.gwtext.client.widgets.MessageBox;
import com.gwtext.client.widgets.tree.TreeNode;
import com.google.gwt.xml.client.XMLParser;

public class MetaXmlParser
{

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
					returnNode.appendChild(measures);
					NodeList measuresChildren = rootChild.getChildNodes();
					for (int j = 0; j < measuresChildren.getLength(); j++)
					{
						Node measuresChild = measuresChildren.item(j);
						if (measuresChild.getNodeName().equals("measure"))
						{
							String name = "";
							String uid = "";
							NodeList attributes = measuresChild.getChildNodes();
							for (int k = 0; k < attributes.getLength(); k++)
							{
								if (attributes.item(k).getNodeName().equals(
										"name"))
									name = attributes.item(k).getFirstChild().getNodeValue();

								else if(attributes.item(k).getNodeName().equals(
								"uid"))
									uid = attributes.item(k).getFirstChild().getNodeValue();
							}
							TreeNode measure = new TreeNode(name);
							measure.setAttribute("uid", uid);
							measure.setAttribute("allowDrag", "true");
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
							String name = "";
							String uid = "";
							NodeList attributes = dimensionsChild.getChildNodes();
							for (int k = 0; k < attributes.getLength(); k++)
							{
								if (attributes.item(k).getNodeName().equals(
										"name"))
									name = attributes.item(k).getFirstChild().getNodeValue();

								else if(attributes.item(k).getNodeName().equals(
								"uid"))
									uid = attributes.item(k).getFirstChild().getNodeValue();
							}
							TreeNode dimension = new TreeNode(name);
							dimension.setAttribute("uid", uid);
							dimension.setAttribute("allowDrag", "true");
							dimensions.appendChild(dimension);
						}
					}


				}
			}
		} else
			MessageBox.alert("Could not load view!");
		return returnNode;
	}


}
