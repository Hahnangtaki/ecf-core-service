package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.CampaignTransaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CampaignTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignTransactionRepository extends JpaRepository<CampaignTransaction, Long> {

}
