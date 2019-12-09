package id.tech.cakra.ecfcoresvc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CompanyBankDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyBankDTO.class);
        CompanyBankDTO companyBankDTO1 = new CompanyBankDTO();
        companyBankDTO1.setId(1L);
        CompanyBankDTO companyBankDTO2 = new CompanyBankDTO();
        assertThat(companyBankDTO1).isNotEqualTo(companyBankDTO2);
        companyBankDTO2.setId(companyBankDTO1.getId());
        assertThat(companyBankDTO1).isEqualTo(companyBankDTO2);
        companyBankDTO2.setId(2L);
        assertThat(companyBankDTO1).isNotEqualTo(companyBankDTO2);
        companyBankDTO1.setId(null);
        assertThat(companyBankDTO1).isNotEqualTo(companyBankDTO2);
    }
}
