package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Color;
import com.example.asm1java5.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ColorRepository {
    private final List<Color> listColor;

    public ColorRepository() {
        listColor = new ArrayList<>();
        listColor.add(new Color(1, "CL01", "Red", 1));
        listColor.add(new Color(2, "CL02", "Blue", 1));
        listColor.add(new Color(3, "CL03", "Green", 1));
        listColor.add(new Color(4, "CL04", "Yellow", 1));
    }

    public List<Color> findAll() {
        return listColor;
    }

    public List<Color> findAllByStatusActive() {
        return listColor.stream()
                .filter(color -> color.getStatus() == 1)
                .collect(Collectors.toList());
    }


    public Page<Color> findAllPageable(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int startIndex = pageNumber * pageSize;
        int endIndex = Math.min(startIndex + pageSize, listColor.size());
        List<Color> pageContent = listColor.subList(startIndex, endIndex);
        return new PageImpl<>(pageContent, pageable, listColor.size());
    }

    public void save(Color color) {
        color.setId(this.listColor.size() + 1);
        this.listColor.add(color);
    }

    public void deleteById(Integer id) {
        for (Color color : listColor) {
            if (color.getId().equals(id)) {
                listColor.remove(color);
                break;
            }
        }
    }

    public void update(Color color) {
        for (Color c : listColor) {
            if (c.getId().equals(color.getId())) {
                c.setCode(color.getCode());
                c.setName(color.getName());
                c.setStatus(color.getStatus());
                break;
            }
        }
    }

    public Color findById(Integer id) {
        for (Color color : listColor) {
            if (color.getId().equals(id)) {
                return color;
            }
        }
        return null;
    }
}
