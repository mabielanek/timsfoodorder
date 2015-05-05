package com.timsmeet.persistance.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.timsmeet.persistance.enums.ActivityStatus;

/**
 * Stores information about contacts.
 */
@Entity
@Table(name = "fo_contact")
public class ContactEntity {

    @Id
    @GeneratedValue(generator = "contactGenerator")
    @GenericGenerator(name = "contactGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_contact_id")
            })
    private long id;

    @Version
    @Column(name = "last_modification_id")
    private long lastModificationId;

    @Column(name = "status", nullable = false, length = 1)
    @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
    private String status;

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "contact_id", nullable = false, foreignKey = @ForeignKey(name = "contact_phone_fk"))
    private List<PhoneEntity> phones = new ArrayList<PhoneEntity>();

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "contact_id", nullable = false, foreignKey = @ForeignKey(name = "contact_web_url_fk"))
    private List<WebUrlEntity> webUrls = new ArrayList<WebUrlEntity>();

    @OneToMany(cascade = { CascadeType.ALL })
    @JoinColumn(name = "contact_id", nullable = false, foreignKey = @ForeignKey(name = "contact_email_fk"))
    private List<EmailEntity> emails = new ArrayList<EmailEntity>();

    /**
     * Gets the record status.
     *
     * @return the status
     */
    public ActivityStatus getStatus() {
        return ActivityStatus.forCode(status);
    }

    /**
     * Sets the record status.
     *
     * @param status
     *            the new status
     */
    public void setStatus(ActivityStatus status) {
        this.status = status.getCode();
    }

    /**
     * Gets the Contact record identifier.
     *
     * @return the record identifier
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the last modification identifier - for optimistic concurrency
     * locking.
     *
     * @return the last modification id
     */
    public long getLastModificationId() {
        return lastModificationId;
    }

    /**
     * Sets the last modification identifier - for optimistic concurrency
     * locking.
     *
     * @param comment
     *            the last modification id
     */
    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

    /**
     * Gets the phones.
     *
     * @return the phones
     */
    public List<PhoneEntity> getPhones() {
        return phones;
    }

    /**
     * Adds the phone.
     *
     * @param phone
     *            the phone
     * @return true, if collection changed as a result of the call
     */
    public boolean addPhone(PhoneEntity phone) {
        if (this.phones == null) {
            this.phones = Lists.newArrayList();
        }
        return this.phones.add(phone);
    }

    /**
     * Removes the phone.
     *
     * @param phone
     *            the phone
     * @return true, if list contained the specified element
     */
    public boolean removePhone(PhoneEntity phone) {
        Preconditions.checkNotNull(phone);
        if (this.phones != null) {
            return this.phones.remove(phone);
        }
        return false;
    }

    /**
     * Gets the web urls.
     *
     * @return the web urls
     */
    public List<WebUrlEntity> getWebUrls() {
        return webUrls;
    }

    /**
     * Adds the web url.
     *
     * @param webUrl
     *            the web url
     * @return true, if collection changed as a result of the call
     */
    public boolean addWebUrl(WebUrlEntity webUrl) {
        if (this.webUrls == null) {
            this.webUrls = Lists.newArrayList();
        }
        return this.webUrls.add(webUrl);
    }

    /**
     * Removes the web url.
     *
     * @param webUrl
     *            the web url
     * @return true, if list contained the specified element
     */
    public boolean removeWebUrl(WebUrlEntity webUrl) {
        Preconditions.checkNotNull(webUrl);
        if (this.webUrls != null) {
            return this.webUrls.remove(webUrl);
        }
        return false;
    }

    /**
     * Gets the emails.
     *
     * @return the emails
     */
    public List<EmailEntity> getEmails() {
        return emails;
    }

    /**
     * Adds the email.
     *
     * @param email
     *            the email
     * @return true, if collection changed as a result of the call
     */
    public boolean addEmail(EmailEntity email) {
        if (this.emails == null) {
            this.emails = Lists.newArrayList();
        }
        return this.emails.add(email);
    }

    /**
     * Removes the email.
     *
     * @param email
     *            the email
     * @return true, if list contained the specified element
     */
    public boolean removeEmail(EmailEntity email) {
        Preconditions.checkNotNull(email);
        if (this.emails != null) {
            return this.emails.remove(email);
        }
        return false;
    }

    public final static class Builder {
        private ContactEntity contact = new ContactEntity();

        public Builder(ActivityStatus status) {
            contact.setStatus(status);
        }

        public ContactEntity build() {
            return contact;
        }

        public Builder setStatus(ActivityStatus status) {
            contact.setStatus(status);
            return this;
        }

        public Builder addPhone(PhoneEntity... phones) {
            for (PhoneEntity phone : phones) {
                contact.addPhone(phone);
            }
            return this;
        }

        public Builder addWebUrl(WebUrlEntity... webUrls) {
            for (WebUrlEntity webUrl : webUrls) {
                contact.addWebUrl(webUrl);
            }
            return this;
        }

        public Builder addEmail(EmailEntity... emails) {
            for (EmailEntity email : emails) {
                contact.addEmail(email);
            }
            return this;
        }
    }
}
