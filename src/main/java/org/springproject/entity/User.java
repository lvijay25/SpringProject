package org.springproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users", indexes = @Index(name = "userIndex", columnList = "username"))
public class User implements Serializable {

    private static final Logger logging = LogManager.getLogger(User.class);
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", length=150, nullable = false, unique = false)
    private String username;
    @Column(name = "email", unique = true)
    private String email;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="addressLine_1", column = @Column(name = "address_line_1")),
            @AttributeOverride(name="city", column = @Column(name = "city_name")),
            @AttributeOverride(name="country", column = @Column(name = "country_name"))
    })
    private Address address;

    private LocalDateTime userCreatedTimeStamp;
    private LocalDateTime userLastUpdatedTimeStamp;

    // JPA CallBacks Life Cycle used for callback methods to notify if any changes are happens in database.
    @PrePersist
    public void beforePersist(){
        LocalDateTime localDateTime = LocalDateTime.now();
        logging.info("Create New User Initiated");
        setUserCreatedTimeStamp(localDateTime);
        setUserLastUpdatedTimeStamp(localDateTime);
    }
    @PreUpdate
    private void beforeUpdate(){
        logging.info("User update successful..");
        setUserLastUpdatedTimeStamp(LocalDateTime.now());
    }

    @PostPersist
    private void afterUserCreated(){
        logging.info("User Created Successfully");
    }

    @PostUpdate
    private void afterUserUpdated(){
        logging.info("User Updated Successfully");
    }
}
