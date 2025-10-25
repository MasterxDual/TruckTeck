package ar.edu.iua.TruckTeck.model.enums;

public enum OrderState {
    PENDING,            // 1 - Pendiente de pesaje inicial
    TARA_REGISTERED,    // 2 - Con pesaje inicial registrado
    LOADING,            // 3 - En carga
    FINALIZED           // 4 - Finalizada
}