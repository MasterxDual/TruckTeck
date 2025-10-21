package ar.edu.iua.TruckTeck.integration.sap.model.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.iua.TruckTeck.model.Order;
import ar.edu.iua.TruckTeck.model.business.exceptions.BusinessException;
import ar.edu.iua.TruckTeck.model.business.exceptions.FoundException;
import ar.edu.iua.TruckTeck.model.business.exceptions.NotFoundException;
import ar.edu.iua.TruckTeck.model.persistence.OrderRepository;

public class OrderBusinessSap implements IOrderBusinessSap {
    @Autowired
    private OrderRepository orderDAO;


    
    
}
