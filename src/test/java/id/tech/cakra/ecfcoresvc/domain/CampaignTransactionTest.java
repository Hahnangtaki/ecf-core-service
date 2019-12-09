package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CampaignTransactionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignTransaction.class);
        CampaignTransaction campaignTransaction1 = new CampaignTransaction();
        campaignTransaction1.setId(1L);
        CampaignTransaction campaignTransaction2 = new CampaignTransaction();
        campaignTransaction2.setId(campaignTransaction1.getId());
        assertThat(campaignTransaction1).isEqualTo(campaignTransaction2);
        campaignTransaction2.setId(2L);
        assertThat(campaignTransaction1).isNotEqualTo(campaignTransaction2);
        campaignTransaction1.setId(null);
        assertThat(campaignTransaction1).isNotEqualTo(campaignTransaction2);
    }
}
