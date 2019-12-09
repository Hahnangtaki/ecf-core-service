package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CampaignInvestorMapperTest {

    private CampaignInvestorMapper campaignInvestorMapper;

    @BeforeEach
    public void setUp() {
        campaignInvestorMapper = new CampaignInvestorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(campaignInvestorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(campaignInvestorMapper.fromId(null)).isNull();
    }
}
