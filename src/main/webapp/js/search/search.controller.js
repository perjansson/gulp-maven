(function () {
    'use strict';

    angular
        .module('searchApp')
        .controller('SearchController', ['searchService', SearchController]);

    function SearchController(searchService) {
        var vm = this;
        vm.searchInput = {search: ''};
        vm.searchResult = [];
        vm.onSearch = onSearch;

        activate();

        function activate() {
            onSearch();
        }

        function onSearch() {
            searchService.search(vm.searchInput)
                .then(function (searchResult) {
                    vm.searchResult = searchResult;
                });
        }
    }

})();
