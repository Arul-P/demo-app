package com.example.demo.service;

import com.example.demo.entity.Metadata;
import com.example.demo.pojo.Root;

public interface MetadataService {
	
	void storeMetadata(Root root);
	
	Metadata getMetaData(String isrc);
}
