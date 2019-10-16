package id.tech.cakra.ecfcoresvc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A CampaignInvestor.
 */
@Entity
@Table(name = "campaign_investor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampaignInvestor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "qty_on_hand")
    private Long qtyOnHand;

    @Column(name = "qty_deposit")
    private Long qtyDeposit;

    @Column(name = "qty_withdraw")
    private Long qtyWithdraw;

    @Column(name = "cast_value")
    private Double castValue;

    @Column(name = "profit_loss")
    private Double profitLoss;

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

    @ManyToOne
    @JsonIgnoreProperties("campaignInvestors")
    private Campaign campaign;

    @ManyToOne
    @JsonIgnoreProperties("campaignInvestors")
    private AccountMember accountMember;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQtyOnHand() {
        return qtyOnHand;
    }

    public CampaignInvestor qtyOnHand(Long qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
        return this;
    }

    public void setQtyOnHand(Long qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public Long getQtyDeposit() {
        return qtyDeposit;
    }

    public CampaignInvestor qtyDeposit(Long qtyDeposit) {
        this.qtyDeposit = qtyDeposit;
        return this;
    }

    public void setQtyDeposit(Long qtyDeposit) {
        this.qtyDeposit = qtyDeposit;
    }

    public Long getQtyWithdraw() {
        return qtyWithdraw;
    }

    public CampaignInvestor qtyWithdraw(Long qtyWithdraw) {
        this.qtyWithdraw = qtyWithdraw;
        return this;
    }

    public void setQtyWithdraw(Long qtyWithdraw) {
        this.qtyWithdraw = qtyWithdraw;
    }

    public Double getCastValue() {
        return castValue;
    }

    public CampaignInvestor castValue(Double castValue) {
        this.castValue = castValue;
        return this;
    }

    public void setCastValue(Double castValue) {
        this.castValue = castValue;
    }

    public Double getProfitLoss() {
        return profitLoss;
    }

    public CampaignInvestor profitLoss(Double profitLoss) {
        this.profitLoss = profitLoss;
        return this;
    }

    public void setProfitLoss(Double profitLoss) {
        this.profitLoss = profitLoss;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public CampaignInvestor createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public CampaignInvestor createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public CampaignInvestor createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public CampaignInvestor lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public CampaignInvestor lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public CampaignInvestor lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignInvestor campaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public AccountMember getAccountMember() {
        return accountMember;
    }

    public CampaignInvestor accountMember(AccountMember accountMember) {
        this.accountMember = accountMember;
        return this;
    }

    public void setAccountMember(AccountMember accountMember) {
        this.accountMember = accountMember;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CampaignInvestor)) {
            return false;
        }
        return id != null && id.equals(((CampaignInvestor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CampaignInvestor{" +
            "id=" + getId() +
            ", qtyOnHand=" + getQtyOnHand() +
            ", qtyDeposit=" + getQtyDeposit() +
            ", qtyWithdraw=" + getQtyWithdraw() +
            ", castValue=" + getCastValue() +
            ", profitLoss=" + getProfitLoss() +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
