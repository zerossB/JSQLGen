$(document).ready(function () {
    $.material.init();

    $('#sob').click(function (e) {
        $('html, body').animate({
            scrollTop: $("#sobre").offset().top
        }, 2000);
    });
    $('#exe').click(function (e) {
        $('html, body').animate({
            scrollTop: $("#exemplos").offset().top
        }, 2000);
    });
    $('#req').click(function (e) {
        $('html, body').animate({
            scrollTop: $("#requisitos").offset().top
        }, 2000);
    });
    $('#con').click(function (e) {
        $('html, body').animate({
            scrollTop: $("#contribuintes").offset().top
        }, 2000);
    });
});