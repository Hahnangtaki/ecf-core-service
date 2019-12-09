package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountAddressDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAddressDTO.class);
        AccountAddressDTO accountAddressDTO1 = new AccountAddressDTO();
        accountAddressDTO1.setId(1L);
        AccountAddressDTO accountAddressDTO2 = new AccountAddressDTO();
        assertThat(accountAddressDTO1).isNotEqualTo(accountAddressDTO2);
        accountAddressDTO2.setId(accountAddressDTO1.getId());
        assertThat(accountAddressDTO1).isEqualTo(accountAddressDTO2);
        accountAddressDTO2.setId(2L);
        assertThat(accountAddressDTO1).isNotEqualTo(accountAddressDTO2);
        accountAddressDTO1.setId(null);
        assertThat(accountAddressDTO1).isNotEqualTo(accountAddressDTO2);
    }
}
