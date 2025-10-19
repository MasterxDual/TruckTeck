package ar.edu.iua.TruckTeck.model.enums;

public enum OrderState {
    PENDING,            // 1 - Pendiente de pesaje inicial
    TARA_REGISTERED,    // 2 - Con pesaje inicial registrado
    LOADING,            // 3 - En carga
    FINALIZED;           // 4 - Finalizada

    public boolean canTransitionTo(OrderState next) {
        return switch (this) {
            case PENDING -> next == TARA_REGISTERED;
            case TARA_REGISTERED -> next == LOADING;
            case LOADING -> next == FINALIZED;
            case FINALIZED -> false;
        };
    }
}