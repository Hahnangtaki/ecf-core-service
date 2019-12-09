package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.AccountBank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountBank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountBankRepository extends JpaRepository<AccountBank, Long> {

}
