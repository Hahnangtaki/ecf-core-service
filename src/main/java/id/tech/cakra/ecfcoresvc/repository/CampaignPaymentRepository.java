package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.CampaignPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CampaignPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignPaymentRepository extends JpaRepository<CampaignPayment, Long> {

}
