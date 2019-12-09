package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountAuthorizeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountAuthorize.class);
        AccountAuthorize accountAuthorize1 = new AccountAuthorize();
        accountAuthorize1.setId(1L);
        AccountAuthorize accountAuthorize2 = new AccountAuthorize();
        accountAuthorize2.setId(accountAuthorize1.getId());
        assertThat(accountAuthorize1).isEqualTo(accountAuthorize2);
        accountAuthorize2.setId(2L);
        assertThat(accountAuthorize1).isNotEqualTo(accountAuthorize2);
        accountAuthorize1.setId(null);
        assertThat(accountAuthorize1).isNotEqualTo(accountAuthorize2);
    }
}
