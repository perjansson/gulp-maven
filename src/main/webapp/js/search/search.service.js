(function() {
    'use strict';

    angular
        .module('searchApp')
        .service('searchService', ['$http', searchService]);

    function searchService($http) {

        this.search = function(searchInput) {
            return $http.post('/search/', searchInput)
                .then(function(response) {
                    return response.data
                });
        };

    }

})();
