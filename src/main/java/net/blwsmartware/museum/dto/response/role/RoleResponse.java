package net.blwsmartware.museum.dto.response.role;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.blwsmartware.museum.entity.Permission;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponse {
    int id;
    String name;
    String description;
    Set<Permission> permissions ;
}
