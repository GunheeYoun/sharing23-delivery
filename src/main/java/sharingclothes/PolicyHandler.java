package sharingclothes;

import sharingclothes.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{

    //추가
    @Autowired
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Ship(@Payload Ordered ordered){
    // Ordered 객체 받아서
        if(ordered.isMe()){
            //추가시작
            // 배송업체와 전문교환
            // 고객에게 SNS ('배송시작됨' 알림)
            // 배송 Aggregate에 Record 등록
            System.out.println("배송중 테스트 로그");

            Delivery delivery = new Delivery();
            System.out.println("##### 배송중 테스트 로그 : " + ordered.getId());
            delivery.setOrderId(ordered.getId());
            delivery.setStatus("Shipped");

            deliveryRepository.save(delivery);
            //추가끝
            System.out.println("##### listener Ship : " + ordered.toJson());
        }
    }

}
