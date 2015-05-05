package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.timsmeet.persistance.enums.ActivityStatus;

@Entity
@Table(name = "fo_email",
        indexes = @Index(columnList = "contact_id", name = "idx_contact_email_fk"))
public class EmailEntity {

    @Id
    @GeneratedValue(generator = "emailGenerator")
    @GenericGenerator(name = "emailGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_email_id")
            })
    private long id;

    @Version
    @Column(name = "last_modification_id")
    private long lastModificationId;

    @Column(name = "status", nullable = false, length = 1)
    @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
    private String status;

    @Column(name = "comment_text", length = 1024)
    private String comment;

    @Column(name = "display_index", nullable = false)
    private int displayIndex;

    @Column(name = "email_address", nullable = false, length = 255)
    private String emailAddress;

    @ManyToOne
    @JoinColumn(name = "contact_id", foreignKey = @ForeignKey(name = "email_contact_fk"))
    private ContactEntity contact;

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
     * Gets the comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets the comment.
     *
     * @param comment
     *            the new comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets the display index, when sorting is necessary for entity with
     * multiple emails.
     *
     * @return the display index
     */
    public int getDisplayIndex() {
        return displayIndex;
    }

    /**
     * Sets the display index, when sorting is necessary for entity with
     * multiple emails.
     *
     * @param displayIndex
     *            the new display index
     */
    public void setDisplayIndex(int displayIndex) {
        this.displayIndex = displayIndex;
    }

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address.
     *
     * @param emailAddress
     *            the new email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public ContactEntity getContact() {
        return contact;
    }

    void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    /**
     * Gets the Email record identifier.
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

    public final static class Builder {
        private EmailEntity entity = new EmailEntity();

        public Builder(ActivityStatus status, int displayIndex) {
            entity.setStatus(status);
            entity.setDisplayIndex(displayIndex);
        }

        public EmailEntity build() {
            return this.entity;
        }

        public Builder status(ActivityStatus status) {
            entity.setStatus(status);
            return this;
        }

        public Builder comment(String comment) {
            entity.setComment(comment);
            return this;
        }

        public Builder displayIndex(int displayIndex) {
            entity.setDisplayIndex(displayIndex);
            return this;
        }

        public Builder emailAddress(String emailAddress) {
            entity.setEmailAddress(emailAddress);
            return this;
        }

    }

}
