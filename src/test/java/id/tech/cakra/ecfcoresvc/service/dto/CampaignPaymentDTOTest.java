package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CampaignPaymentDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignPaymentDTO.class);
        CampaignPaymentDTO campaignPaymentDTO1 = new CampaignPaymentDTO();
        campaignPaymentDTO1.setId(1L);
        CampaignPaymentDTO campaignPaymentDTO2 = new CampaignPaymentDTO();
        assertThat(campaignPaymentDTO1).isNotEqualTo(campaignPaymentDTO2);
        campaignPaymentDTO2.setId(campaignPaymentDTO1.getId());
        assertThat(campaignPaymentDTO1).isEqualTo(campaignPaymentDTO2);
        campaignPaymentDTO2.setId(2L);
        assertThat(campaignPaymentDTO1).isNotEqualTo(campaignPaymentDTO2);
        campaignPaymentDTO1.setId(null);
        assertThat(campaignPaymentDTO1).isNotEqualTo(campaignPaymentDTO2);
    }
}
