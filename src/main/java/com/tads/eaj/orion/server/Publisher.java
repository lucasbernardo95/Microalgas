/*
 * Aplicação para publicar dados na rede para os sensores da apicação Orion.
 */
package com.tads.eaj.orion.server;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 *
 * @author Aluno
 */
public class Publisher {

    public static void main(String[] args) {
        String topic = /*"temp/random"*/ "MQTT Examples";    //tópico que a aplicação irá publicar
        String content = "exemplo java";           //conteúdo da mensagem a ser publicada
        String broker = "tcp://iot.eclipse.org:1883";           //endereço do broker que será instalado no meu windows
        String clientID = "JavaSample"; // ID do cliente
        int qos = 2;

        MemoryPersistence persistence = new MemoryPersistence();

        try {
            //instancia um cliente passando o servidor, idcliente e persistência
            MqttClient cliente = new MqttClient(broker, clientID, persistence);
            MqttConnectOptions conOptions = new MqttConnectOptions();

            conOptions.setCleanSession(true);

            System.out.println("Conectando ao broker: " + broker + "\n");
            //conecta o cliente
            cliente.connect(conOptions);
            System.out.println(
                    "Conectado!\n"
                    + "Publicando mensagem: " + content);

            //informa a quantidade de Bytes que a mensagem irá conter
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);

            //publica a mensagem no tópico escolhido
            cliente.publish(topic, message);
            //cliente.subscribe(topic);
            System.out.println("Mensagem publicada");

            //desconecta o cliente para haver economia de recursos
            cliente.disconnect();
            System.out.println("Desconectado");
            System.exit(0);

        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }

    }
}
