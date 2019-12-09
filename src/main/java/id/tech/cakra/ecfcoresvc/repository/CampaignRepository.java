package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.Campaign;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Campaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

}
