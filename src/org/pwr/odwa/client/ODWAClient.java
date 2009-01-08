package org.pwr.odwa.client;

import org.pwr.odwa.common.metadata.MetaGUIApi;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.util.ClientFactory;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.net.URL;

public class ODWAClient {
    public static void main(String[] args) {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL("http://localhost:8080/xmlrpc"));
            config.setEnabledForExtensions(true);
            config.setConnectionTimeout(60000);
            config.setReplyTimeout(60000);

            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            ClientFactory factory = new ClientFactory(client);
            MetaGUIApi api = (MetaGUIApi) factory.newInstance(MetaGUIApi.class);

            Object[] slots = api.getSlots();

            System.out.println("#" + slots.length + " slots arrived:");

            for (Object sl : slots) {
                System.out.println(" " + sl.toString());
            }
        } catch (Exception exception) {
            System.err.println("ODWAClient: " + exception);
        }
    }
}

