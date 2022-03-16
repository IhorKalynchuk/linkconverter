package com.trendyol.linkconverter.repository;

import com.trendyol.linkconverter.model.LinkConversion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkConversionRepository extends JpaRepository<LinkConversion, Long> {
}
