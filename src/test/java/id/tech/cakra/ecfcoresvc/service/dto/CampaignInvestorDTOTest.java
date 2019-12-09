package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CampaignInvestorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignInvestorDTO.class);
        CampaignInvestorDTO campaignInvestorDTO1 = new CampaignInvestorDTO();
        campaignInvestorDTO1.setId(1L);
        CampaignInvestorDTO campaignInvestorDTO2 = new CampaignInvestorDTO();
        assertThat(campaignInvestorDTO1).isNotEqualTo(campaignInvestorDTO2);
        campaignInvestorDTO2.setId(campaignInvestorDTO1.getId());
        assertThat(campaignInvestorDTO1).isEqualTo(campaignInvestorDTO2);
        campaignInvestorDTO2.setId(2L);
        assertThat(campaignInvestorDTO1).isNotEqualTo(campaignInvestorDTO2);
        campaignInvestorDTO1.setId(null);
        assertThat(campaignInvestorDTO1).isNotEqualTo(campaignInvestorDTO2);
    }
}
