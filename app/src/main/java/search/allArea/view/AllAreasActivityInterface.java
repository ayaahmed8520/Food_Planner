package search.allArea.view;


import java.util.List;

import search.allArea.model.Area;

public interface AllAreasActivityInterface {
    public List<Area> getAreas();
    public void navigateToParticularAreaMeal(String areaName);
}