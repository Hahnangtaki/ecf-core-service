package id.tech.cakra.ecfcoresvc.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * A CampaignTransaction.
 */
@Entity
@Table(name = "campaign_transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CampaignTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "trans_code", length = 10)
    private String transCode;

    @Size(max = 200)
    @Column(name = "trans_desc", length = 200)
    private String transDesc;

    @Column(name = "trans_date")
    private LocalDate transDate;

    @Size(max = 1)
    @Column(name = "trans_type", length = 1)
    private String transType;

    @Column(name = "qty")
    private Long qty;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "unique_charges")
    private Double uniqueCharges;

    @Column(name = "net_amount")
    private Double netAmount;

    @Size(max = 1)
    @Column(name = "status", length = 1)
    private String status;

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
    @JsonIgnoreProperties("campaignTransactions")
    private Campaign campaign;

    @ManyToOne
    @JsonIgnoreProperties("campaignTransactions")
    private AccountMember accountMember;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransCode() {
        return transCode;
    }

    public CampaignTransaction transCode(String transCode) {
        this.transCode = transCode;
        return this;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransDesc() {
        return transDesc;
    }

    public CampaignTransaction transDesc(String transDesc) {
        this.transDesc = transDesc;
        return this;
    }

    public void setTransDesc(String transDesc) {
        this.transDesc = transDesc;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public CampaignTransaction transDate(LocalDate transDate) {
        this.transDate = transDate;
        return this;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public String getTransType() {
        return transType;
    }

    public CampaignTransaction transType(String transType) {
        this.transType = transType;
        return this;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Long getQty() {
        return qty;
    }

    public CampaignTransaction qty(Long qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public Double getAmount() {
        return amount;
    }

    public CampaignTransaction amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUniqueCharges() {
        return uniqueCharges;
    }

    public CampaignTransaction uniqueCharges(Double uniqueCharges) {
        this.uniqueCharges = uniqueCharges;
        return this;
    }

    public void setUniqueCharges(Double uniqueCharges) {
        this.uniqueCharges = uniqueCharges;
    }

    public Double getNetAmount() {
        return netAmount;
    }

    public CampaignTransaction netAmount(Double netAmount) {
        this.netAmount = netAmount;
        return this;
    }

    public void setNetAmount(Double netAmount) {
        this.netAmount = netAmount;
    }

    public String getStatus() {
        return status;
    }

    public CampaignTransaction status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreateSystemDate() {
        return createSystemDate;
    }

    public CampaignTransaction createSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
        return this;
    }

    public void setCreateSystemDate(LocalDate createSystemDate) {
        this.createSystemDate = createSystemDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public CampaignTransaction createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public CampaignTransaction createUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDate getLastModificationSystemDate() {
        return lastModificationSystemDate;
    }

    public CampaignTransaction lastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
        return this;
    }

    public void setLastModificationSystemDate(LocalDate lastModificationSystemDate) {
        this.lastModificationSystemDate = lastModificationSystemDate;
    }

    public ZonedDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public CampaignTransaction lastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
        return this;
    }

    public void setLastModificationDate(ZonedDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Long getLastModificationUserId() {
        return lastModificationUserId;
    }

    public CampaignTransaction lastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
        return this;
    }

    public void setLastModificationUserId(Long lastModificationUserId) {
        this.lastModificationUserId = lastModificationUserId;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public CampaignTransaction campaign(Campaign campaign) {
        this.campaign = campaign;
        return this;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public AccountMember getAccountMember() {
        return accountMember;
    }

    public CampaignTransaction accountMember(AccountMember accountMember) {
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
        if (!(o instanceof CampaignTransaction)) {
            return false;
        }
        return id != null && id.equals(((CampaignTransaction) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CampaignTransaction{" +
            "id=" + getId() +
            ", transCode='" + getTransCode() + "'" +
            ", transDesc='" + getTransDesc() + "'" +
            ", transDate='" + getTransDate() + "'" +
            ", transType='" + getTransType() + "'" +
            ", qty=" + getQty() +
            ", amount=" + getAmount() +
            ", uniqueCharges=" + getUniqueCharges() +
            ", netAmount=" + getNetAmount() +
            ", status='" + getStatus() + "'" +
            ", createSystemDate='" + getCreateSystemDate() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUserId=" + getCreateUserId() +
            ", lastModificationSystemDate='" + getLastModificationSystemDate() + "'" +
            ", lastModificationDate='" + getLastModificationDate() + "'" +
            ", lastModificationUserId=" + getLastModificationUserId() +
            "}";
    }
}
