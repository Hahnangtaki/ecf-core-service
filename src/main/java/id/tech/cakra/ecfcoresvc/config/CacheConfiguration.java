package id.tech.cakra.ecfcoresvc.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountAddress.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountMember.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountMember.class.getName() + ".campaignTransactions");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountMember.class.getName() + ".campaigns");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountMember.class.getName() + ".campaignInvestors");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountMember.class.getName() + ".accountAddresses");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountMember.class.getName() + ".accountAuthorizes");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.CampaignInvestor.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountAuthorize.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountIndividu.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountInstitution.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.Campaign.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.Campaign.class.getName() + ".campaignCategories");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.Campaign.class.getName() + ".campaignInvestors");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.Campaign.class.getName() + ".campaignPayments");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.Campaign.class.getName() + ".campaignTransactions");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.CampaignTransaction.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.CampaignPayment.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.CampaignCategory.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.CompanyBank.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.CompanyBank.class.getName() + ".campaigns");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.CompanyBank.class.getName() + ".campaignPayments");
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountBank.class.getName());
            createCache(cm, id.tech.cakra.ecfcoresvc.domain.AccountBank.class.getName() + ".campaignPayments");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache != null) {
            cm.destroyCache(cacheName);
        }
        cm.createCache(cacheName, jcacheConfiguration);
    }

}
