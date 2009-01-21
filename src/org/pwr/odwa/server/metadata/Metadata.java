package org.pwr.odwa.server.metadata;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.xpath.*;

import org.xml.sax.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

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
                if (node != null) {
                    dimension.setDesc(node.getTextContent());
                } else {
                    dimension.setDesc("");
                }

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
                    dimension.setDefault(null);
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

                    node = (Node)xpath.evaluate("default",
                        hierarchies.item(h), XPathConstants.NODE);
                    if (node != null) {
                        hierarchy.setDefault(new UID(node.getTextContent()));
                    } else {
                        hierarchy.setDefault(null);
                    }

                    path = "levels/level";

                    NodeList levels = (NodeList)xpath.evaluate(path,
                        hierarchies.item(h), XPathConstants.NODESET);

                    ArrayList<UID> ary_levels = new ArrayList<UID>();

                    for (int l = 0; l < levels.getLength(); l++) {
                        Level level = new Level();

                        node = (Node)xpath.evaluate("uid",
                            levels.item(l), XPathConstants.NODE);
                        level.setUID(new UID(node.getTextContent()));

                        node = (Node)xpath.evaluate("name",
                            levels.item(l), XPathConstants.NODE);
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
                        if (node != null) {
                            level.setDefault(new UID(node.getTextContent()));
                        } else {
                            level.setDefault(null);
                        }

                        level.setDimension(dimension.getUID());
                        level.setHierarchy(hierarchy.getUID());

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

    public String getMetadataTree() {
        try {
            DocumentBuilderFactory doc_factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder doc_builder = doc_factory.newDocumentBuilder();

            Document doc = doc_builder.newDocument();

            Element root = doc.createElement("metadata");
            doc.appendChild(root);

            for (UID d_uid : m_types.get(EltType.MDX_DIMENSION)) {
                Dimension d_elt = (Dimension)m_cache.get(d_uid);

                Element d_node = doc.createElement("dimension");
                root.appendChild(d_node);

                Element d__uid_node = doc.createElement("uid");
                Element d_name_node = doc.createElement("name");
                Element d_desc_node = doc.createElement("desc");

                Text d__uid_data = doc.createTextNode(d_elt.getUID().toString());
                Text d_name_data = doc.createTextNode(d_elt.getName());
                Text d_desc_data = doc.createTextNode(d_elt.getDesc());

                d__uid_node.appendChild(d__uid_data);
                d_name_node.appendChild(d_name_data);
                d_desc_node.appendChild(d_desc_data);

                d_node.appendChild(d__uid_node);
                d_node.appendChild(d_name_node);
                d_node.appendChild(d_desc_node);

                Element d_hierarchies = doc.createElement("hierarchies");

                for (UID h_uid : d_elt.getHierarchies()) {
                    Hierarchy h_elt = (Hierarchy)m_cache.get(h_uid);

                    Element h_node = doc.createElement("hierarchy");

                    Element h__uid_node = doc.createElement("uid");
                    Element h_name_node = doc.createElement("name");
                    Element h_desc_node = doc.createElement("desc");

                    Text h__uid_data = doc.createTextNode(h_elt.getUID().toString());
                    Text h_name_data = doc.createTextNode(h_elt.getName());
                    Text h_desc_data = doc.createTextNode(h_elt.getDesc());

                    h__uid_node.appendChild(h__uid_data);
                    h_name_node.appendChild(h_name_data);
                    h_desc_node.appendChild(h_desc_data);

                    h_node.appendChild(h__uid_node);
                    h_node.appendChild(h_name_node);
                    h_node.appendChild(h_desc_node);

                    Element h_levels = doc.createElement("members");

                    for (UID l_uid : h_elt.getLevels()) {
                        Level l_elt = (Level)m_cache.get(l_uid);

                        Element l_node = doc.createElement("level");

                        Element l__uid_node = doc.createElement("uid");
                        Element l_name_node = doc.createElement("name");
                        Element l_desc_node = doc.createElement("desc");

                        Text l__uid_data = doc.createTextNode(l_elt.getUID().toString());
                        Text l_name_data = doc.createTextNode(l_elt.getName());
                        Text l_desc_data = doc.createTextNode(l_elt.getDesc());

                        l__uid_node.appendChild(l__uid_data);
                        l_name_node.appendChild(l_name_data);
                        l_desc_node.appendChild(l_desc_data);

                        l_node.appendChild(l__uid_node);
                        l_node.appendChild(l_name_node);
                        l_node.appendChild(l_desc_node);

                        Element l_members = doc.createElement("members");

                        for (UID m_uid : l_elt.getMembers()) {
                            Member m_elt = (Member)m_cache.get(m_uid);

                            Element m_node = doc.createElement("member");

                            Element m__uid_node = doc.createElement("uid");
                            Element m_name_node = doc.createElement("name");
                            Element m_desc_node = doc.createElement("desc");

                            Text m__uid_data = doc.createTextNode(m_elt.getUID().toString());
                            Text m_name_data = doc.createTextNode(m_elt.getName());
                            Text m_desc_data = doc.createTextNode(m_elt.getDesc());

                            m__uid_node.appendChild(m__uid_data);
                            m_name_node.appendChild(m_name_data);
                            m_desc_node.appendChild(m_desc_data);

                            m_node.appendChild(m__uid_node);
                            m_node.appendChild(m_name_node);
                            m_node.appendChild(m_desc_node);

                            l_members.appendChild(m_node);
                        }

                        l_node.appendChild(l_members);
                        h_levels.appendChild(l_node);
                    }

                    h_node.appendChild(h_levels);
                    d_hierarchies.appendChild(h_node);
                }

                d_node.appendChild(d_hierarchies);
            }

            TransformerFactory trans_factory = TransformerFactory.newInstance();
            Transformer trans = trans_factory.newTransformer();

            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);

            trans.transform(source, sr);

            return sw.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
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
        System.out.println(meta.getMetadataTree());
    }
}
