package id.tech.cakra.ecfcoresvc.repository;
import id.tech.cakra.ecfcoresvc.domain.CampaignCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CampaignCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignCategoryRepository extends JpaRepository<CampaignCategory, Long> {

}
