package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountInstitutionMapperTest {

    private AccountInstitutionMapper accountInstitutionMapper;

    @BeforeEach
    public void setUp() {
        accountInstitutionMapper = new AccountInstitutionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountInstitutionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountInstitutionMapper.fromId(null)).isNull();
    }
}
