package org.pwr.odwa.server;

import java.util.ArrayList;

import org.pwr.odwa.common.metadata.*;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MetaGUIApiServiceImpl extends RemoteServiceServlet implements
		MetaGUIApiService
{

	public ArrayList<MetaSlot> getSlots(String userName)
	{
		System.out.println("ODWAServer: MetaGUIApi: getSlots executed");
		ArrayList<MetaSlot> list = new ArrayList<MetaSlot>();

		MetaSlot sl0 = new MetaSlot();
		sl0.setID(new MetaID(0));
		sl0.setName("Slot #0");
		sl0.setNotes("Notes for #0");
		list.add(sl0);

		MetaSlot sl1 = new MetaSlot();
		sl1.setID(new MetaID(1));
		sl1.setName("Slot #1");
		sl1.setNotes("Notes for #1");
		list.add(sl1);

		MetaSlot sl2 = new MetaSlot();
		sl2.setID(new MetaID(2));
		sl2.setName("Slot #2");
		sl2.setNotes("Notes for #2");
		list.add(sl2);

		return list;

	}

	public ArrayList<MetaDataView> getDataViews(MetaID slotId)
	{
		System.out.println("ODWAServer: MetaGUIApi: getDataViews executed");
		try
		{
			ArrayList<MetaDataView> list = new ArrayList<MetaDataView>();

			MetaDataView view = new MetaDataView();
			view.setID(new MetaID(slotId.getID() * 3 + 5));
			view.setName("View " + view.getID().getID());

			list.add(view);

			return list;
		} catch (Exception ex)
		{
			System.err.println("ODWAServer: getDataViews: " + ex);
			return null;
		}
	}

	public ArrayList<MetaDimTable> getDimTables(MetaID viewId)
	{
		System.out.println("ODWAServer: MetaGUIApi: getDimTables executed");
		try
		{
			ArrayList<MetaDimTable> list = new ArrayList<MetaDimTable>();

			return list;
		} catch (Exception ex)
		{
			System.err.println("ODWAServer: getDimTables: " + ex);
			return null;
		}
	}

	public ArrayList<MetaDim> getDimentions(MetaID tableId)
	{
		System.out.println("ODWAServer: MetaGUIApi: getDimentions executed");
		try
		{
			ArrayList<MetaDim> list = new ArrayList<MetaDim>();

			return list;
		} catch (Exception ex)
		{
			System.err.println("ODWAServer: getDimensions: " + ex);
			return null;
		}
	}

	public ArrayList<MetaDimElement> getDimElements(MetaID dimentionId)
	{
		System.out.println("ODWAServer: MetaGUIApi: getDimElements executed");
		try
		{
			ArrayList<MetaDimElement> list = new ArrayList<MetaDimElement>();

			return list;
		} catch (Exception ex)
		{
			System.err.println("ODWAServer: getElements: " + ex);
			return null;
		}
	}

	public ArrayList<MetaHierarchy> getHierarchies(MetaID viewId)
	{
		System.out.println("ODWAServer: MetaGUIApi: getHierarchies executed");
		try
		{
			ArrayList<MetaHierarchy> list = new ArrayList<MetaHierarchy>();

			return list;
		} catch (Exception ex)
		{
			System.err.println("ODWAServer: getHierarchies: " + ex);
			return null;
		}
	}

	public ArrayList<MetaMeasure> getMeasures(MetaID viewId)
	{
		System.out.println("ODWAServer: MetaGUIApi: getMeasures executed");
		try
		{
			ArrayList<MetaMeasure> list = new ArrayList<MetaMeasure>();

			return list;
		} catch (Exception ex)
		{
			System.err.println("ODWAServer: getMeasures: " + ex);
			return null;
		}
	}

	public String getDataView(MetaID viewId)
	{
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<metadata>"
				+ "<database>" + "<engine>mysql</engine>"
				+ "<host>localhost</host>" + "<port>3306</port>"
				+ "<name>odwa</name>" + "</database>" +

				"<measures>" + "<measure>" + "<uid>M1</uid>" +

				"<name>Order Quantity</name>"
				+ "<desc>Order Quantity measure</desc>" +

				"<table>factinternetsales</table>"
				+ "<field>OrderQuantity</field>" +

				"<format>%d</format>" + "<function>sum</function>"
				+ "</measure>" +

				"<measure>" + "<uid>M2</uid>" +

				"<name>Sales Amount</name>"
				+ "<desc>Sales Amount measure</desc>" +

				"<table>factinternetsales</table>"
				+ "<field>SalesAmount</field>" +

				"<format>%d</format>" + "<function>sum</function>"
				+ "</measure>" +

				"<measure>" + "<uid>M3</uid>" +

				"<name>Freight</name>" + "<desc>Freight measure</desc>" +

				"<table>factinternetsales</table>" + "<field>Freight</field>" +

				"<format>%.2f</format>" + "<function>sum</function>"
				+ "</measure>" + "</measures>" +

				"<dimensions>" + "<dimension>" + "<uid>D1</uid>" +

				"<name>Order Date</name>" + "<desc>Order Date dimension</desc>"
				+

				"<table>dimtime</table>" +

				"<primary>TimeKey</primary>"
				+ "<foreign>OrderDateKey</foreign>" +

				"<default>D1H1</default>" +

				"<hierarchies>" + "<hierarchy>" + "<uid>D1H1</uid>" +

				"<name>Calendar</name>" + "<desc>Calendar hierarchy</desc>" +

				"<default>D1H1L1</default>" +

				"<levels>" + "<level>" + "<uid>D1H1L1</uid>" +

				"<name>Year</name>" + "<desc>Year (calendar)</desc>" +

				"<field>CalendarYear</field>" + "<dtype>char(4)</dtype>" +

				"<default>D1H1L1M1</default>" +

				"<members>" + "<member>" + "<uid>D1H1L1M1</uid>" +

				"<name>2001</name>" + "<item>2001</item>" +

				"<children>" + "<child>" + "<uid>D1H1L1M1C1</uid>"
				+ "<ref>D1H1L2M1</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L1M1C2</uid>" + "<ref>D1H1L2M2</ref>"
				+ "</child>" + "</children>" + "</member>" +

				"<member>" + "<uid>D1H1L1M2</uid>" +

				"<name>2002</name>" + "<item>2002</item>" +

				"<children>" + "<child>" + "<uid>D1H1L1M2C1</uid>"
				+ "<ref>D1H1L2M1</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L1M2C2</uid>" + "<ref>D1H1L2M2</ref>"
				+ "</child>" + "</children>" + "</member>" +

				"<member>" + "<uid>D1H1L1M3</uid>" +

				"<name>2003</name>" + "<item>2003</item>" +

				"<children>" + "<child>" + "<uid>D1H1L1M3C1</uid>"
				+ "<ref>D1H1L2M1</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L1M3C2</uid>" + "<ref>D1H1L2M2</ref>"
				+ "</child>" + "</children>" + "</member>" +

				"<member>" + "<uid>D1H1L1M4</uid>" +

				"<name>2004</name>" + "<item>2004</item>" +

				"<children>" + "<child>" + "<uid>D1H1L1M4C1</uid>"
				+ "<ref>D1H1L2M1</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L1M4C2</uid>" + "<ref>D1H1L2M2</ref>"
				+ "</child>" + "</children>" + "</member>" + "</members>"
				+ "</level>" +

				"<level>" + "<uid>D1H1L2</uid>" +

				"<name>Semester</name>" + "<desc>Semester (calendar)</desc>" +

				"<field>CalendarSemester</field>" + "<dtype>tinyint(4)</dtype>"
				+

				"<default>D1H1L2M1</default>" +

				"<members>" + "<member>" + "<uid>D1H1L2M1</uid>" +

				"<name>S1</name>" + "<item>1</item>" +

				"<children>" + "<child>" + "<uid>D1H1L2M1C1</uid>"
				+ "<ref>D1H1L3M1</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L2M1C2</uid>" + "<ref>D1H1L3M2</ref>"
				+ "</child>" + "</children>" + "</member>" +

				"<member>" + "<uid>D1H1L2M2</uid>" +

				"<name>S2</name>" + "<item>2</item>" +

				"<children>" + "<child>" + "<uid>D1H1L2M2C1</uid>"
				+ "<ref>D1H1L3M3</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L2M2C2</uid>" + "<ref>D1H1L3M4</ref>"
				+ "</child>" + "</children>" + "</member>" + "</members>"
				+ "</level>" +

				"<level>" + "<uid>D1H1L3</uid>" +

				"<name>Quarter</name>" + "<desc>Quarter (calendar)</desc>" +

				"<field>CalendarQuarter</field>" + "<dtype>tinyint(4)</dtype>" +

				"<default>D1H1L3M1</default>" +

				"<members>" + "<member>" + "<uid>D1H1L3M1</uid>" +

				"<name>Q1</name>" + "<item>1</item>" +

				"<children>" + "<child>" + "<uid>D1H1L3M1C1</uid>"
				+ "<ref>D1H1L4M1</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L3M1C2</uid>" + "<ref>D1H1L4M2</ref>"
				+ "</child>" +

				"<child>" + "<uid>D1H1L3M1C3</uid>" + "<ref>D1H1L4M3</ref>"
				+ "</child>" + "</children>" + "</member>" +

				"<member>" + "<uid>D1H1L3M2</uid>" +

				"<name>Q2</name>" + "<item>2</item>" +

				"<children>" + "<child>" + "<uid>D1H1L3M2C1</uid>"
				+ "<ref>D1H1L4M4</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L3M2C2</uid>" + "<ref>D1H1L4M5</ref>"
				+ "</child>" +

				"<child>" + "<uid>D1H1L3M2C3</uid>" + "<ref>D1H1L4M6</ref>"
				+ "</child>" + "</children>" + "</member>" +

				"<member>" + "<uid>D1H1L3M3</uid>" +

				"<name>Q3</name>" + "<item>3</item>" +

				"<children>" + "<child>" + "<uid>D1H1L3M3C1</uid>"
				+ "<ref>D1H1L4M7</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L3M3C2</uid>" + "<ref>D1H1L4M8</ref>"
				+ "</child>" +

				"<child>" + "<uid>D1H1L3M3C3</uid>" + "<ref>D1H1L4M9</ref>"
				+ "</child>" + "</children>" + "</member>" + "" + "<member>"
				+ "<uid>D1H1L3M4</uid>" +

				"<name>Q4</name>" + "<item>4</item>" +

				"<children>" + "<child>" + "<uid>D1H1L3M4C1</uid>"
				+ "<ref>D1H1L4M10</ref>" + "</child>" +

				"<child>" + "<uid>D1H1L3M4C2</uid>" + "<ref>D1H1L4M11</ref>"
				+ "</child>" +

				"<child>" + "<uid>D1H1L3M4C3</uid>" + "<ref>D1H1L4M12</ref>"
				+ "</child>" + "</children>" + "</member>" + "</members>"
				+ "</level>" +

				"<level>" + "<uid>D1H1L4</uid>" +

				"<name>Month</name>" + "<desc>Month (calendar)</desc>" +

				"<field>MonthNumberOfYear</field>"
				+ "<dtype>tinyint(4)</dtype>" +

				"<default>D1H1L3M1</default>" +

				"<members>" + "<member>" + "<uid>D1H1L3M1</uid>" +

				"<name>January</name>" + "<item>1</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M2</uid>" +

				"<name>February</name>" + "<item>2</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M3</uid>" +

				"<name>March</name>" + "<item>3</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M4</uid>" +

				"<name>April</name>" + "<item>4</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M5</uid>" +

				"<name>May</name>" + "<item>5</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M6</uid>" +

				"<name>June</name>" + "<item>6</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M7</uid>" +

				"<name>July</name>" + "<item>7</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M8</uid>" +

				"<name>August</name>" + "<item>8</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M9</uid>" +

				"<name>September</name>" + "<item>9</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M10</uid>" +

				"<name>Ocober</name>" + "<item>10</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M11</uid>" +

				"<name>November</name>" + "<item>11</item>" + "</member>" +

				"<member>" + "<uid>D1H1L3M12</uid>" +

				"<name>December</name>" + "<item>12</item>" + "</member>"
				+ "</members>" + "</level>" + "</levels>" + "</hierarchy>"
				+ "</hierarchies>" + "</dimension>" +

				"<dimension>" + "<uid>D2</uid>" +

				"<name>Customer</name>" + "<desc>Customer dimension</desc>" +

				"<table>dimcustomer</table>" +

				"<primary>CustomerKey</primary>"
				+ "<foreign>CustomerKey</foreign>" +

				"<default>D2H1</default>" +

				"<hierarchies>" + "<hierarchy>" + "<uid>D2H1</uid>" +

				"<name>Gender</name>" + "<desc>Gender hierarchy</desc>" +

				"<default>D2H1L1</default>" +

				"<levels>" + "<level>" + "<uid>D2H1L1</uid>" +

				"<name>Gender</name>" + "<desc>Gender level</desc>" +

				"<field>Gender</field>" + "<dtype>varchar(1)</dtype>" +

				"<default>D2H1L1M1</default>" +

				"<members>" + "<member>" + "<uid>D2H1L1M1</uid>" +

				"<name>Female</name>" + "<item>F</item>" + "</member>"
				+ "<member>" + "<uid>D2H1L1M2</uid>" +

				"<name>Male</name>" + "<item>M</item>" + "</member>"
				+ "</members>" + "</level>" + "</levels>" + "</hierarchy>"
				+ "</hierarchies>" + "</dimension>" +

				"<dimension>" + "<uid>D3</uid>" +

				"<name>Product</name>" + "<desc>Product dimension</desc>" +

				"<table>dimproduct</table>" +

				"<primary>ProductKey</primary>"
				+ "<foreign>ProductKey</foreign>" +

				"<default>D3H1</default>" +

				"<hierarchies>" + "<hierarchy>" + "<uid>D3H1</uid>" +

				"<name>Color</name>" + "<desc>Color hierarchy</desc>" +

				"<default>D3H1L1</default>" +

				"<levels>" + "<level>" + "<uid>D3H1L1</uid>" +

				"<name>Color</name>" + "<desc>Color level</desc>" +

				"<field>Color</field>" + "<dtype>varchar(15)</dtype>" +

				"<default>D3H1L1M1</default>" +

				"<members>" + "<member>" + "<uid>D3H1L1M1</uid>" +

				"<name>Unknown</name>" + "<item>NA</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M2</uid>" +

				"<name>Black</name>" + "<item>Black</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M3</uid>" +

				"<name>Silver</name>" + "<item>Silver</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M4</uid>" +

				"<name>Red</name>" + "<item>Red</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M5</uid>" +

				"<name>White</name>" + "<item>White</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M6</uid>" +

				"<name>Blue</name>" + "<item>Blue</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M7</uid>" +

				"<name>Multi</name>" + "<item>Multi</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M8</uid>" +

				"<name>Yellow</name>" + "<item>Yellow</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M9</uid>" +

				"<name>Grey</name>" + "<item>Grey</item>" + "</member>"
				+ "<member>" + "<uid>D3H1L1M10</uid>" +

				"<name>Silver/Black</name>" + "<item>Silver/Black</item>"
				+ "</member>" + "</members>" + "</level>" + "</levels>"
				+ "</hierarchy>" + "</hierarchies>" + "</dimension>"
				+ "</dimensions>" + "</metadata>";
	}

}
