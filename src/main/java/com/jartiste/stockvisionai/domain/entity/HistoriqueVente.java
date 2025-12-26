    package com.jartiste.stockvisionai.domain.entity;

    import lombok.*;
    import org.springframework.data.annotation.CreatedDate;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    import java.time.*;

    @Document
    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class HistoriqueVente {

        @Id
        private String id;

        private Integer quantity;
        private String productId;
        private String entrepotId;
        private DayOfWeek dayOfWeek;

        private Month month;

        private Integer year;
        @CreatedDate
        private LocalDate CreateAt;

    }
