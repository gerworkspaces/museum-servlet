package net.blwsmartware.museum.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataResponse<T> {
    int pageNumber;
    int pageSize;
    long totalElements;
    int totalPages;
    boolean isLastPage;
    List<T> content;

}
