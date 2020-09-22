package guru.springframework.sfgpetclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.BaseEntity;

public class AbstractMapService<T extends BaseEntity, ID> {

	protected Map<Long, T> map = new HashMap<>();

	Set<T> findAll(){
		return new HashSet<>(map.values());
	}

	T findById(ID id) {
		return map.get(id);
	}

	T save(T objcet) {

		if (objcet != null) {
			if (objcet.getId() == null) {
				objcet.setId(getNextId());
			}
			map.put(objcet.getId(), objcet);
		} else {
			throw new RuntimeException("Object cannot be null");
		}
		return objcet;
	}

	void deleteById(ID id) {
		map.remove(id);
	}

	void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));

	}

	private Long getNextId() {

		Long nextId = null;

		try {
			nextId = Collections.max(map.keySet()) + 1;
		} catch (NoSuchElementException e) {
			nextId = 1L;
		}

		return nextId;
	}

}
