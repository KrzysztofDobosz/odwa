package odwaServlet;

import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.pwr.odwa.common.selection.*;

/**
 * 
 * 
 * Servlet version of ODWA to execute needs all what every servlet.. so just
 * download http://necklon.hekko.pl/sppi/hurtownie/odwaServlet.7z, unpack to
 * tomcat webapps directory, run tomcat and visit
 * http://localhost:8080/odwaServ/odwa
 * 
 * also put metadata.xml to tomcat bin directory (I don't know why it reads from
 * there...)
 * 
 * any changes:
 * 
 * in this file - compile it and overwrite these in tomcat
 * webapps/odwaServ/odwa/WEB-INF/classes/odwaServlet
 * 
 * in server (userSelection and later also whole engine)
 * put jar archive of odwa server (org.pwr...) to webapps/odwaServ/odwa/WEB-INF/lib
 * 
 * it should work, if not - contact me.
 * 
 * @author Michal Brzezinski-Spiczak
 */

// //////////////////////////////////////////////////////////////////////////////
// /////////////////////////////////////////////////////////////////////////////
public class Odwa extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1983459834166849875L;

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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BasicDom dom = new BasicDom();
		dom.start("metadata.xml");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out
				.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		out
				.println("<html xmlns=\"http://www.w3.org/1999/xhtml\"> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		out
				.println("<title>ODWA - servlet version</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"view.css\" media=\"all\">");
		out
				.println("<script type=\"text/javascript\" src=\"view.js\"></script>  </head> <body id=\"main_body\" >  ");
		out
				.println("<div style=\"position: absolute; top:12px; left:20px; width:750px; \">	<img src=\"top.png\" width =\"720 \"  ></div>");
		out
				.println("<div style=\"position: absolute; top:20px; left:20px; width:550px; height:550px \" id=\"form_container\"><h1><a>Open Data Warehouse Analysis</a></h1> ");
		out
				.println("<form id=\"form_125708\" class=\"appnitro\"  method=\"post\" action=\"\"><div class=\"form_description\"><h2>Open Data Warehouse Analysis</h2><p>Servlet version of ODWA</p></div>");
		out
				.println("<ul><li id=\"li_6\" ><label class=\"description\" for=\"measure\">Measure </label>");

		out
				.println("<div><select class=\"element select medium\" id=\"measure\" name=\"measure\"><option value=\"\" selected=\"selected\"></option>");
		for (int i = 0; i < dom.measures.size(); i++)
			out.println("<option value=\"" + dom.measures.get(i).split(":")[1]
					+ "\" >" + dom.measures.get(i).split(":")[0] + "</option>");
		out.println("</select></div>");
		out
				.println("<p class=\"guidelines\" id=\"guide_6\"><small>Choose measure</small></p></li>");
		out
				.println("<li id=\"li_1\" ><label class=\"description\" for=\"column_1\">Column: nested axis 1 </label>");
		out
				.println("<div><input id=\"column_1\" name=\"column_1\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_1\"><small>Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p></li>");

		out
				.println("<li id=\"li_2\" ><label class=\"description\" for=\"column_2\">Column: nested axis 2</label>");
		out
				.println("<div><input id=\"column_2\" name=\"column_2\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_2\"><small>(optionally) Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p> </li>");
		out
				.println("<li id=\"li_4\" ><label class=\"description\" for=\"row_1\">Row: nested axis 1</label>");
		out
				.println("<div><input id=\"row_1\" name=\"row_1\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_4\"><small>Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p> </li>");
		out
				.println("<li id=\"li_3\" ><label class=\"description\" for=\"row_2\">Row: nested axis 2</label>");
		out
				.println("<div><input id=\"row_2\" name=\"row_2\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_3\"><small>(optionally) Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p> </li>");
		out
				.println("<li id=\"li_5\" ><label class=\"description\" for=\"filter\">Filter </label>");
		out
				.println("<div><input id=\"filter\" name=\"filter\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_5\"><small>Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p> </li>");
		out
				.println("<li class=\"buttons\"><input type=\"hidden\" name=\"databaseId\" value="
						+ dom.databaseId
						+ " /><input type=\"hidden\" name=\"form_id\" value=\"125708\" /><input id=\"saveForm\" class=\"button_text\" type=\"submit\" name=\"submit\" value=\"Submit\" /></li></ul></form>");

		// Enumeration paramNames = request.getParameterNames();
		// while (paramNames.hasMoreElements()) {
		// String paramName = (String) paramNames.nextElement();
		// out.println(paramName + " --- ");
		// String[] paramValues = request.getParameterValues(paramName);
		// if (paramValues.length == 1) {
		// String paramValue = paramValues[0];
		// if (paramValue.length() == 0)
		// out.print("<I>No Value</I>");
		// else
		// out.print(paramValue + "<br>");
		// } else {
		// out.println("<UL>");
		// for (int i = 0; i < paramValues.length; i++) {
		// out.println("<LI>" + paramValues[i]);
		// }
		// out.println("</UL>");
		// }
		// }
		// //////////////////////////////////////tworzenie userSelection
		if (request.getParameter("form_id") != null) {
			UserSelection selection = new UserSelection();

			// setting measure
			Measure measure = new Measure();
			if (request.getParameter("measure") != "") {
				String measureID = request.getParameter("measure");
				measure = new Measure();
				measure.setMeasureUid(measureID);

				// out.println("Tworzenie userSelection:<br>measure = "
				// + measure.getMeasureUid());
			}
			selection.setMeasure(measure);
			// setting databaseid
			String baseId = null;
			if (request.getParameter("databaseId") != "") {
				baseId = request.getParameter("databaseId");

				// out.println("<br>database = " + baseId);
			}
			selection.setDataBaseId(baseId);
			// setting column - axis 1

			AxisElement columnAxis1 = new AxisElement();
			DimensionElSet dimelset = new DimensionElSet();
			DimensionEl dimel = new DimensionEl();

			Axis col = new Axis();
			if (request.getParameter("column_1") != "") {
				col.addAxisElement(readAxisElement(request, "column_1"));
			}
			if (request.getParameter("column_2") != "") {
				col.addAxisElement(readAxisElement(request, "column_2"));
			}
			Axis row = new Axis();
			if (request.getParameter("row_1") != "") {
				row.addAxisElement(readAxisElement(request, "row_1"));
			}
			if (request.getParameter("row_2") != "") {
				row.addAxisElement(readAxisElement(request, "row_2"));
			}

			selection.setColumn(col);

			selection.setRow(row);

			selection.setSlice(readFilter(request));

			out.println(selection);
		}
		// ///////////////////////////////////////////////////////////////
		out
				.println("<div   id=\"footer\">Generated by <a href=\"http://www.phpform.org\">pForm</a></div><img src=\"bottom.png\" width =\"720 \"  ></div>");

		out
				.println("<div style=\"position: absolute; padding: 20px; top:20px; left:570px; width:140px; height:510px \" id=\"form_container\"><b>Datastore structure:<br><br></b>");
		out.println("<b>Dimensions:</b><br><br>");
		for (int i = 0; i < dom.dimensions.size(); i++)
			out.println(dom.dimensions.get(i).split(":")[0] + " ("
					+ dom.dimensions.get(i).split(":")[1] + ") " + "<br>");
		out.println("</div>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private DimensionElSet readFilter(HttpServletRequest request) {
		DimensionElSet dimelset = new DimensionElSet();
		if (request.getParameter("filter") != "") {
			// out.println("<br><br>Filter = <br>");
			String column1 = request.getParameter("filter");
			String[] column1el = column1.split(" ");
			// for (int i=0; i<column1el.length; i++)
			// out.print(column1el[i] + "|");}

			DimensionEl dimel;
			for (int i = 0; i < column1el.length; i++) {
				dimel = new DimensionEl();
				Path path = new Path();
				Method method = new Method();
				if (column1el[i].contains(".")) {
					path.setPath(column1el[i].split("\\.")[0]);
					if (column1el[i].split("\\.").length == 2)
						method.setMethodId(column1el[i].split("\\.")[1]);
				} else
					path.setPath(column1el[i]);
				dimel.setPath(path);
				dimel.setMethod(method);
				dimelset.addDimensionEl(dimel);
				// out.print("<br>method: " +
				// dimel.getMethod().getMethodId()
				// + "--- path: " + dimel.getPath().getPath());

			}
		}
		return dimelset;

	}

	private AxisElement readAxisElement(HttpServletRequest request, String pole) {
		AxisElement axsisElement = new AxisElement();

		// out.println("<br><br>Column_1 = <br>");

		String column1 = request.getParameter(pole);
		String[] column1el = column1.split(" ");
		DimensionElSet dimelset = new DimensionElSet();
		Function func = new Function();
		for (int i = 0; i < column1el.length; i++) {
			Path path = new Path();
			Method method = new Method();
			if (column1el[i].contains(".")) {
				path.setPath(column1el[i].split("\\.")[0]);
				if (column1el[i].split("\\.").length == 2)
					method.setMethodId(column1el[i].split("\\.")[1]);
			} else
				path.setPath(column1el[i]);
			DimensionEl dimel = new DimensionEl();
			dimel.setPath(path);
			dimel.setMethod(method);
			dimelset.addDimensionEl(dimel);
			// out.print("<br>method: " +
			// dimel.getMethod().getMethodId()
			// + "--- path: " + dimel.getPath().getPath());
		}

		axsisElement.setDimensionElSet(dimelset);
		axsisElement.setFunction(func);

		return axsisElement;
	}
}
