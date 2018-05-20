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
    /**
     * Método responsável por publicar uma política periódicamente sempre que o 
     * tempo definido no parâmetro se esgotar
     * @param politica política a ser aplicada nos nodes da rede
     * @param periodo tempo máximo em ML para repetir o processo de publicação
     */

    public static void publicarPorPeriodo(String politica, Integer periodo) {
        try {
            publicar(politica);
            Thread.sleep(periodo); //exm.: 1000 = 1s
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Método responsável por publicar a política a ser aplicada ao node que
     * mandou a mensagem contendo o dado de energia.
     *
     * O tópico publicado sera "idNode/política ex.: ESP1/DeepSleep"
     *
     * @param politica a ser aplicada
     */
    public static void publicar(String politica) {
        System.out.println("publisher!!!");
//        Integer tentativas = 0; //quantidade de tentativas para publicar o dado
        String topic = "mpmee/energyPolicy";
        //String content = "choro level 4";
        int qos = 2;//qos = 2 indica que vai ter confirmação em ambos os lados do recebimento da mensagem
        String broker = "tcp://m14.cloudmqtt.com:13367";//endereço do broker
        String clientId = "publisher";//id desta aplicação que está publicando (o middleware)
        MemoryPersistence persistence = new MemoryPersistence();
        String senha = "mpmeePublisher";
        String usuario = "mpmeePublisher";

        try {
            //seta as credênciais 
            MqttClient cliente = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
//            connOpts.setCleanSession(true);
            connOpts.setUserName(senha);
            connOpts.setPassword(senha.toCharArray());
            cliente.connect(connOpts);

            System.out.println("Connected to broker: " + broker);
            System.out.println("Publish message: " + politica);

            //cria a mensagem
            MqttMessage message = new MqttMessage(politica.getBytes());
            message.setQos(qos);

            //envia a mensagem 5 vezes para garantir que o nodemcu receberá a mensagem
            cliente.publish(topic, message);//publica mensagem no tópico desejado
            System.out.println("Message published => " + politica);
//                try {
//                    Thread.sleep(500);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            cliente.disconnect();

            System.out.println("Disconnected");
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
