package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountAuthorizeMapperTest {

    private AccountAuthorizeMapper accountAuthorizeMapper;

    @BeforeEach
    public void setUp() {
        accountAuthorizeMapper = new AccountAuthorizeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountAuthorizeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountAuthorizeMapper.fromId(null)).isNull();
    }
}
