package locidnet.core.components.custom.SearchFilter;

import android.widget.Filter;
import android.widget.Filterable;

import java.util.List;

/**
 * Created by Michaelan on 9/27/2016.
 */
public abstract class AbstractFilter<E> implements Filterable {

    public List<E> abcList,abcOriginalList;
    public AbstractFilter(List<E> list) {
        this.abcList = list;
        this.abcOriginalList = list;

    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                abcList = (List<E>) results.values;

                refresh(abcList);

            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<E> filteredResults = null;
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

    public abstract List<E> getFilteredResults(String constraint);

    public abstract void refresh(List<E>abcList);
}
