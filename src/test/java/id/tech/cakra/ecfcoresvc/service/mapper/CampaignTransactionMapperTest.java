package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CampaignTransactionMapperTest {

    private CampaignTransactionMapper campaignTransactionMapper;

    @BeforeEach
    public void setUp() {
        campaignTransactionMapper = new CampaignTransactionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(campaignTransactionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(campaignTransactionMapper.fromId(null)).isNull();
    }
}
