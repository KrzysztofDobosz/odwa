package org.pwr.odwa.common.selection;

import java.io.Serializable;
import java.util.ArrayList;

import org.pwr.odwa.server.metadata.Metadata;
import org.pwr.odwa.server.metadata.UID;

/**
 * Class representing hierarchical path inside dimension
 *
 * @author Katarzyna Rzerzicha
 * @author Michał Brzeziński-Spiczak
 *
 */
public class Path implements Serializable {

	private static final long serialVersionUID = -2841252524985516662L;

	private String path;

	public Path() {
		path = null;
	}
  /**
   * Get path
   */
	public String getPath() {
		return path;
	}
	/**
	 * Set path
	 */

	public void setPath(String path) {
		this.path = path;
	}

    public String toMDX(Metadata meta, boolean keys) {
        if (keys) {
            return meta.getElement(new UID(path)).getKeyUniqueName(meta);
        } else {
            return meta.getElement(new UID(path)).getUniqueName(meta);
        }
    }
}

