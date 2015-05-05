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

@Entity
@Table(name = "fo_dish_genere",
        indexes = {
                @Index(columnList = "dish_id", name = "idx_dish_gene_dish_fk"),
                @Index(columnList = "genere_id", name = "idx_dish_gene_gene_fk") })
public class DishGenereEntity {

    @Id
    @GeneratedValue(generator = "dishGenereGenerator")
    @GenericGenerator(name = "dishGenereGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_fo_dish_genere_id")
            })
    private long id;

    @Version
    @Column(name = "last_modification_id")
    private long lastModificationId;

    @ManyToOne
    @JoinColumn(name = "dish_id", foreignKey = @ForeignKey(name = "dish_gene_dish_fk"))
    private DishEntity dish;

    @ManyToOne
    @JoinColumn(name = "genere_id", foreignKey = @ForeignKey(name = "dish_gene_gene_fk"))
    private GenereEntity genere;

    public DishEntity getDish() {
        return dish;
    }

    public void setDish(DishEntity dish) {
        this.dish = dish;
    }

    public GenereEntity getGenere() {
        return genere;
    }

    public void setGenere(GenereEntity genere) {
        this.genere = genere;
    }

    public long getId() {
        return id;
    }

    public long getLastModificationId() {
        return lastModificationId;
    }

}
