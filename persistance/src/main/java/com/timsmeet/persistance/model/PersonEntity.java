package com.timsmeet.persistance.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.timsmeet.persistance.constants.DbTable;

@Entity
@Table(name = DbTable.Person.TABLE)
public class PersonEntity {

    @Id
    @GeneratedValue(generator = "personGenerator")
    @GenericGenerator(name = "personGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_fo_person_id") })
    private long id;

    @Version
    @Column(name = DbTable.Person.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.Person.LOGIN, nullable = false, length = 255)
    private String login;

    @Column(name = DbTable.Person.PASSWORD, nullable = false, length = 255)
    private String password;

    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = DbTable.Person.CONTACT_ID, foreignKey = @ForeignKey(name = "person_contact_fk"))
    private ContactEntity contact;

    @ManyToOne
    @JoinColumn(name = DbTable.Person.LOCATION_ID, foreignKey = @ForeignKey(name = "person_location_fk"))
    private LocationEntity location;

    @Column(name = DbTable.Person.ROOM, length = 15)
    private String room;

    @Column(name = DbTable.Person.DESK, length = 15)
    private String desk;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }


}
