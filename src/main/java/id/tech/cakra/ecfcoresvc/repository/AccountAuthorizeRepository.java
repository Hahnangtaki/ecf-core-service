package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.AccountAuthorize;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountAuthorize entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountAuthorizeRepository extends JpaRepository<AccountAuthorize, Long> {

}
