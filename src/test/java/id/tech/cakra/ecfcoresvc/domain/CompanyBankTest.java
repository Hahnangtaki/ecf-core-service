package id.tech.cakra.ecfcoresvc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import id.tech.cakra.ecfcoresvc.web.rest.TestUtil;

public class CompanyBankTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompanyBank.class);
        CompanyBank companyBank1 = new CompanyBank();
        companyBank1.setId(1L);
        CompanyBank companyBank2 = new CompanyBank();
        companyBank2.setId(companyBank1.getId());
        assertThat(companyBank1).isEqualTo(companyBank2);
        companyBank2.setId(2L);
        assertThat(companyBank1).isNotEqualTo(companyBank2);
        companyBank1.setId(null);
        assertThat(companyBank1).isNotEqualTo(companyBank2);
    }
}
