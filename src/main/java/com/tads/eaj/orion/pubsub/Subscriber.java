/*
 * Objeto que representa um assinante da rede. 
 * Tem a função declarar interesse sobre um tópico específico e receber
 * as mewnsagens publicadas no tópico de interesse.
 * O método callback da classe SimpleCallback ouve a rede e sempre que ouver 
 * um novo dado publicado neste tópico o mosquitto notifica e o subscriber 
 * recebe a mensagem.
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
public class Subscriber {

    public static void main(String[] args) {

        String topic = "orion/energyPolicy";
        String content = "level 4";
        int qos = 2;
        String broker = "tcp://localhost:1883";
        String clientId = "node id 1";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient cliente = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            System.out.println("Connecting to broker: " + broker);
            cliente.connect(connOpts);

            /*se inscrve para receber as mensagens do tópico "#" todos*/
            cliente.subscribe("orion/energyPolicy", 1);
            System.out.println("Connected");

            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
            //informa quem é o método callblack para ficar ouvindo a rede para 
            //saber quando há mensagens do interesse 'tópico'
            cliente.setCallback(new SimpleCallback());

            try {
                Thread.sleep(5000);
                //cliente.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Disconnected");
            System.exit(0);
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("except " + me);
            me.printStackTrace();
        }

    }
}
