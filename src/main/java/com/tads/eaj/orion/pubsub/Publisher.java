/*
 * Aplicação para publicar dados na rede para os sensores da apicação Orion.
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
public class Publisher {

    public static void main(String[] args) {
        
        String topic = "orion/energyPolicy";
        String content = "level 4";
        int qos = 2;
        String broker = "tcp://localhost:1883";
        String clientId = "publisher id 4";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient cliente = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            
            System.out.println("Connecting to broker: " + broker);
            cliente.connect(connOpts);
            
            System.out.println("Connected");
            System.out.println("Publish message: " + content);
            
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            //publica mensagem no tópico desejado
            cliente.publish(topic, message);
            System.out.println("Message published");
            //try {
            //    Thread.sleep(5000);
                cliente.disconnect();
            //} catch(Exception e) {
            //    e.printStackTrace();
            ///}
           // System.out.println("Disconnected");
           // System.exit(0);
        } catch(MqttException me){
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("except " + me);
            me.printStackTrace();
        }
    }
}
