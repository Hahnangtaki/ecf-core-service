package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountIndividuTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountIndividu.class);
        AccountIndividu accountIndividu1 = new AccountIndividu();
        accountIndividu1.setId(1L);
        AccountIndividu accountIndividu2 = new AccountIndividu();
        accountIndividu2.setId(accountIndividu1.getId());
        assertThat(accountIndividu1).isEqualTo(accountIndividu2);
        accountIndividu2.setId(2L);
        assertThat(accountIndividu1).isNotEqualTo(accountIndividu2);
        accountIndividu1.setId(null);
        assertThat(accountIndividu1).isNotEqualTo(accountIndividu2);
    }
}
