package io.github.perjansson.search;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class SearchInput {

    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("search", search)
                .toString();
    }
}
