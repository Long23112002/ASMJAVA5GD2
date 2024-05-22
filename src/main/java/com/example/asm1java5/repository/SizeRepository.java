package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Product;
import com.example.asm1java5.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SizeRepository {

    private final List<Size> listSize;

    public SizeRepository() {
        listSize = new ArrayList<>();
        listSize.add(new Size(1, "SZ01", "XL", 1));
        listSize.add(new Size(2, "SZ02", "S", 1));
        listSize.add(new Size(3, "SZ03", "M", 1));
    }

    public List<Size> findByName(String name) {
        return listSize.stream()
                .filter(customer -> customer.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Page<Size> findAllPageable(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, listSize.size());
        List<Size> pageContent = listSize.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, pageable, listSize.size());
    }

    public List<Size> findAllByStatusActive() {
        return listSize.stream()
                .filter(size -> size.getStatus() == 1)
                .collect(Collectors.toList());
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

    public boolean existsByName(String name) {
        return listSize.stream().anyMatch(size -> size.getName().trim().equalsIgnoreCase(name.trim()));
    }

    public boolean existsByCode(String code) {
        return listSize.stream().anyMatch(size -> size.getCode().trim().equals(code.trim()));
    }

    public boolean existsByNameAndIdNot(String name, Integer id) {
        return listSize.stream().anyMatch(size -> size.getName().trim().equals(name.trim()) && !size.getId().equals(id));
    }

    public boolean existsByCodeAndIdNot(String code, Integer id) {
        return listSize.stream().anyMatch(size -> size.getCode().trim().equals(code.trim()) && !size.getId().equals(id));
    }
}
