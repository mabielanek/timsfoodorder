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
@Table(name = DbTable.Group.TABLE)
public class GroupEntity {

    @Id
    @GeneratedValue(generator = "groupGenerator")
    @GenericGenerator(name = "groupGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = { @Parameter(name = "sequence_name", value = "seq_fo_group_id") })
    private long id;

    @Version
    @Column(name = DbTable.Group.LAST_MODIFICATION_ID)
    private long lastModificationId;

    @Column(name = DbTable.Group.NAME, nullable = false, length = 255)
    private String name;

    @ManyToOne
    @JoinColumn(name = DbTable.Group.PERSON_OWNER, foreignKey = @ForeignKey(name = "group_person_fk"))
    private PersonEntity personOwner;

    @ManyToOne
    @JoinColumn(name = DbTable.Group.ORGANIZATION_OWNER, foreignKey = @ForeignKey(name = "group_organization_fk"))
    private OrganizationEntity organizationOwner;

    public long getLastModificationId() {
        return lastModificationId;
    }

    public void setLastModificationId(long lastModificationId) {
        this.lastModificationId = lastModificationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonEntity getPersonOwner() {
        return personOwner;
    }

    public void setPersonOwner(PersonEntity personOwner) {
        this.personOwner = personOwner;
    }

    public OrganizationEntity getOrganizationOwner() {
        return organizationOwner;
    }

    public void setOrganizationOwner(OrganizationEntity organizationOwner) {
        this.organizationOwner = organizationOwner;
    }

    public long getId() {
        return id;
    }


}
