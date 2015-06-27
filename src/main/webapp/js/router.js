(function() {
    'use strict';

    angular
        .module('searchApp')
        .config(['$stateProvider', '$urlRouterProvider', routeConfig]);

    function routeConfig($stateProvider, $urlRouterProvider) {

        $stateProvider
            .state('search', {
                url: '/search',
                templateUrl: 'html/search.html',
                controller: 'SearchController as vm'
            });

        $urlRouterProvider.otherwise('/search');

    }

    angular
        .module('searchApp')
        .run(function($state, $rootScope) {
            $rootScope.$on("$stateChangeError", console.log.bind(console));
        });

})();
