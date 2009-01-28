package org.pwr.odwa.server.metadata;

/**
 * Type definitions of metadata elements.
 */
public enum EltType {
    MDX_MEASURE,      /** Maps to 'measure' in metadata XML */
    MDX_DIMENSION,    /** Maps to 'dimension' in metadata XML */
    MDX_HIERARCHY,    /** Maps to 'hierarchy' in metadata XML */
    MDX_LEVEL,        /** Maps to 'level' in metadata XML */
    MDX_MEMBER,       /** Maps to 'memeber' in metadata XML */
    MDX_CHILD,        /** Maps to 'child' in metadata XML */
}

