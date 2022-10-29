// var xhr = new XMLHttpRequest();
// var resultJson;
// var apiName;
//
const END_POINT = "http://apis.data.go.kr/1613000/DmstcFlightNvgInfoService";
const ENCODE_KEY = "D3j1vRFzobgXWnqUIARqQGVsV9YEe0KK6GVqIpdNGb8hF9hfUtvp9bddjYyda%2BoXJ%2FQ0bCyPxeZJk1hvOsLSQA%3D%3D";
const DECODE_KEY = "D3j1vRFzobgXWnqUIARqQGVsV9YEe0KK6GVqIpdNGb8hF9hfUtvp9bddjYyda+oXJ/Q0bCyPxeZJk1hvOsLSQA==";
//
// // const corsUrl = 'https://cors-anywhere.herokuapp.com/';
// let url = END_POINT + apiName
// let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + ENCODE_KEY;
// queryParams += '&' + encodeURI('_type') + '=' + encodeURIComponent('json');
//
// xhr.open('GET', url + queryParams);
// xhr.onreadystatechange = function () {
//     if (this.readyState === 4) {
//         resultJson = JSON.parse(this.response)
//
//     }
// }
// xhr.send('');
//
//
// $.ajax({
//     url: "/reservation/bookingApi",
//     type: "POST",
//     contentType: "application/json; charset=UTF-8",
//     data: JSON.stringify(resultJson),
//     dataType: "json",
//     success: function () {
//     },
//     error: function () {
//     }
// });
var main = {
    init: function () {
        var _this = this;
        $('#submit_research').on('click', function () {
            _this.find();
        });
    },
    find: function () {
    console.log("finding");
        $.ajax({
            type: 'GET',
            url: END_POINT + "/getAirmanList" + '?' + encodeURIComponent('serviceKey') + '=' + ENCODE_KEY + '&' + encodeURI('_type') + '=' + encodeURIComponent('json'),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function (res) {
            alert(JSON.stringify(res));
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();
