package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CampaignInvestorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignInvestor.class);
        CampaignInvestor campaignInvestor1 = new CampaignInvestor();
        campaignInvestor1.setId(1L);
        CampaignInvestor campaignInvestor2 = new CampaignInvestor();
        campaignInvestor2.setId(campaignInvestor1.getId());
        assertThat(campaignInvestor1).isEqualTo(campaignInvestor2);
        campaignInvestor2.setId(2L);
        assertThat(campaignInvestor1).isNotEqualTo(campaignInvestor2);
        campaignInvestor1.setId(null);
        assertThat(campaignInvestor1).isNotEqualTo(campaignInvestor2);
    }
}
