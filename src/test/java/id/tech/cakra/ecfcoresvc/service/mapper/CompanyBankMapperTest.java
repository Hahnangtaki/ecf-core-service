package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CompanyBankMapperTest {

    private CompanyBankMapper companyBankMapper;

    @BeforeEach
    public void setUp() {
        companyBankMapper = new CompanyBankMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(companyBankMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(companyBankMapper.fromId(null)).isNull();
    }
}
