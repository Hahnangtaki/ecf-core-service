package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountAuthorizeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAuthorizeDTO.class);
        AccountAuthorizeDTO accountAuthorizeDTO1 = new AccountAuthorizeDTO();
        accountAuthorizeDTO1.setId(1L);
        AccountAuthorizeDTO accountAuthorizeDTO2 = new AccountAuthorizeDTO();
        assertThat(accountAuthorizeDTO1).isNotEqualTo(accountAuthorizeDTO2);
        accountAuthorizeDTO2.setId(accountAuthorizeDTO1.getId());
        assertThat(accountAuthorizeDTO1).isEqualTo(accountAuthorizeDTO2);
        accountAuthorizeDTO2.setId(2L);
        assertThat(accountAuthorizeDTO1).isNotEqualTo(accountAuthorizeDTO2);
        accountAuthorizeDTO1.setId(null);
        assertThat(accountAuthorizeDTO1).isNotEqualTo(accountAuthorizeDTO2);
    }
}
