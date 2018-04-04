/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.eaj.orion.pubsub;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Lucas Bernardo
 */
public class Teste {

    public static void main(String[] args) {
        String topic = "orion/energyPolicy";
        String content = "choro level 5";
        int qos = 2;
        String broker = "tcp://localhost:1883";
        String clientId = "publisher 2";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient cliente = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("Connecting to broker: " + broker);
            cliente.connect(connOpts);

            //Subscriber.increver();
            System.out.println("Connected");
            System.out.println("Publish message: " + content);

            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            //publica mensagem no tópico desejado
            while (true) {
                cliente.publish(topic, message);
                System.out.println("Message published => " +content);
                try {
                    Thread.sleep(4000);
                    //cliente.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // System.out.println("Disconnected");
            // System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("except " + me);
            me.printStackTrace();
        }
        
//        p1.publicar("publisher 1");
//        p2.publicar("publisher 2");
//        
//        s.inscrver("subscriber 1");
        
//        String topic = "MQTT examples";
//        String content = "Message from MqttPublishSample";
//        int qos = 2;
//        String broker = "tcp://localhost:1883";
//        String clientId = "JavaSample";
//        MemoryPersistence persistence = new MemoryPersistence();
//
//        try {
//            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
//            MqttConnectOptions connOpts = new MqttConnectOptions();
//            connOpts.setCleanSession(true);
//            System.out.println("Connecting to broker: " + broker);
//            sampleClient.connect(connOpts);
//            sampleClient.subscribe("#", 1);
//            System.out.println("Connected");
//            System.out.println("Publish message: " + content);
//            MqttMessage message = new MqttMessage(content.getBytes());
//            message.setQos(qos);
//            sampleClient.setCallback(new SimpleCallback());
//            //sampleClient.publish(topic, message);
//            System.out.println("Message published");
//            try {
//                Thread.sleep(5000);
//                sampleClient.disconnect();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println("Disconnected");
//            System.exit(0);
//        } catch (MqttException me) {
//            System.out.println("reason " + me.getReasonCode());
//            System.out.println("msg " + me.getMessage());
//            System.out.println("loc " + me.getLocalizedMessage());
//            System.out.println("cause " + me.getCause());
//            System.out.println("except " + me);
//            me.printStackTrace();
//        }
    }
}
