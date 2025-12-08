package ar.edu.iua.TruckTeck.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.iua.TruckTeck.model.persistence.OrderDetailRepository;
import ar.edu.iua.TruckTeck.util.IStandardResponseBusiness;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(Constants.URL_ORDERS)
@Tag(name = "OrderDetail", description = "API Servicios relacionados con el Detalle de una Orden")
public class OrderDetailRestController {

    /**
     * Componente de negocio encargado de construir respuestas estándar.
     */
    @Autowired
    private IStandardResponseBusiness response;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping(value = "/detail/{id}")
    public ResponseEntity<?> list(@PathVariable long id) {
        try {
            List<Object[]> results = orderDetailRepository.findDetailsByOrderId(id);
            List<Map<String, Object>> details = new ArrayList<>();
            
            for (Object[] row : results) {
                Map<String, Object> detail = new HashMap<>();
                detail.put("id", row[0]);
                detail.put("timestamp", row[1]);
                detail.put("accumulatedMass", row[2]);
                detail.put("density", row[3]);
                detail.put("temperature", row[4]);
                detail.put("caudal", row[5]);
                details.add(detail);
            }
            
            return new ResponseEntity<>(details, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(response.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
             HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
