package org.springproject.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springproject.entity.Address;
import org.springproject.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("username")
    @NotBlank(message = "Name field is not blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String username;

    @JsonProperty("email")
    @Email(message = "Email should be valid")
    private String email;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("userCreatedTimeStamp")
    private LocalDateTime userCreatedTimeStamp;

    @JsonProperty("userLastUpdatedTimeStamp")
    private LocalDateTime userLastUpdatedTimeStamp;
}