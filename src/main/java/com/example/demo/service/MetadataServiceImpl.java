package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Metadata;
import com.example.demo.pojo.ExternalIds;
import com.example.demo.pojo.Item;
import com.example.demo.pojo.Root;
import com.example.demo.repository.MetadataRepo;

@Service
public class MetadataServiceImpl implements MetadataService {

	@Autowired
	MetadataRepo repo;

	@Override
	public void storeMetadata(Root root) {
		ArrayList<Item> items = root.getTracks().getItems();
		for (Item item : items) {
			ExternalIds extId = item.getExternal_ids();
			Metadata meta = repo.findByIsrc(extId.getIsrc());
			if (null != meta) {
				meta.setName(item.getName());
				meta.setDuration(Long.valueOf(item.getDuration_ms()));
				if (item.isExplicit())
					meta.setExplicit("TRUE");
				else
					meta.setExplicit("FALSE");
				repo.save(meta);
			} else {
				Metadata data = new Metadata();
				data.setIsrc(extId.getIsrc());
				data.setName(item.getName());
				data.setDuration(Long.valueOf(item.getDuration_ms()));
				if (item.isExplicit()) {
					data.setExplicit("TRUE");
				} else {
					data.setExplicit("FALSE");
				}
				repo.save(data);
			}
			break;
		}
	}

	@Override
	public Metadata getMetaData(String isrc) {
		return repo.findByIsrc(isrc);
	}

}
