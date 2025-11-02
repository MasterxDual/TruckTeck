package ar.edu.iua.TruckTeck.model.persistence;

import ar.edu.iua.TruckTeck.model.OrderDetail;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    
    @Query("SELECT od.timestamp; FROM OrderDetail od WHERE od.order.id = :orderId ORDER BY od.timestamp DESC")
    LocalDateTime findLastTimestampByOrderId(@Param("orderId") Long orderId);

}