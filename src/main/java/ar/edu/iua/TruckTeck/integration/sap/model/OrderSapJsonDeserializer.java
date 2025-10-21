package ar.edu.iua.TruckTeck.integration.sap.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import ar.edu.iua.TruckTeck.integration.sap.model.business.IOrderBusinessSap;
import ar.edu.iua.TruckTeck.model.Client;
import ar.edu.iua.TruckTeck.model.Driver;
import ar.edu.iua.TruckTeck.model.Order;
import ar.edu.iua.TruckTeck.util.JsonUtiles;

public class OrderSapJsonDeserializer extends StdDeserializer<Order> {
    /**
     * Constructor protegido requerido por Jackson.
     *
     * @param vc Clase de la entidad a deserializar.
     */
	protected OrderSapJsonDeserializer(Class<?> vc) {
		super(vc);
	}

    /**
     * Componente de negocio de órdenes SAP.
     * <p>
     * Se utiliza para validar y cargar la orden indicada en el JSON,
     * en caso de estar presente.
     * </p>
     */
    private IOrderBusinessSap orderBusinessSap;


    /**
     * Constructor que inicializa el deserializador con acceso al
     * componente de negocio de categorías.
     *
     * @param vc               Clase de la entidad a deserializar.
     * @param orderBusinessSap Servicio de negocio para validación de órdenes SAP.
     */
	public OrderSapJsonDeserializer(Class<?> vc, IOrderBusinessSap orderBusinessSap) {
		super(vc);
		this.orderBusinessSap = orderBusinessSap;
	}

    public Order deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JacksonException {
        Order order = new Order();
        JsonNode node = jp.getCodec().readTree(jp);
        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        
        String number = JsonUtiles.getString(node, "order,number,id,order_number,order_id,id_order,number_id".split(","), null);
        String externalCode = JsonUtiles.getString(node, "external_code,code,externalCode".split(","), null);
        
        // Deserializacion del Driver
        JsonNode driverNode = node.get("driver");
        if (driverNode != null && !driverNode.isNull()) {
            Driver driver = mapper.treeToValue(driverNode, Driver.class);
            order.setDriver(driver);
        }

        // Deserializacion del Cliente
        JsonNode clientNode = node.get("client");
        if (clientNode != null && !clientNode.isNull()) {
            Client client = mapper.treeToValue(clientNode, Client.class);
            order.setClient(client);
        }

        

    }
}
