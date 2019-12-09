package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountAddressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAddress.class);
        AccountAddress accountAddress1 = new AccountAddress();
        accountAddress1.setId(1L);
        AccountAddress accountAddress2 = new AccountAddress();
        accountAddress2.setId(accountAddress1.getId());
        assertThat(accountAddress1).isEqualTo(accountAddress2);
        accountAddress2.setId(2L);
        assertThat(accountAddress1).isNotEqualTo(accountAddress2);
        accountAddress1.setId(null);
        assertThat(accountAddress1).isNotEqualTo(accountAddress2);
    }
}
