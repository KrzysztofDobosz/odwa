package org.pwr.odwa.server.metadata;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.xpath.*;
import org.xml.sax.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;

public class Metadata {
    private HashMap<EltType, ArrayList<UID>> m_types;
    private HashMap<String, ArrayList<UID>> m_names;
    private HashMap<UID, Meta> m_cache;
    private DatabaseInfo m_info;

    public Metadata() {
        initMetadata();
    }

    public void initMetadata() {
        m_types = new HashMap<EltType, ArrayList<UID>>();
        m_names = new HashMap<String, ArrayList<UID>>();
        m_cache = new HashMap<UID, Meta>();

        EltType[] types = {
            EltType.MDX_MEASURE,
            EltType.MDX_DIMENSION,
            EltType.MDX_HIERARCHY,
            EltType.MDX_LEVEL,
            EltType.MDX_MEMBER,
        };

        ArrayList<UID> list;

        for (int i = 0; i < types.length; i++) {
            list = new ArrayList<UID>();
            m_types.put(types[i], list);
        }

        m_info = new DatabaseInfo();
    }

    public void loadMetadata(String xml) {
        try {
            File document = new File(xml);

            XPath xpath = XPathFactory.newInstance().newXPath();

            Node node;
            String path;
            InputSource source;

            path = "/metadata/database";

            source = new InputSource(new FileInputStream(document));
            Node database = (Node)xpath.evaluate(path,
                source, XPathConstants.NODE);

            node = (Node)xpath.evaluate("engine",
                database, XPathConstants.NODE);
            m_info.setProvider(node.getTextContent());

            node = (Node)xpath.evaluate("host",
                database, XPathConstants.NODE);
            m_info.setHost(node.getTextContent());

            node = (Node)xpath.evaluate("port",
                database, XPathConstants.NODE);
            m_info.setPort(node.getTextContent());

            node = (Node)xpath.evaluate("name",
                database, XPathConstants.NODE);
            m_info.setName(node.getTextContent());

            path = "/metadata/measures/measure";

            source = new InputSource(new FileInputStream(document));
            NodeList measures = (NodeList)xpath.evaluate(path,
                source, XPathConstants.NODESET);

            for (int i = 0; i < measures.getLength(); i++) {
                Measure measure = new Measure();

                node = (Node)xpath.evaluate("uid",
                    measures.item(i), XPathConstants.NODE);
                measure.setUID(new UID(node.getTextContent()));

                node = (Node)xpath.evaluate("name",
                    measures.item(i), XPathConstants.NODE);
                measure.setName(node.getTextContent());

                node = (Node)xpath.evaluate("desc",
                    measures.item(i), XPathConstants.NODE);
                measure.setDesc(node.getTextContent());

                node = (Node)xpath.evaluate("table",
                    measures.item(i), XPathConstants.NODE);
                measure.setTable(node.getTextContent());

                node = (Node)xpath.evaluate("field",
                    measures.item(i), XPathConstants.NODE);
                measure.setField(node.getTextContent());

                node = (Node)xpath.evaluate("format",
                    measures.item(i), XPathConstants.NODE);
                measure.setFormat(node.getTextContent());

                node = (Node)xpath.evaluate("function",
                    measures.item(i), XPathConstants.NODE);
                measure.setFunction(node.getTextContent());

                updateTypes(EltType.MDX_MEASURE, measure);
                updateNames(measure);
                updateCache(measure);
            }

            path = "/metadata/dimensions/dimension";

            source = new InputSource(new FileInputStream(document));
            NodeList dimensions = (NodeList)xpath.evaluate(path,
                source, XPathConstants.NODESET);

            for (int d = 0; d < dimensions.getLength(); d++) {
                Dimension dimension = new Dimension();

                node = (Node)xpath.evaluate("uid",
                    dimensions.item(d), XPathConstants.NODE);
                dimension.setUID(new UID(node.getTextContent()));

                node = (Node)xpath.evaluate("name",
                    dimensions.item(d), XPathConstants.NODE);
                dimension.setName(node.getTextContent());

                node = (Node)xpath.evaluate("desc",
                    dimensions.item(d), XPathConstants.NODE);
                dimension.setDesc(node.getTextContent());

                node = (Node)xpath.evaluate("table",
                    dimensions.item(d), XPathConstants.NODE);
                dimension.setTable(node.getTextContent());

                node = (Node)xpath.evaluate("primary",
                    dimensions.item(d), XPathConstants.NODE);
                dimension.setPrimary(node.getTextContent());

                node = (Node)xpath.evaluate("foreign",
                    dimensions.item(d), XPathConstants.NODE);
                dimension.setForeign(node.getTextContent());

                node = (Node)xpath.evaluate("default",
                    dimensions.item(d), XPathConstants.NODE);
                if (node != null) {
                    dimension.setDefault(new UID(node.getTextContent()));
                } else {
                    dimension.setDesc("");
                }

                path = "hierarchies/hierarchy";

                NodeList hierarchies = (NodeList)xpath.evaluate(path,
                    dimensions.item(d), XPathConstants.NODESET);

                ArrayList<UID> ary_hierarchies = new ArrayList<UID>();

                for (int h = 0; h < hierarchies.getLength(); h++) {
                    Hierarchy hierarchy = new Hierarchy();

                    node = (Node)xpath.evaluate("uid",
                        hierarchies.item(h), XPathConstants.NODE);
                    hierarchy.setUID(new UID(node.getTextContent()));

                    node = (Node)xpath.evaluate("name",
                        hierarchies.item(h), XPathConstants.NODE);
                    hierarchy.setName(node.getTextContent());

                    node = (Node)xpath.evaluate("desc",
                        hierarchies.item(h), XPathConstants.NODE);
                    if (node != null) {
                        hierarchy.setDesc(node.getTextContent());
                    } else {
                        hierarchy.setDesc("");
                    }

                    hierarchy.setDefault(dimension.getUID());

                    path = "levels/level";

                    NodeList levels = (NodeList)xpath.evaluate(path,
                        hierarchies.item(h), XPathConstants.NODESET);

                    ArrayList<UID> ary_levels = new ArrayList<UID>();

                    for (int l = 0; l < levels.getLength(); l++) {
                        Level level = new Level();

                        node = (Node)xpath.evaluate("uid",
                            levels.item(h), XPathConstants.NODE);
                        level.setUID(new UID(node.getTextContent()));

                        node = (Node)xpath.evaluate("name",
                            levels.item(h), XPathConstants.NODE);
                        level.setName(node.getTextContent());

                        node = (Node)xpath.evaluate("desc",
                            levels.item(l), XPathConstants.NODE);
                        if (node != null) {
                            level.setDesc(node.getTextContent());
                        } else {
                            level.setDesc("");
                        }

                        node = (Node)xpath.evaluate("field",
                            levels.item(l), XPathConstants.NODE);
                        level.setField(node.getTextContent());

                        node = (Node)xpath.evaluate("dtype",
                            levels.item(l), XPathConstants.NODE);
                        level.setDType(node.getTextContent());

                        node = (Node)xpath.evaluate("default",
                            levels.item(l), XPathConstants.NODE);
                        level.setDefault(new UID(node.getTextContent()));

                        level.setDimension(dimension.getUID());
                        level.setHierarchy(hierarchy.getUID());

                        // prevlevel
                        // nextlevel

                        path = "members/member";

                        NodeList members = (NodeList)xpath.evaluate(path,
                            levels.item(l), XPathConstants.NODESET);

                        ArrayList<UID> ary_members = new ArrayList<UID>();

                        for (int m = 0; m < members.getLength(); m++) {
                            Member member = new Member();

                            node = (Node)xpath.evaluate("uid",
                                members.item(m), XPathConstants.NODE);
                            member.setUID(new UID(node.getTextContent()));

                            node = (Node)xpath.evaluate("name",
                                members.item(m), XPathConstants.NODE);
                            member.setName(node.getTextContent());

                            node = (Node)xpath.evaluate("desc",
                                members.item(m), XPathConstants.NODE);
                            if (node != null) {
                                member.setDesc(node.getTextContent());
                            } else {
                                member.setDesc("");
                            }

                            node = (Node)xpath.evaluate("item",
                                members.item(m), XPathConstants.NODE);
                            member.setItem(node.getTextContent());

                            member.setDimension(dimension.getUID());
                            member.setHierarchy(hierarchy.getUID());
                            member.setLevel(level.getUID());

                            // prevmember
                            // nextmember

                            path = "children/child";

                            NodeList children = (NodeList)xpath.evaluate(path,
                                members.item(m), XPathConstants.NODESET);

                            ArrayList<UID> ary_children = new ArrayList<UID>();

                            for (int c = 0; c < children.getLength(); c++) {
                                ary_children.add(new UID(children.item(c).getTextContent()));
                            }

                            member.setChildren(ary_children);
                            ary_members.add(member.getUID());

                            updateTypes(EltType.MDX_MEMBER, member);
                            updateNames(member);
                            updateCache(member);
                        }

                        level.setMembers(ary_members);
                        ary_levels.add(level.getUID());

                        updateTypes(EltType.MDX_LEVEL, level);
                        updateNames(level);
                        updateCache(level);
                    }

                    hierarchy.setLevels(ary_levels);
                    ary_hierarchies.add(hierarchy.getUID());

                    updateTypes(EltType.MDX_HIERARCHY, hierarchy);
                    updateNames(hierarchy);
                    updateCache(hierarchy);
                }

                dimension.setHierarchies(ary_hierarchies);

                updateTypes(EltType.MDX_DIMENSION, dimension);
                updateNames(dimension);
                updateCache(dimension);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateTypes(EltType type, Meta elt) {
        ArrayList<UID> list;

        list = m_types.get(type);
        list.add(elt.getUID());
        m_types.put(type, list);
    }

    private void updateNames(Meta elt) {
        String name = elt.getName();
        ArrayList<UID> list;

        if (m_names.containsKey(name)) {
            list = m_names.get(name);
        } else {
            list = new ArrayList<UID>();
        }

        list.add(elt.getUID());
        m_names.put(name, list);
    }

    private void updateCache(Meta elt) {
        m_cache.put(elt.getUID(), elt);
    }

    public Meta getElement(UID uid) {
        return m_cache.get(uid);
    }

    public DatabaseInfo getDatabaseInfo() {
        return m_info;
    }

    public static void main(String[] args) {
        Metadata meta = new Metadata();
        meta.loadMetadata("../opt/metadata.xml");
    }
}

