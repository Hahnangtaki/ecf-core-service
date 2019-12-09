package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class AccountInstitutionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountInstitutionDTO.class);
        AccountInstitutionDTO accountInstitutionDTO1 = new AccountInstitutionDTO();
        accountInstitutionDTO1.setId(1L);
        AccountInstitutionDTO accountInstitutionDTO2 = new AccountInstitutionDTO();
        assertThat(accountInstitutionDTO1).isNotEqualTo(accountInstitutionDTO2);
        accountInstitutionDTO2.setId(accountInstitutionDTO1.getId());
        assertThat(accountInstitutionDTO1).isEqualTo(accountInstitutionDTO2);
        accountInstitutionDTO2.setId(2L);
        assertThat(accountInstitutionDTO1).isNotEqualTo(accountInstitutionDTO2);
        accountInstitutionDTO1.setId(null);
        assertThat(accountInstitutionDTO1).isNotEqualTo(accountInstitutionDTO2);
    }
}
