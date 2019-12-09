package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountIndividuMapperTest {

    private AccountIndividuMapper accountIndividuMapper;

    @BeforeEach
    public void setUp() {
        accountIndividuMapper = new AccountIndividuMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountIndividuMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountIndividuMapper.fromId(null)).isNull();
    }
}
