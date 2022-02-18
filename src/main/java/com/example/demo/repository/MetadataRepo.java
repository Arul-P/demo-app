package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Metadata;

@Repository
public interface MetadataRepo extends JpaRepository<Metadata, Long> {

	Metadata findByIsrc(String isrc);
}
