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
import com.timsmeet.persistance.enums.PhoneNumberType;

@Entity
@Table(name = "fo_phone",
  indexes = @Index(columnList="contact_id", name="idx_contact_phone_fk")
)
public class PhoneEntity {

  @Id
  @GeneratedValue(generator = "phoneGenerator")
  @GenericGenerator(name = "phoneGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_fo_phone_id")
  })
  private long id;
  
  @Version
  @Column(name = "last_modification_id")
  private long lastModificationId;
  
  @Column(name = "status", nullable = false, length = 1)
  @org.hibernate.annotations.Check(constraints = "status IN('A','I','D')")
  private String status;

  @Column(name = "phone", length = 15)
  private String phone;
  
  @Column(name = "phone_ext", length = 15)
  private String phoneExt;
  
  @Column(name = "number_type", length = 1, nullable = false)
  @org.hibernate.annotations.Check(constraints = "status IN('M','F','S')")
  private String numberType;

  @Column(name = "comment_text", length = 1024)
  private String comment;
  
  @Column(name = "display_index", nullable = false)
  private int displayIndex;
  
  @ManyToOne
  @JoinColumn(name = "contact_id", foreignKey=@ForeignKey(name="phone_contact_fk"))
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
   * @param status the new status
   */
  public void setStatus(ActivityStatus status) {
    this.status = status.getCode();
  }

  /**
   * Gets the phone number.
   *
   * @return the phone number
   */
  public String getPhone() {
    return phone;
  }

  /**
   * Sets the phone number.
   *
   * @param phone the new phone number
   */
  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Gets the phone number extension.
   *
   * @return the phone number extension
   */
  public String getPhoneExt() {
    return phoneExt;
  }

  /**
   * Sets the phone number extension
   *
   * @param phoneExt the new phone number extension
   */
  public void setPhoneExt(String phoneExt) {
    this.phoneExt = phoneExt;
  }

  /**
   * Gets the number type.
   *
   * @return the number type
   */
  public PhoneNumberType getNumberType() {
    return PhoneNumberType.forCode(numberType);
  }

  /**
   * Sets the number type.
   *
   * @param numberType the new number type
   */
  public void setNumberType(PhoneNumberType numberType) {
    this.numberType = numberType.getCode();
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
   * @param comment the new comment
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * Gets the display index, when sorting is necessary for entity with multiple phones.
   *
   * @return the display index
   */
  public int getDisplayIndex() {
    return displayIndex;
  }

  /**
   * Sets the display index, when sorting is necessary for entity with multiple phones.
   *
   * @param displayIndex the new display index
   */
  public void setDisplayIndex(int displayIndex) {
    this.displayIndex = displayIndex;
  }
  
  public ContactEntity getContact() {
	return contact;
  }

  void setContact(ContactEntity contact) {
	this.contact = contact;
  }  

  /**
   * Gets the Phone record identifier.
   *
   * @return the record identifier
   */
  public long getId() {
    return id;
  }

  /**
   * Gets the last modification identifier - for optimistic concurrency locking.
   *
   * @return the last modification id
   */
  public long getLastModificationId() {
    return lastModificationId;
  }
  
  /**
   * Sets the last modification identifier - for optimistic concurrency locking.
   *
   * @param comment the last modification id
   */
  public void setLastModificationId(long lastModificationId) {
	  this.lastModificationId = lastModificationId;
  }

	public final static class Builder {
		private PhoneEntity entity = new PhoneEntity();

		public PhoneEntity build() {
			return entity;
		}

		public Builder(ActivityStatus status, PhoneNumberType numberType,
				int displayIndex) {
			entity.setStatus(status);
			entity.setNumberType(numberType);
			entity.setDisplayIndex(displayIndex);
		}

		public Builder status(ActivityStatus status) {
			entity.setStatus(status);
			return this;
		}

		public Builder phone(String phone) {
			entity.setPhone(phone);
			return this;
		}

		public Builder phoneExt(String phoneExt) {
			entity.setPhoneExt(phoneExt);
			return this;
		}

		public Builder numberType(PhoneNumberType numberType) {
			entity.setNumberType(numberType);
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

	}

}
