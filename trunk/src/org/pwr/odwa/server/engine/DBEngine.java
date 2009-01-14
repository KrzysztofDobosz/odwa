package org.pwr.odwa.server.engine;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.pwr.odwa.common.result.DBRow;
import org.pwr.odwa.common.result.DBResult;
import org.pwr.odwa.common.selection.UserSelection;
import org.pwr.odwa.server.structure.DBField;
import org.pwr.odwa.server.structure.DBStructure;
import org.pwr.odwa.server.structure.DBTable;

/**
 *
 *
 */
public class DBEngine
{
   Connection conn;

   /**
    * Connects to Database under given URL, as a given user with given password
    *
    * @param url
    *           database adress
    * @param user
    *           user login
    * @param password
    */
   public void connect(String url, String user, String password)
   {
      try
      {
         Class.forName("com.mysql.jdbc.Driver");
         System.out.println(DriverManager.getDrivers());
         conn = DriverManager.getConnection(url, user, password);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Ends connection with database
    */
   public void disconnect()
   {
      try
      {
         conn.close();
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Executes Query in Database
    *
    * testowane dla sumy sprzedaży w podziale na kraje dla płci w roku
    * 2008(filtr)
    *
    * @param Query
    *           User Query
    * @return Result of User Query
    */
   public DBResult executeQuery(UserSelection Query)
   {
      // getting fact Table name
      String factTable = "factinternetsales";

      ArrayList<String> addedDimTables = new ArrayList<String>();

      // ***************axis dimensions checking
      ArrayList<String> dimIdFieldList = new ArrayList<String>(); // list
      // of field names and ids similarly to axisElemList
      dimIdFieldList.add("CustomerKey");
      dimIdFieldList.add("SalesTerritoryKey");

      ArrayList<String> factDimIdFieldList = new ArrayList<String>(); // list
      // of field names and ids in the fact table as the above
      factDimIdFieldList.add("CustomerKey");
      factDimIdFieldList.add("SalesTerritoryKey");

      ArrayList<String> dimElemName = new ArrayList<String>(); // list
      // of dimension elements' names expected by user
      dimElemName.add("Gender");
      dimElemName.add("SalesTerritoryCountry");
      ArrayList<String> axisDimList = new ArrayList<String>(); // obtained
      // from userSelection and translated to dimension names
      axisDimList.add("dimcustomer");
      axisDimList.add("dimsalesterritory");

      // Create MySQL query
      SQLQuery query = new MySQLQuery();
      query.addToFromClause(factTable); // add fact table to from fields...

      for (int i = 0; i < axisDimList.size(); i++)
      {
         String elem;
         if (!addedDimTables.contains(elem = axisDimList.get(i)))
         {
            addedDimTables.add(elem);
            query.addToFromClause(elem, SQLJoinOperator.INNER_JOIN, elem + "."
                  + dimIdFieldList.get(i), factTable + "."
                  + factDimIdFieldList.get(i));

         }
         query.addResField(elem + "." + dimElemName.get(i));
         query.addToGroupByClause(elem + "." + dimElemName.get(i));
      }

      String measure = "SUM(factinternetsales.SalesAmount)";// metadata module
      query.addResField(measure);

      // *************constraints check
      ArrayList<String> boundDimIdFieldList = new ArrayList<String>(); // list
      // field names and dimension id corresponding to
      // axisElemList
      boundDimIdFieldList.add("TimeKey");
      ArrayList<String> boundFactDimIdFieldList = new ArrayList<String>();
      // list of field names and dimension ids in the fact tabel as the above
      boundFactDimIdFieldList.add("OrderDateKey");
      ArrayList<String> boundDimElemName = new ArrayList<String>(); // list
      // dimension element names expected by user
      boundDimElemName.add("CalendarYear");
      ArrayList<String> boundDimList = new ArrayList<String>(); // obtained
      // from userSelection and translated to dimension table names
      boundDimList.add("dimtime");
      ArrayList<String> boundDimValuesList = new ArrayList<String>();
      boundDimValuesList.add("2003");

      for (int i = 0; i < boundDimList.size(); i++)
      {
         String elem;
         if (!addedDimTables.contains(elem = boundDimList.get(i)))
         {
            addedDimTables.add(elem);
            query.addToFromClause(elem, SQLJoinOperator.INNER_JOIN, elem + "."
                  + boundDimIdFieldList.get(i), factTable + "."
                  + boundFactDimIdFieldList.get(i));
         }
         query.addToWhereClause(elem + "." + boundDimElemName.get(i) + "='"
               + boundDimValuesList.get(i) + "'", SQLLogicOperator.AND);
      }

      try
      {
         Statement db = conn.createStatement(); // ResultSet result =
         ResultSet result = db.executeQuery(query.getQuery());

         ArrayList<String> colNames = new ArrayList<String>();
         ArrayList<String> fieldTypes = new ArrayList<String>();

         int columnCount = (result.getMetaData().getColumnCount());

         for (int i = 1; i <= columnCount; i++)
         {
            colNames.add(result.getMetaData().getColumnName(i));
            fieldTypes.add(result.getMetaData().getColumnTypeName(i));
         }

         ArrayList<DBRow> rows = new ArrayList<DBRow>();

         ArrayList<Object> rowObjects = new ArrayList<Object>();

         if (result.next())
         {
            for (int i = 1; i <= columnCount; i++)
            {
               rowObjects.add(result.getObject(i));
            }
            rows.add(new DBRow(rowObjects));
         }
         else
         {
            return null;
         }

         DBResult res = new DBResult(rows, colNames, fieldTypes, query
               .getQuery());
         return res;
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

      return null;
   }

   /**
    * Returns all available databases. First use connect for getting
    * connections. Returns only databases visible for logged user.
    *
    * @return list of structures of all databases visible for logged user. See
    *         {@link DBStructure}
    */
   public ArrayList<DBStructure> getDatabases()
   {
      try
      {
         DatabaseMetaData meta = conn.getMetaData();
         ResultSet catalogs = meta.getCatalogs();

         ArrayList<DBStructure> result = new ArrayList<DBStructure>();

         // adding databases
         while (catalogs.next())
         {
            String catName = catalogs.getString(1);
            ResultSet tables = meta.getTables(catName, null, null, null);

            ArrayList<DBTable> dbTables = new ArrayList<DBTable>();

            // adding tables
            while (tables.next())
            {
               String tabName = tables.getString(3);
               ResultSet fields = meta.getColumns(catName, null, tabName, null);

               ArrayList<DBField> dbFields = new ArrayList<DBField>();

               // preparing list of primary keys in table
               ResultSet primaryKeys = meta.getPrimaryKeys(catName, null,
                     tabName);
               ArrayList<String> primKeys = new ArrayList<String>();

               while (primaryKeys.next())
               {
                  primKeys.add(primaryKeys.getString(4));
               }

               // creating foreign keys list
               ResultSet foreignKeys = meta.getImportedKeys(catName, null,
                     tabName);
               ArrayList<String> foreKeys = new ArrayList<String>();
               ArrayList<ForeKeyCont> foreKeysDesc = new ArrayList<ForeKeyCont>();

               while (foreignKeys.next())
               {
                  foreKeys.add(foreignKeys.getString(8));
                  foreKeysDesc.add(new ForeKeyCont(foreignKeys.getString(8),
                        foreignKeys.getString(3), foreignKeys.getString(4)));
               }

               // adding fields
               while (fields.next())
               {
                  String fieldName = fields.getString(4);
                  String foreTable = null;
                  String foreColumn = null;

                  if (foreKeys.contains(fieldName))
                  {
                     int i = foreKeys.indexOf(fieldName);
                     ForeKeyCont cont = foreKeysDesc.get(i);
                     foreTable = cont.getForeTableName();
                     foreColumn = cont.getForeColumnName();
                  }

                  DBField field = new DBField(fieldName, primKeys
                        .contains(fieldName), foreKeys.contains(fieldName),
                        foreTable, foreColumn, fields.getString(6), fields
                              .getString(13), fields.getBoolean(11));

                  dbFields.add(field);
               }

               DBTable table = new DBTable(tabName, dbFields);
               dbTables.add(table);
            }

            DBStructure struct = new DBStructure(catName, dbTables);
            result.add(struct);
         }
         return result;
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }

      return null;
   }
}

/**
 * Container for Foreign Key Data, usage only in getStructure()
 */
class ForeKeyCont
{
   private String name;

   private String foreTableName;

   private String foreColumnName;

   /**
    * Creates new ForeKeyKont
    *
    * @param n
    *           Foreign Key name
    * @param t
    *           Referd table name
    * @param c
    *           Refered column name
    */
   public ForeKeyCont(String n, String t, String c)
   {
      name = n;
      foreTableName = t;
      foreColumnName = c;
   }

   public String getName()
   {
      return name;
   }

   public String getForeTableName()
   {
      return foreTableName;
   }

   public String getForeColumnName()
   {
      return foreColumnName;
   }
}
