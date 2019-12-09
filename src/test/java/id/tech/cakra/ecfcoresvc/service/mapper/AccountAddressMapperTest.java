package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountAddressMapperTest {

    private AccountAddressMapper accountAddressMapper;

    @BeforeEach
    public void setUp() {
        accountAddressMapper = new AccountAddressMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountAddressMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountAddressMapper.fromId(null)).isNull();
    }
}
