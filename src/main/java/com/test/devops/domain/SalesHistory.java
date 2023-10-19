package com.test.devops.domain;

import com.test.devops.domain.enumeration.PaiementType;
import lombok.Data;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "sales_history")
@Data
public class SalesHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* To test with API */
    @ElementCollection
    @CollectionTable(name = "bills_paiement_types")
    @MapKeyColumn(name = "bill_id")
    @MapKeyClass(Bill.class)
    @Column(name = "paiement_type")
    @Enumerated(EnumType.STRING)
    private Map<Bill, PaiementType> billsPaiementTypes;

    /* Good */
    @OneToMany(cascade = CascadeType.ALL)
    @MapKeyColumn(name = "paiement_type")
    @MapKeyEnumerated(EnumType.STRING)
    @JoinTable(name = "paiement_types_bills", inverseJoinColumns = @JoinColumn(name = "bill_id"))
    private Map<PaiementType, Bill> paiementTypesBills;

}
