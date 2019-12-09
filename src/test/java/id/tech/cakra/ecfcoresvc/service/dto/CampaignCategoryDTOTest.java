package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CampaignCategoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignCategoryDTO.class);
        CampaignCategoryDTO campaignCategoryDTO1 = new CampaignCategoryDTO();
        campaignCategoryDTO1.setId(1L);
        CampaignCategoryDTO campaignCategoryDTO2 = new CampaignCategoryDTO();
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO2.setId(campaignCategoryDTO1.getId());
        assertThat(campaignCategoryDTO1).isEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO2.setId(2L);
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
        campaignCategoryDTO1.setId(null);
        assertThat(campaignCategoryDTO1).isNotEqualTo(campaignCategoryDTO2);
    }
}
