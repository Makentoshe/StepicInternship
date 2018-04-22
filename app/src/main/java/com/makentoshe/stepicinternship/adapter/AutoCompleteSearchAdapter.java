package com.makentoshe.stepicinternship.adapter;

import android.content.Context;
import android.widget.Filter;

import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.model.AutoCompleteModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Custom adapter for autocomplete that make calls to the server.
 */
public class AutoCompleteSearchAdapter extends BaseAutoCompleteAdapter{

    public AutoCompleteSearchAdapter(Context context) {
        super(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<String> strings = checkSearches(constraint.toString());
                    filterResults.count = strings.size();
                    filterResults.values = strings;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    mAutocompleteList = (List<String>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    /**
     * <p>Returns a list with tips, that may help to find something or reduce input time.</p>
     *
     * @param term the string for which searching
     * @return a list of the strings which contain a tips.
     */
    private List<String> checkSearches(String term) {
        try{
            Response response = StepicInternship.getApi().getAutocompleteData(term).execute();
            ArrayList<String> queries = new ArrayList<>();
            for (AutoCompleteModel.Query query : ((AutoCompleteModel)response.body()).getQueries()){
                queries.add(query.getText());
            }
            return queries;
        } catch (IOException e){
            e.printStackTrace();
        }

        return new ArrayList<>(0);
    }
}
