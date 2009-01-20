package org.pwr.odwa.common.result;

import java.io.Serializable;
import java.util.ArrayList;


import org.pwr.odwa.common.dbtypes.DBFieldDataType;

/**
 * Represents database query result (mostly syntactic sugar)
 *
 */
public class DBResult implements Serializable
{
   private int next;

   private String query;

   private ArrayList<DBRow> rows;

   private ArrayList<String> columnNames;

   private ArrayList<String> fieldTypes;

   private int columnCount;

   public DBResult(){}

   public DBResult(ArrayList<DBRow> DBRes, ArrayList<String> colNames,
         ArrayList<String> fieldTypes, String query)
   {
      this.query = query;
      this.rows = DBRes;
      this.columnNames = colNames;
      this.fieldTypes = fieldTypes;
      this.columnCount = columnNames.size();
      next = 0;
   }

   /**
    * Returns next row of the result
    *
    * @return
    */
   public DBRow fetchRow()
   {
      if (next >= rows.size()) return null;
      DBRow ret = rows.get(next);
      next += 1;
      return ret;
   }

   /**
    * Resets current row number
    *
    * @see #fetchRow()
    */
   public void reset()
   {
      next = 0;
   }
   
   /**
    * Returns dataType for the column
    *
    * @param num
    *           Column index
    *
    * @return
    */
   public DBFieldDataType getType(int num)
   {
      String[] textTypes =
      { "BLOB", "CHAR ", "BINARY", "VARBINARY", "VARCHAR", "TEXT", "SET",
            "ENUM", "LONGVARBINARY", "LONGVARCHAR" };
      String[] numTypes =
      { "INT", "TINYINT ", "SMALLINT", "MEDIUMINT", "BIGINT", "FLOAT",
            "DOUBLE", "REAL", "NUMERIC", "INTEGER", "DECIMAL", "BOOLEAN" };
      String[] dateTypes =
      { "DATETIME", "DATE", "TIMESTAMP", "TIME", "YEAR" };

      for (int i = 0; i < textTypes.length; i++)
      {
         if (fieldTypes.get(num).equals(textTypes[i]))
            return DBFieldDataType.StringType;
      }
      for (int i = 0; i < numTypes.length; i++)
      {
         if (fieldTypes.get(num).equals(numTypes[i]))
            return DBFieldDataType.IntegerType;
      }
      for (int i = 0; i < dateTypes.length; i++)
      {
         if (fieldTypes.get(num).equals(dateTypes[i]))
            return DBFieldDataType.DateType;
      }
      return null;
   }

   /**
    * Returns Type of the field identified by name
    *
    * @param name
    *           field name
    * @return
    */
   DBFieldDataType getType(String name)
   {
      int num = columnNames.indexOf(name);
      return getType(num);
   }

   /**
    * Returns Result ArrayList
    *
    * @return
    */
   ArrayList<DBRow> fetchArray()
   {
      return null;
   }

   /**
    * @return the columnCount
    */
   public int getColumnCount()
   {
      return columnCount;
   }

   /**
    * Returns name of the column
    *
    * @param num
    *           number of the column
    * @return
    */
   public String getColumnName(int num)
   {
      return columnNames.get(num);
   }

   /**
    * @return the query
    */
   public String getQuery()
   {
      return query;
   }
}
