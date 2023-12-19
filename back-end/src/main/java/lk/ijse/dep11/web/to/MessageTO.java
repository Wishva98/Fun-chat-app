package lk.ijse.dep11.web.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTO {
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "In valid email format")
    private String email;

    @NotBlank(message = "message cannot be blank")
    private String message;

}
