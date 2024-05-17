package com.example.asm1java5.repository;

import com.example.asm1java5.entity.Color;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ColorRepository {
    private final List<Color> listColor;

    public ColorRepository() {
        listColor = new ArrayList<>();
        listColor.add(new Color(1, "CL01", "Red", 1));
        listColor.add(new Color(2, "CL02", "Blue", 0));
        listColor.add(new Color(3, "CL03", "Green", 1));
        listColor.add(new Color(4, "CL04", "Yellow", 1));
    }

    public List<Color> findAll() {
        return listColor;
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
