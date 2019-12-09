package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.AccountAddress;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AccountAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountAddressRepository extends JpaRepository<AccountAddress, Long> {

}
