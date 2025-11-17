package ar.edu.iua.TruckTeck.auth.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.TruckTeck.auth.model.Role;

/**
 * Interfaz de repositorio para realizar operaciones CRUD sobre entidades {@link Role}.
 *
 * <p>
 * Esta interfaz extiende {@link JpaRepository}, lo que proporciona métodos estándar para
 * guardar, actualizar, eliminar y recuperar instancias de {@link Role} desde la base de datos.
 * Aprovecha la abstracción de repositorios de Spring Data JPA, reduciendo la necesidad 
 * de escribir código repetitivo.
 * </p>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{ 
    // No se requieren métodos adicionales; JpaRepository provee las operaciones CRUD estándar.
}