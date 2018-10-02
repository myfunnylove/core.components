package locidnet.core.components.custom.SearchFilterMap.SearchFilter;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michaelan on 9/27/2016.
 */
public abstract class AbstractFilterMap<E> implements Filterable {

    public HashMap<E,ArrayList<E>> abcList,abcOriginalList;
    public AbstractFilterMap(HashMap<E,ArrayList<E>> list) {
        this.abcList = list;
        this.abcOriginalList = list;

    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                abcList = (HashMap<E,ArrayList<E>>) results.values;

                refresh(abcList);

            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Map<E,ArrayList<E>> filteredResults = null;
                if (constraint.length() == 0) {
                    filteredResults = abcOriginalList;
                } else {
                    filteredResults = getFilteredResults(constraint.toString().toLowerCase());
                }

                FilterResults results = new FilterResults();
                results.values = filteredResults;


                return results;
            }
        };
    }

    public abstract HashMap<E,ArrayList<E>> getFilteredResults(String constraint);

    public abstract void refresh(HashMap<E,ArrayList<E>>abcList);
}
