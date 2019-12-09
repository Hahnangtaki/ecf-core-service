package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CampaignTransactionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampaignTransactionDTO.class);
        CampaignTransactionDTO campaignTransactionDTO1 = new CampaignTransactionDTO();
        campaignTransactionDTO1.setId(1L);
        CampaignTransactionDTO campaignTransactionDTO2 = new CampaignTransactionDTO();
        assertThat(campaignTransactionDTO1).isNotEqualTo(campaignTransactionDTO2);
        campaignTransactionDTO2.setId(campaignTransactionDTO1.getId());
        assertThat(campaignTransactionDTO1).isEqualTo(campaignTransactionDTO2);
        campaignTransactionDTO2.setId(2L);
        assertThat(campaignTransactionDTO1).isNotEqualTo(campaignTransactionDTO2);
        campaignTransactionDTO1.setId(null);
        assertThat(campaignTransactionDTO1).isNotEqualTo(campaignTransactionDTO2);
    }
}
