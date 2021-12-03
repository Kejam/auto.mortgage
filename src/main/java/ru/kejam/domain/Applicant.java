package ru.kejam.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "Applicant", schema = "public")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "app_date")
    private Date date;
    @Column(name = "client_id")
    private int clientId;
    @Column(name = "amount")
    private float amount;
    @Column(name = "corrid")
    private int corrId;
    @Column(name = "seller_id")
    private int sellerId;
    @Column(name = "frod_buyer")
    private boolean frodBuyer;
    @Column(name = "frod_docs")
    private boolean frodDocs;
    @Column(name = "frod_object")
    private boolean frodObject;
    @Column(name = "frod_app")
    private boolean frodApp;
    @Column(name = "docs_id")
    private int docsId;
    @Column(name = "object_id")
    private int objectId;
    @Column(name = "address")
    private String address;
    @Column(name = "type_of_object")
    private String typeObject;
    @Column(name = "date_applicant")
    private Date dateApplicant;
    @Column(name = "date_signing")
    private Date dateSigning;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "code_client")
    private String codeClient;
    @Column(name = "decision")
    private String decision;
    @Column(name = "f_name")
    private String fName;
    @Column(name = "s_name")
    private String sName;
    @Column(name = "o_name")
    private String oName;
    @Column(name = "sex")
    private String sex;
    @Column(name = "date_birth")
    private String dateBirth;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "civil")
    private String civil;
    @Column(name = "snils")
    private String snils;
    @Column(name = "inn")
    private String inn;
    @Column(name = "n_pass")
    private String nPass;
    @Column(name = "s_pass")
    private String sPass;
    @Column(name = "type_seller")
    private String typeSeller;
    @Column(name = "add_date")
    private String addDate;
    @Column(name = "d_applicant")
    private byte[] dApplicant;
    @Column(name = "d_vipiska_iz_domovoi")
    private byte[] dVipiskaDomovoi;
    @Column(name = "d_kadastr_pass")
    private byte[] dKadastrPass;
    @Column(name = "d_tech_pass")
    private byte[] dTechPass;
    @Column(name = "d_owner_order")
    private byte[] dOwnerOrder;
    @Column(name = "d_price_order")
    private byte[] dPriceOrder;
    @Column(name = "d_ndfl")
    private byte[] dNdfl;
    @Column(name = "d_work")
    private byte[] dWork;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Applicant applicant = (Applicant) o;
        return id != null && Objects.equals(id, applicant.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
