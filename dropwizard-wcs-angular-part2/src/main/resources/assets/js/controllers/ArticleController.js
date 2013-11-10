'use strict';


/**
 * ArticleController
 * @constructor
 */
var ArticleController = function($scope, $http) {
	
	$scope.filteredArticles = [];
    $scope.articles = [];
	$scope.currentPage = 1;
	$scope.numPerPage = 5;
	$scope.maxSize = 5;
	$scope.viewMode = false;

    $scope.fetchArticlesList = function() {
        $http.get('service/articles').success(function(articlesList){
            $scope.articles = articlesList;
            $scope.filteredArticles = $scope.articles.slice(0, $scope.numPerPage);

        });
    }

    $scope.viewArticle = function(selectedArticle) {
        $scope.resetError();
        $scope.viewMode = true;
        $http.get('service/articles/' + selectedArticle.id).success(function(article){
            $scope.article = article;
        });
    }
    
    $scope.numPages = function () {
        return Math.ceil($scope.articles.length / $scope.numPerPage);
    };
      
	$scope.$watch('currentPage + numPerPage', function() {
	    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
	    , end = begin + $scope.numPerPage;
	    
	    $scope.filteredArticles = $scope.articles.slice(begin, end);
	}); 



    $scope.resetError = function() {
        $scope.error = false;
        $scope.errorMessage = '';
    }

    $scope.setError = function(message) {
        $scope.error = true;
        $scope.errorMessage = message;
    }
 
    $scope.fetchArticlesList();
    $scope.predicate = 'id';
}