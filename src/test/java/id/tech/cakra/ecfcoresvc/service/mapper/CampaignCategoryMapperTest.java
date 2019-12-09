package id.tech.cakra.ecfcoresvc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CampaignCategoryMapperTest {

    private CampaignCategoryMapper campaignCategoryMapper;

    @BeforeEach
    public void setUp() {
        campaignCategoryMapper = new CampaignCategoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(campaignCategoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(campaignCategoryMapper.fromId(null)).isNull();
    }
}
