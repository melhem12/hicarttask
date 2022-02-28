package com.melhem.hicarttask.entitiy;

import com.melhem.hicarttask.security.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_name")
    private String customerName ;
    @Column(name = "customer_address")
    private String customerAddress;
    @Column(name = "customer_Phone")
    private String customerPhone;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by", nullable = false)
    private AppUser addedBy;
    @Column(name = "created_at")
    private Date createdAt ;

}
