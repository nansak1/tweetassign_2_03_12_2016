/**
 * Created by nansak1 on 4/2/2016.
 */

app.service('loginService', function () {

    var token ={};
    var username = {};
   /* var attendees = [
        {id: 1, first: 'ted', last: 'johnson'},
        {id: 2, first: 'dale', last: 'keith'}
    ];

    var addAttendee = function (newObj) {
        newObj.id = attendees.length + 1;
        attendees.push(newObj);
    };

    var updateAttendee = function (newObj) {
        for (i = 0; i < attendees.length; i++) {
            if (attendees[i].id == newObj.id) {
                attendees[i] = newObj;
            }
        }
    };*/

    var getToken = function () {
        return token;
    };

    var setToken = function (newToken) {
        token = newToken;
    };

    var getUsername = function(){
        return username;
    };

    var setUsername = function(newUsername){
        username = newUsername;
    };

   /* var deleteAttendee = function (id) {
        for (i = 0; i < attendees.length; i++) {
            if (id == attendees[i].id) {
                attendees.splice(i, 1);
            }
        }
    };*/

    return {

        getToken : getToken,
        setToken : setToken,
        getUsername : getUsername,
        setUsername: setUsername

        /*addAttendee: addAttendee,
        getAttendees: getAttendees,
        updateAttendee: updateAttendee,
        deleteAttendee: deleteAttendee*/
    };
});