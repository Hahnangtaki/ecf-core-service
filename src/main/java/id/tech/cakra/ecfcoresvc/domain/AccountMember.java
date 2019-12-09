package id.tech.cakra.ecfcoresvc.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A AccountMember.
 */
@Entity
@Table(name = "account_member")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AccountMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "account_code", length = 10)
    private String accountCode;

    @Size(max = 100)
    @Column(name = "account_name", length = 100)
    private String accountName;

    @Size(max = 1)
    @Column(name = "account_type", length = 1)
    private String accountType;

    @Column(name = "account_angel")
    private Boolean accountAngel;

    @Size(max = 4)
    @Column(name = "ksei_client_code", length = 4)
    private String kseiClientCode;

    @Size(max = 15)
    @Column(name = "ksei_subrek", length = 15)
    private String kseiSubrek;

    @Column(name = "create_system_date")
    private LocalDate createSystemDate;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "last_modification_system_date")
    private LocalDate lastModificationSystemDate;

    @Column(name = "last_modification_date")
    private ZonedDateTime lastModificationDate;

    @Column(name = "last_modification_user_id")
    private Long lastModificationUserId;

    @OneToMany(mappedBy = "accountMember")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignTransaction> campaignTransactions = new HashSet<>();

    @OneToMany(mappedBy = "accountMember")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Campaign> campaigns = new HashSet<>();

    @OneToMany(mappedBy = "accountMember")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CampaignInvestor> campaignInvestors = new HashSet<>();

    @OneToMany(mappedBy = "accountMember")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AccountAddress> accountAddresses = new HashSet<>();

    @OneToMany(mappedBy = "accountMember")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AccountAuthorize> accountAuthorizes = new HashSet<>();

    @OneToOne(mappedBy = "accountMember")
    @JsonIgnore
    private AccountInstitution accountInstitution;

    @OneToOne(mappedBy = "accountMember")
    @JsonIgnore
    private AccountIndividu accountIndividu;

    @OneToOne(mappedBy = "accountMember")
    @JsonIgnore
    private AccountBank accountBank;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public AccountMember accountCode(String accountCode) {
        this.accountCode = accountCode;
        return this;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public AccountMember accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public AccountMember accountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Boolean isAccountAngel() {
        return accountAngel;
    }

    public AccountMember accountAngel(Boolean accountAngel) {
        this.accountAngel = accountAngel;
        return this;
    }

    public void setAccountAngel(Boolean accountAngel) {
        this.accountAngel = accountAngel;
    }

    public String getKseiClientCode() {
        return kseiClientCode;
    }

    public AccountMember kseiClientCode(String kseiClientCode) {
        this.kseiClientCode = kseiClientCode;
        return this;
    }

    public void setKseiClientCode(String kseiClientCode) {
        this.kseiClientCode = kseiClientCode;
    }

    public String getKseiSubrek() {
        return kseiSubrek;
    }

    public AccountMember kseiSubrek(String kseiSubrek) {
        this.kseiSubrek = kseiSubrek;
        return this;
    }

    public void setKseiSubrek(String kseiSubrek) {
        this.kseiSubrek = kseiSubrek;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public AccountMember createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public AccountMember createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public AccountMember createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public AccountMember lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public AccountMember lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public AccountMember lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public Set<CampaignTransaction> getCampaignTransactions() {
        return campaignTransactions;
    }

    public AccountMember campaignTransactions(Set<CampaignTransaction> campaignTransactions) {
        this.campaignTransactions = campaignTransactions;
        return this;
    }

    public AccountMember addCampaignTransaction(CampaignTransaction campaignTransaction) {
        this.campaignTransactions.add(campaignTransaction);
        campaignTransaction.setAccountMember(this);
        return this;
    }

    public AccountMember removeCampaignTransaction(CampaignTransaction campaignTransaction) {
        this.campaignTransactions.remove(campaignTransaction);
        campaignTransaction.setAccountMember(null);
        return this;
    }

    public void setCampaignTransactions(Set<CampaignTransaction> campaignTransactions) {
        this.campaignTransactions = campaignTransactions;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public AccountMember campaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
        return this;
    }

    public AccountMember addCampaign(Campaign campaign) {
        this.campaigns.add(campaign);
        campaign.setAccountMember(this);
        return this;
    }

    public AccountMember removeCampaign(Campaign campaign) {
        this.campaigns.remove(campaign);
        campaign.setAccountMember(null);
        return this;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Set<CampaignInvestor> getCampaignInvestors() {
        return campaignInvestors;
    }

    public AccountMember campaignInvestors(Set<CampaignInvestor> campaignInvestors) {
        this.campaignInvestors = campaignInvestors;
        return this;
    }

    public AccountMember addCampaignInvestor(CampaignInvestor campaignInvestor) {
        this.campaignInvestors.add(campaignInvestor);
        campaignInvestor.setAccountMember(this);
        return this;
    }

    public AccountMember removeCampaignInvestor(CampaignInvestor campaignInvestor) {
        this.campaignInvestors.remove(campaignInvestor);
        campaignInvestor.setAccountMember(null);
        return this;
    }

    public void setCampaignInvestors(Set<CampaignInvestor> campaignInvestors) {
        this.campaignInvestors = campaignInvestors;
    }

    public Set<AccountAddress> getAccountAddresses() {
        return accountAddresses;
    }

    public AccountMember accountAddresses(Set<AccountAddress> accountAddresses) {
        this.accountAddresses = accountAddresses;
        return this;
    }

    public AccountMember addAccountAddress(AccountAddress accountAddress) {
        this.accountAddresses.add(accountAddress);
        accountAddress.setAccountMember(this);
        return this;
    }

    public AccountMember removeAccountAddress(AccountAddress accountAddress) {
        this.accountAddresses.remove(accountAddress);
        accountAddress.setAccountMember(null);
        return this;
    }

    public void setAccountAddresses(Set<AccountAddress> accountAddresses) {
        this.accountAddresses = accountAddresses;
    }

    public Set<AccountAuthorize> getAccountAuthorizes() {
        return accountAuthorizes;
    }

    public AccountMember accountAuthorizes(Set<AccountAuthorize> accountAuthorizes) {
        this.accountAuthorizes = accountAuthorizes;
        return this;
    }

    public AccountMember addAccountAuthorize(AccountAuthorize accountAuthorize) {
        this.accountAuthorizes.add(accountAuthorize);
        accountAuthorize.setAccountMember(this);
        return this;
    }

    public AccountMember removeAccountAuthorize(AccountAuthorize accountAuthorize) {
        this.accountAuthorizes.remove(accountAuthorize);
        accountAuthorize.setAccountMember(null);
        return this;
    }

    public void setAccountAuthorizes(Set<AccountAuthorize> accountAuthorizes) {
        this.accountAuthorizes = accountAuthorizes;
    }

    public AccountInstitution getAccountInstitution() {
        return accountInstitution;
    }

    public AccountMember accountInstitution(AccountInstitution accountInstitution) {
        this.accountInstitution = accountInstitution;
        return this;
    }

    public void setAccountInstitution(AccountInstitution accountInstitution) {
        this.accountInstitution = accountInstitution;
    }

    public AccountIndividu getAccountIndividu() {
        return accountIndividu;
    }

    public AccountMember accountIndividu(AccountIndividu accountIndividu) {
        this.accountIndividu = accountIndividu;
        return this;
    }

    public void setAccountIndividu(AccountIndividu accountIndividu) {
        this.accountIndividu = accountIndividu;
    }

    public AccountBank getAccountBank() {
        return accountBank;
    }

    public AccountMember accountBank(AccountBank accountBank) {
        this.accountBank = accountBank;
        return this;
    }

    public void setAccountBank(AccountBank accountBank) {
        this.accountBank = accountBank;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountMember)) {
            return false;
        }
        return id != null && id.equals(((AccountMember) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AccountMember{" +
            "id=" + getId() +
            ", accountCode='" + getAccountCode() + "'" +
            ", accountName='" + getAccountName() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", accountAngel='" + isAccountAngel() + "'" +
            ", kseiClientCode='" + getKseiClientCode() + "'" +
            ", kseiSubrek='" + getKseiSubrek() + "'" +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
