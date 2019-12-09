package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class AccountMemberMapperTest {

    private AccountMemberMapper accountMemberMapper;

    @BeforeEach
    public void setUp() {
        accountMemberMapper = new AccountMemberMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(accountMemberMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(accountMemberMapper.fromId(null)).isNull();
    }
}
