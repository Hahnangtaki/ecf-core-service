package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountMemberDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountMemberDTO.class);
        AccountMemberDTO accountMemberDTO1 = new AccountMemberDTO();
        accountMemberDTO1.setId(1L);
        AccountMemberDTO accountMemberDTO2 = new AccountMemberDTO();
        assertThat(accountMemberDTO1).isNotEqualTo(accountMemberDTO2);
        accountMemberDTO2.setId(accountMemberDTO1.getId());
        assertThat(accountMemberDTO1).isEqualTo(accountMemberDTO2);
        accountMemberDTO2.setId(2L);
        assertThat(accountMemberDTO1).isNotEqualTo(accountMemberDTO2);
        accountMemberDTO1.setId(null);
        assertThat(accountMemberDTO1).isNotEqualTo(accountMemberDTO2);
    }
}
