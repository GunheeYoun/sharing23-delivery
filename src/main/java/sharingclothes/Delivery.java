package sharingclothes;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Delivery_table")
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private String status;

    @PostPersist
    public void onPostPersist(){
        Shipped shipped = new Shipped();
        BeanUtils.copyProperties(this, shipped);
        System.out.println("shipped 테스트");
        System.out.println("shipped 테스트 getId : " +shipped.getId());
        System.out.println("shipped 테스트 getOrderId : " +shipped.getOrderId());
        System.out.println("shipped 테스트 getStatus : " +shipped.getStatus());
        shipped.publishAfterCommit();
        System.out.println("shipped after 테스트  : " +"publishAfterCommit 후");

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
