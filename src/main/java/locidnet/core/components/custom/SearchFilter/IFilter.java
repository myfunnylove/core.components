package locidnet.core.components.custom.SearchFilter;

/**
 * Created by Michaelan on 9/27/2016.
 */
public interface IFilter<E> {

    AbstractFilter<E> getFilteredResults();
}
