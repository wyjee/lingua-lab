package org.example.lingualabserver.dto;
import lombok.Data;

import java.util.List;

@Data
public class DeleteStatisticsRequest {
    private List<Long> ids;
}