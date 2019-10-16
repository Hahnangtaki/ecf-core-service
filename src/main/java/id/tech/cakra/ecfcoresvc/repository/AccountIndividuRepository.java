package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.AccountIndividu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountIndividu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountIndividuRepository extends JpaRepository<AccountIndividu, Long> {

}
