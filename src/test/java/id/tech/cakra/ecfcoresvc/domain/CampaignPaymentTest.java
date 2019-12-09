package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CampaignPaymentTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignPayment.class);
        CampaignPayment campaignPayment1 = new CampaignPayment();
        campaignPayment1.setId(1L);
        CampaignPayment campaignPayment2 = new CampaignPayment();
        campaignPayment2.setId(campaignPayment1.getId());
        assertThat(campaignPayment1).isEqualTo(campaignPayment2);
        campaignPayment2.setId(2L);
        assertThat(campaignPayment1).isNotEqualTo(campaignPayment2);
        campaignPayment1.setId(null);
        assertThat(campaignPayment1).isNotEqualTo(campaignPayment2);
    }
}
