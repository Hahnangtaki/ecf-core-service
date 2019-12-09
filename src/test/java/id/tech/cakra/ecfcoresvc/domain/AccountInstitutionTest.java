package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountInstitutionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountInstitution.class);
        AccountInstitution accountInstitution1 = new AccountInstitution();
        accountInstitution1.setId(1L);
        AccountInstitution accountInstitution2 = new AccountInstitution();
        accountInstitution2.setId(accountInstitution1.getId());
        assertThat(accountInstitution1).isEqualTo(accountInstitution2);
        accountInstitution2.setId(2L);
        assertThat(accountInstitution1).isNotEqualTo(accountInstitution2);
        accountInstitution1.setId(null);
        assertThat(accountInstitution1).isNotEqualTo(accountInstitution2);
    }
}
