package org.pwr.odwa.common.dbtypes;

import java.util.ArrayList;

import org.pwr.odwa.common.selection.UserSelection;

public interface DBEngineApi
{
   public Object[] executeQuery(UserSelection userSel);
}
