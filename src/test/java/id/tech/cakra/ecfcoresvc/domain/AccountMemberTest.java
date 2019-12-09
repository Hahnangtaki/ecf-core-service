package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountMemberTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountMember.class);
        AccountMember accountMember1 = new AccountMember();
        accountMember1.setId(1L);
        AccountMember accountMember2 = new AccountMember();
        accountMember2.setId(accountMember1.getId());
        assertThat(accountMember1).isEqualTo(accountMember2);
        accountMember2.setId(2L);
        assertThat(accountMember1).isNotEqualTo(accountMember2);
        accountMember1.setId(null);
        assertThat(accountMember1).isNotEqualTo(accountMember2);
    }
}
