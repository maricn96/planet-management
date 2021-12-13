package com.mi.planetmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingRequestDTO {

    private Integer perPage;
    private Integer pageNumber;

}
