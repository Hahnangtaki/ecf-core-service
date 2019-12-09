package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CampaignPaymentMapperTest {

    private CampaignPaymentMapper campaignPaymentMapper;

    @BeforeEach
    public void setUp() {
        campaignPaymentMapper = new CampaignPaymentMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(campaignPaymentMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(campaignPaymentMapper.fromId(null)).isNull();
    }
}
