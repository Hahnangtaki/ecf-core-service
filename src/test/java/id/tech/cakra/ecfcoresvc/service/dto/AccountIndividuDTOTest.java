package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountIndividuDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountIndividuDTO.class);
        AccountIndividuDTO accountIndividuDTO1 = new AccountIndividuDTO();
        accountIndividuDTO1.setId(1L);
        AccountIndividuDTO accountIndividuDTO2 = new AccountIndividuDTO();
        assertThat(accountIndividuDTO1).isNotEqualTo(accountIndividuDTO2);
        accountIndividuDTO2.setId(accountIndividuDTO1.getId());
        assertThat(accountIndividuDTO1).isEqualTo(accountIndividuDTO2);
        accountIndividuDTO2.setId(2L);
        assertThat(accountIndividuDTO1).isNotEqualTo(accountIndividuDTO2);
        accountIndividuDTO1.setId(null);
        assertThat(accountIndividuDTO1).isNotEqualTo(accountIndividuDTO2);
    }
}
