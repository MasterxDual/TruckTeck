package ar.edu.iua.TruckTeck.integration.tms.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.iua.TruckTeck.controllers.Constants;
import ar.edu.iua.TruckTeck.integration.tms.model.business.IOrderTmsBusiness;
import ar.edu.iua.TruckTeck.model.Order;
import ar.edu.iua.TruckTeck.model.business.exceptions.BusinessException;
import ar.edu.iua.TruckTeck.model.business.exceptions.NotFoundException;
import ar.edu.iua.TruckTeck.util.IStandardResponseBusiness;


@RestController
@RequestMapping(value = Constants.URL_TMS, produces = MediaType.APPLICATION_JSON_VALUE)
public class TmsRestController {

    private static final Logger log = LoggerFactory.getLogger(TmsRestController.class);

    /**
     * Servicio de negocio para operaciones TMS.
     */
    @Autowired
    private IOrderTmsBusiness orderTmsBusiness;

    /**
     * Utilidad para construir respuestas estándar de error.
     */
    @Autowired
    private IStandardResponseBusiness standardResponse;

    /**
     * Registra el pesaje inicial de un camión vacío.
     * <p>
     * <b>Endpoint:</b> {@code POST /api/v1/tms/weighing/initial}
     * </p>
     * 
     * @param number El dominio (patente) del camión.
     * @param weight El peso inicial (tara) del camión.
     * @return Una respuesta HTTP con el estado de la operación.
     */
    @PostMapping(value = "b2b/weighing/initial")
    //public ResponseEntity<?> registerInitialWeighing(@PathVariable String number, @PathVariable Double weight) {
    public ResponseEntity<?> registerInitialWeighing(@RequestBody Order orderBody) {
        String number = orderBody.getNumber();
        Double initialWeight = orderBody.getInitialWeight();

        try {

            log.info("TMS API: Recibiendo pesaje inicial para camión: {}, peso: {}", number, initialWeight);

            // Registrar el pesaje inicial en la capa de negocio
            Order order = orderTmsBusiness.registerInitialWeighing(number, initialWeight);

            log.info("TMS API: Pesaje inicial registrado. Orden ID: {}, Código: {}", order.getId(), order.getActivationCode());

            // Respuesta mínima: devolvemos únicamente la ubicación del recurso (orden)
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location", Constants.URL_ORDERS + "/" + order.getId());
            return new ResponseEntity<>(responseHeaders, HttpStatus.OK);

        } catch (NotFoundException e) {
            log.warn("TMS API: {}", e.getMessage());
            return new ResponseEntity<>(
                standardResponse.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
                HttpStatus.NOT_FOUND
            );
        } catch (BusinessException e) {
            log.error("TMS API: Error de negocio: {}", e.getMessage());
            return new ResponseEntity<>(standardResponse.build(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("TMS API: Error interno al registrar pesaje inicial", e);
            return new ResponseEntity<>(
                standardResponse.build(HttpStatus.INTERNAL_SERVER_ERROR, e, "Error interno del servidor"),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    /**
     * Registra el pesaje final de un camión cargado.
     * <p>
     * <b>Endpoint:</b> {@code POST /api/v1/tms/weighing/final}
     * </p>
     * 
     * @param activationCode El código de activación de 5 dígitos.
     * @param weight El peso final del camión cargado.
     * @return Una respuesta HTTP con el estado de la operación.
     */
//     @PostMapping(value = "/weighing/final")
//     public ResponseEntity<?> registerFinalWeighing(@RequestParam String activationCode, @RequestParam Double weight) {
//         try {
//             // log.info("TMS API: Recibiendo pesaje final para código: {}, peso: {}", activationCode, weight);

//             // Registrar el pesaje final en la capa de negocio
//             Order order = orderTmsBusiness.registerFinalWeighing(activationCode, weight);

//             // Calcular datos de conciliación
//             if (order.getInitialWeight() != null && order.getFinalWeight() != null) {
//                 Double netWeight = order.getFinalWeight() - order.getInitialWeight();
//                 Double accumulatedMass = order.getAccumulatedMass() != null ? order.getAccumulatedMass() : 0.0;
//                 Double balanceDifference = netWeight - accumulatedMass;

//                 // log.info("TMS API: Pesaje final registrado. Orden ID: {}, Diferencia balanza-caudalímetro: {} kg", order.getId(), balanceDifference);
//             }

//             // Devolvemos la orden completa en la respuesta
//             HttpHeaders responseHeaders = new HttpHeaders();
//             responseHeaders.set("location", Constants.URL_ORDERS + "/" + order.getId());
//             return new ResponseEntity<>(order, responseHeaders, HttpStatus.OK);

//         } catch (NotFoundException e) {
//             // log.warn("TMS API: {}", e.getMessage());
//             return new ResponseEntity<>(
//                 standardResponse.build(HttpStatus.NOT_FOUND, e, e.getMessage()),
//                 HttpStatus.NOT_FOUND
//             );
//         } catch (BusinessException e) {
//             // log.error("TMS API: Error de negocio: {}", e.getMessage());
//             return new ResponseEntity<>(
//                 standardResponse.build(HttpStatus.BAD_REQUEST, e, e.getMessage()),
//                 HttpStatus.BAD_REQUEST
//             );
//         } catch (Exception e) {
//             // log.error("TMS API: Error interno al registrar pesaje final", e);
//             return new ResponseEntity<>(
//                 standardResponse.build(HttpStatus.INTERNAL_SERVER_ERROR, e, "Error interno del servidor"),
//                 HttpStatus.INTERNAL_SERVER_ERROR
//             );
//         }
//     }
}
