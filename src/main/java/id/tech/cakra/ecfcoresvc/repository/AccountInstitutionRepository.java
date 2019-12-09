package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.AccountInstitution;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountInstitution entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountInstitutionRepository extends JpaRepository<AccountInstitution, Long> {

}
