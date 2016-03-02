package com.jms;

import java.io.*;
import java.util.Hashtable;

import javax.jms.*;
import javax.naming.*;

public class Sender {

 // Defines the JNDI context factory.  
    public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";  
    // Defines the JNDI provider url.  
    public final static String PROVIDER_URL = "t3://localhost:7001";  
    // Defines the JMS connection factory for the queue.  
    public final static String JMS_FACTORY = "javax.jms.QueueConnectionFactory";  
    // Defines the queue 用的是对应 QUEUE的JNDI名子  
    public final static String QUEUE = "testqueue";   
    // 定义主题发布者(JNDI名称)  
    public final static String TOPIC = "testtopic";   
    public final static String DURABLE_SUB ="MySub";  
    private static Context context=null;  
    
    public static void main(String[] args) {

        new Sender().send();

    }

    public void send() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));

        try {

            // Prompt for JNDI names

//            System.out.println("Enter ConnectionFactory name:");

//            String factoryName = reader.readLine();
            String factoryName = "connectionFactory";

//            System.out.println("Enter Destination name:");

//            String destinationName = reader.readLine();
            String destinationName = "destination";

            // Look up administered objects

            Hashtable<String, String> env = new Hashtable<String, String>();  
            env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);  
            env.put(Context.PROVIDER_URL, PROVIDER_URL); 
                    
            InitialContext initContext = new InitialContext(env);

            ConnectionFactory factory =

            (ConnectionFactory) initContext.lookup(factoryName);

            Destination destination = (Destination) initContext
                    .lookup(destinationName);

            initContext.close();

            // Create JMS objects

            Connection connection = factory.createConnection();

            Session session =

            connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer sender = session.createProducer(destination);

            // Send messages

            String messageText = null;

            while (true) {

                System.out.println("Enter message to send or 'quit':");

                messageText = reader.readLine();

                if ("quit".equals(messageText))

                    break;

                TextMessage message = session.createTextMessage(messageText);

                sender.send(message);

            }

            // Exit

            System.out.println("Exiting…");

            reader.close();

            connection.close();

            System.out.println("Goodbye!");

        } catch (Exception e) {

            e.printStackTrace();

            System.exit(1);

        }

    }

}
