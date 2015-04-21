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
@Table(name = "fo_web_url",
  indexes = @Index(columnList="contact_id", name="idx_contact_web_url_fk")
)
public class WebUrlEntity {

  @Id
  @GeneratedValue(generator = "webUrlGenerator")
  @GenericGenerator(name = "webUrlGenerator", strategy="org.hibernate.id.enhanced.SequenceStyleGenerator", 
  parameters = { 
    @Parameter(name = "sequence_name", value="seq_fo_web_url_id")
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
  
  @Column(name = "web_url_address", nullable = false, length = 255)
  private String webUrlAddress;
  
  @ManyToOne
  @JoinColumn(name = "contact_id", foreignKey=@ForeignKey(name="web_url_contact_fk"))
  private ContactEntity contact;

  /**
   * Gets the status.
   *
   * @return the status
   */
  public ActivityStatus getStatus() {
    return ActivityStatus.forCode(status);
  }

  /**
   * Sets the status.
   *
   * @param status the new status
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
   * @param comment the new comment
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  /**
   * Gets the display index, when sorting is necessary for entity with multiple web urls.
   *
   * @return the display index
   */
  public int getDisplayIndex() {
    return displayIndex;
  }

  /**
   * Sets the display index, when sorting is necessary for entity with multiple web urls.
   *
   * @param displayIndex the new display index
   */
  public void setDisplayIndex(int displayIndex) {
    this.displayIndex = displayIndex;
  }

  /**
   * Gets the web url address.
   *
   * @return the web url address
   */
  public String getWebUrlAddress() {
    return webUrlAddress;
  }

  /**
   * Sets the web url address.
   *
   * @param webUrlAddress the new web url address
   */
  public void setWebUrlAddress(String webUrlAddress) {
    this.webUrlAddress = webUrlAddress;
  }
  public ContactEntity getContact() {
	return contact;
  }

  void setContact(ContactEntity contact) {
	this.contact = contact;
  }  
  
  /**
   * Gets the Web Url record identifier.
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
		private WebUrlEntity entity = new WebUrlEntity();
		
		public Builder(ActivityStatus status, String webUrlAddress, int displayIndex) {
			entity.setStatus(status);
			entity.setDisplayIndex(displayIndex);
			entity.setWebUrlAddress(webUrlAddress);
		}

		public WebUrlEntity build() {
			return entity;
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

		public Builder webUrlAddress(String webUrlAddress) {
			entity.setWebUrlAddress(webUrlAddress);
			return this;
		}

	}

}
