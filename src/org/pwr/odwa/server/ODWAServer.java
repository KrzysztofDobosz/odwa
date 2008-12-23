package org.pwr.odwa.server;

import org.pwr.odwa.common.MetaGUIApi;

import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl;
import org.apache.xmlrpc.server.PropertyHandlerMapping;

import org.apache.xmlrpc.webserver.WebServer;


public class ODWAServer {
    private static int port = 8080;

    public static void main(String[] args) {
        try {
            System.out.println("Starting XML-RPC server.");
            WebServer server = new WebServer(port);

            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler(MetaGUIApi.class.getName(), MetaGUIApiImpl.class);

            XmlRpcServer xmlRpcServer = server.getXmlRpcServer();
            xmlRpcServer.setHandlerMapping(phm);

            XmlRpcServerConfigImpl config =
                (XmlRpcServerConfigImpl) xmlRpcServer.getConfig();
            config.setEnabledForExtensions(true);
            config.setContentLengthOptional(false);

            server.start();
            System.out.println("OK. Accepting requests.");
        } catch (Exception exception) {
            System.err.println("ODWAServer: " + exception);
        }
    }
}

