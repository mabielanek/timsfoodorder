package com.timsmeet.persistance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.timsmeet.persistance.constants.DbTable;

@Entity
@Table(name = DbTable.PersonGroup.TABLE)
public class PersonGroupEntity {

    @Id
    @GeneratedValue(generator = "personGroupGenerator")
    @GenericGenerator(name = "personGroupGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_fo_person_group_id") })
    private long id;

    @Version
    @Column(name = DbTable.PersonGroup.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @ManyToOne
    @JoinColumn(name = DbTable.PersonGroup.PERSON, foreignKey = @ForeignKey(name = "person_group_person_fk"))
    private PersonEntity person;

    @ManyToOne
    @JoinColumn(name = DbTable.PersonGroup.GROUP, foreignKey = @ForeignKey(name = "person_group_group_fk"))
    private GroupEntity group;

    public long getLastModificationId() {
        return lastModificationId;
    }

    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public long getId() {
        return id;
    }


}
