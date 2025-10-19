package ar.edu.iua.TruckTeck.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.iua.TruckTeck.model.Order;

/**
 * Repositorio para la entidad {@link Order}.
 * Proporciona operaciones CRUD básicas y algunas consultas frecuentes.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Busca una orden por su número (clave primaria en la entidad Order).
     * @param number número de la orden
     * @return Optional con la orden si existe
     */
    @Query("SELECT o FROM Order o WHERE o.number = :number")
    Optional<Order> findByNumber(long number);
}

