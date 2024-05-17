package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Size;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SizeRepository {

    private final List<Size> listSize;

    public SizeRepository() {
        listSize = new ArrayList<>();
        listSize.add(new Size(1, "SZ01", "XL", 1));
        listSize.add(new Size(2, "SZ02", "S", 0));
        listSize.add(new Size(3, "SZ03", "M", 1));
    }

    public List<Size> findAll() {
        return listSize;
    }

    public void save(Size size) {
        size.setId(this.listSize.size() + 1);
        this.listSize.add(size);
    }

    public void deleteById(Integer id) {
        for (Size size : listSize) {
            if (size.getId().equals(id)) {
                listSize.remove(size);
                break;
            }
        }
    }

    public void update(Size size) {
        for (Size c : listSize) {
            if (c.getId().equals(size.getId())) {
                c.setCode(size.getCode());
                c.setName(size.getName());
                c.setStatus(size.getStatus());
                break;
            }
        }
    }

    public Size findById(Integer id) {
        for (Size size : listSize) {
            if (size.getId().equals(id)) {
                return size;
            }
        }
        return null;
    }
}
