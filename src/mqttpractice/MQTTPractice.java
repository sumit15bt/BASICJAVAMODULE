package mqttpractice;

import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTPractice {

    public static void main(String[] args) throws MqttException {
        // TODO code application logic here
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                // do your work
                try {
                    MqttClient client = new MqttClient("tcp://localhost:1883", "myclient1");
                    client.setCallback(new SimpleMqttCallBack());
                    client.connect();
                    client.subscribe("mqttData");
                    MqttMessage message = new MqttMessage();
                    message.setPayload("Hello World from Java".getBytes());
                    client.publish("mqttData", message);

                    MqttMessage message1 = new MqttMessage();
                    message1.setPayload("Hello World from Java 2 ".getBytes());
                    client.publish("mqttData", message1);
                    client.disconnect();
                } catch (Exception ex) {
                    System.out.println("ERROR :" + ex);
                }
            }
        }, 0, (1000 * 1));

    }

}
