package com.example.projektKoncowy.repository;

import com.example.projektKoncowy.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {


/*    List<Order> findByDeliveryZip(String deliveryZIP);

    List<Order> readOrdersByDeliveryZipAndPlacedAtBetween
            (String deliveryZip, Date startDate, Date endDate);

    List<Order> findByDeliveryToAndDeliverCityAllIgnoresCase(String deliveryTo, String deliveryCity);

    List<Order> findByDeliveryCityOrOrderByDeliveryTo(String city);*/

/*    @Query("Order o where o.deliveryCity = 'Gliwice'")
    List<Order> readOrdersDeliveredInSeattle();*/

}
