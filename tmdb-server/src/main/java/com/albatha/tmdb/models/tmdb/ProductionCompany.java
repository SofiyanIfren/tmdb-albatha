package com.albatha.tmdb.models.tmdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductionCompany {

    private String id;
    private String name;
    private String origin_country;
    private String logo_path;
    
}
