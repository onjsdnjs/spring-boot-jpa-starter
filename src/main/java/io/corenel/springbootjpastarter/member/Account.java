package io.corenel.springbootjpastarter.member;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(nullable=false, unique=true)
    private String username;

    private String password;

    private String email;

    @Transient
    private String no;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name="street", column=@Column(name="home_street")),
                    @AttributeOverride(name="city", column=@Column(name="home_city")),
                    @AttributeOverride(name="state", column=@Column(name="home_state")),
                    @AttributeOverride(name="zipCode", column=@Column(name="home_zipCode"))
            }
            )
    private Address homeAddress;

//    @Embedded
//    private Address officeAddress;

    @OneToMany(mappedBy="account")
    private Set<Study> studies = new HashSet<Study>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addStudy(Study study){
        study.setAccount(this);
        this.getStudies().add(study);
    }

    public void removeStudy(Study study){
        study.setAccount(null);
        this.getStudies().remove(study);
    }

    public Set<Study> getStudies() {
        return studies;
    }
}
