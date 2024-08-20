package search.presenter;


import java.util.List;

import search.allArea.model.AllAreas;
import search.allArea.model.Area;

public class SearchPresenter {
    public List<Area> getAreas(){
        return AllAreas.getInstance().getAllAreas();
    }

}

