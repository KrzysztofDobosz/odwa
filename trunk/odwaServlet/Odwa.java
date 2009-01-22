package odwaServlet;

import java.io.*; 

import javax.servlet.*;
import javax.servlet.http.*;

import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.result.DBRow;
import org.pwr.odwa.common.selection.*;
import org.pwr.odwa.server.engine.DBEngine;

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

	private static final long serialVersionUID = 1983459834166849875L;

	

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
		
		if (request.getParameter("form_id") == null){
			out.println(displayForm(dom));
		}

		
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
			
			////////////////////////////////////////////////////////////////////////////
			//wykonywanie zapytania...
			
			try{
			  DBEngine db = new DBEngine();
		        db.connect("jdbc:mysql://localhost/" + baseId, "root", "pass");
		       
		        DBResult res = db.executeQuery(selection);
		        db.disconnect();
		        DBRow row__;
		        int count = res.getColumnCount();
		        out.println("<br><br>");
		        for (int i = 0; i < count; i++)
		        {
		            out.print(res.getColumnName(i) + " ");
		        } 
		       out.println("<br>");
		       
		       
		        while ( (row__ = res.fetchRow()) != null)
		        {
		            for (int i = 0; i< count; i++)
		                { 
		                out.print(row__.getFieldVal(i).toString() + " ");
		                }
		                out.println("<br>");
		                
		                
		        }
		        
		}catch (Exception e) {
			out.println("Nieznany b��d!");
			out.println(e.getMessage());
		}
			
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
	
	public String displayForm(BasicDom dom){
		String formString = new String();
		 
		formString+=("<form id=\"form_125708\" class=\"appnitro\"  method=\"post\" action=\"\"><div class=\"form_description\"><h2>Open Data Warehouse Analysis</h2><p>Servlet version of ODWA</p></div>");
		formString+=("<ul><li id=\"li_6\" ><label class=\"description\" for=\"measure\">Measure </label>");
		formString+=("<div><select class=\"element select medium\" id=\"measure\" name=\"measure\"><option value=\"\" selected=\"selected\"></option>");
		for (int i = 0; i < dom.measures.size(); i++)
			formString+=("<option value=\"" + dom.measures.get(i).split(":")[1]
					+ "\" >" + dom.measures.get(i).split(":")[0] + "</option>");
		formString+=("</select></div>");
		formString+=("<p class=\"guidelines\" id=\"guide_6\"><small>Choose measure</small></p></li>");
		formString+=("<li id=\"li_1\" ><label class=\"description\" for=\"column_1\">Column: nested axis 1 </label>");
		formString+=("<div><input id=\"column_1\" name=\"column_1\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_1\"><small>Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p></li>");

		formString+=("<li id=\"li_2\" ><label class=\"description\" for=\"column_2\">Column: nested axis 2</label>");
		formString+=("<div><input id=\"column_2\" name=\"column_2\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_2\"><small>(optionally) Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p> </li>");
		formString+=("<li id=\"li_4\" ><label class=\"description\" for=\"row_1\">Row: nested axis 1</label>");
		formString+=("<div><input id=\"row_1\" name=\"row_1\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_4\"><small>Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p> </li>");
		formString+=("<li id=\"li_3\" ><label class=\"description\" for=\"row_2\">Row: nested axis 2</label>");
		formString+=("<div><input id=\"row_2\" name=\"row_2\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_3\"><small>(optionally) Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p> </li>");
		formString+=("<li id=\"li_5\" ><label class=\"description\" for=\"filter\">Filter </label>");
		formString+=("<div><input id=\"filter\" name=\"filter\" class=\"element text large\" type=\"text\" maxlength=\"255\" value=\"\"/> </div><p class=\"guidelines\" id=\"guide_5\"><small>Write UID of specified dimension/hierarhic elements, use .MEMBERS, .CHILDREN</small></p> </li>");
		formString+=("<li class=\"buttons\"><input type=\"hidden\" name=\"databaseId\" value="
						+ dom.databaseId
						+ " /><input type=\"hidden\" name=\"form_id\" value=\"125708\" /><input id=\"saveForm\" class=\"button_text\" type=\"submit\" name=\"submit\" value=\"Submit\" /></li></ul></form>");
		
		return formString;
	}
}