package org.springproject.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springproject.config.AddressDeserializer;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@JsonDeserialize(using = AddressDeserializer.class)
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String addressLine_1;
    private String city;
    private String country;
}
