package ar.edu.iua.TruckTeck.integration.sap.model.business;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.TruckTeck.model.business.IOrderBusiness;

@RestController
@RequestMapping(Constants.URL_ORDERS)
public interface IOrderBusinessSap extends IOrderBusiness{
    
}
