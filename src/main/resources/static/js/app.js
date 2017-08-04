(function(){
    'use strict';
    var todoListApp = angular.module('todoListApp', []);

    var viewsLoaderCtr = function($rootScope, $scope, $http) {
        $scope.todoItems = [];
        $scope.checks = [];
        $http({
            method: 'GET',
            url: '/getAllItems'
        }).then(function successCallback(response) {
            console.log(response);
            $scope.todoItems = response.data;
        }, function errorCallback(response) {
            alert("Error loading the items");
        });

        $rootScope.$on('itemAdded', function(event, data) {
            $scope.todoItems.push(data);            
        })

        $scope.updateChecked = function(index) {
            var correctedIndex = $scope.todoItems.length - (index+1);
             var checkInfo = {
                id: $scope.todoItems[correctedIndex].id ,
                newState: $scope.todoItems[correctedIndex].state
            };
            $http({
                method: 'GET',
                url: '/updateCheck',
                params: checkInfo
            }).then(function successCallback(response) {
            }, function errorCallback(response) {
                alert("Error updating the item");
            });
        }

        $scope.seeContent = function(index) {
            var correctedIndex = $scope.todoItems.length - (index+1);
            alert($scope.todoItems[correctedIndex].content);
        }
    };
    var addItemListCtr = function($rootScope, $scope, $http) {
        $scope.title = "";
        $scope.content = "";
        $scope.addItem = function () {
            var itemInfo = {
                title: $scope.title,
                content: $scope.content
            };
            if($scope.title == "") {
                alert("Please don't insert empty data!");
            } else {
                $http({
                    method: 'GET',
                    url: '/createListItem',
                    params: itemInfo
                }).then(function successCallback(response) {
                    $rootScope.$broadcast('itemAdded',response.data);
                    $scope.title = "";
                    $scope.content = "";
                    hideCard();
                }, function errorCallback(response) {
                    alert("Error loading the items");
                });
            }
        }
    };

    viewsLoaderCtr.$inject = ['$rootScope','$scope', '$http'];
    addItemListCtr.$inject = ['$rootScope','$scope', '$http'];
    todoListApp.controller('viewsLoaderCtr',viewsLoaderCtr);
    todoListApp.controller('addItemListCtr',addItemListCtr);
    
})();