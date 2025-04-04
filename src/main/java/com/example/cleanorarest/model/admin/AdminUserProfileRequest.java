package com.example.cleanorarest.model.admin;

import com.example.cleanorarest.validators.EmailUnique;
import com.example.cleanorarest.validators.FieldPhoneUnique;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
@EmailUnique
@FieldPhoneUnique
public class AdminUserProfileRequest {
    private Long id;

    @Size(message = "Имя должно быть от 2 до 100 символов", min = 2, max = 100)
    @NotBlank(message = "Имя обязательно")
    private String name;

    private String surname;

    private MultipartFile avatar;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(max = 100, message = "Размер поля должен быть не более 100 символов")
    @Email(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-z]{2,3}", message = "Неверный формат email")
    private String email;

    @Pattern(regexp = "\\+380(50|66|95|99|67|68|96|97|98|63|93|73)[0-9]{7}", message = "Неверный формат номера")
    private String phoneNumber;

    @Size(message = "Пароль должен быть от 6 до 100 символов", min = 6, max = 100)
    @NotBlank(message = "Пароль обязателен")
    private String password;

    private Boolean isActive;

    @NotNull(message = "Роль обязательна")
    @Pattern(regexp = "ADMIN|MANAGER", message = "Недопустимая роль. Допустимые значения: Администратор, Менеджер")
    private String role;
}